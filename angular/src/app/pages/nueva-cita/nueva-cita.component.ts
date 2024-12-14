import {Component, OnInit} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {ProximosTratamientosComponent} from "../../shared/proximos-tratamientos/proximos-tratamientos.component";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmCitaComponent} from "../../shared/confirm-cita/confirm-cita.component";
import {ConsultaService} from "../../services/consulta.service";
import {Consulta} from "../../models/consulta.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-nueva-cita',
  templateUrl: './nueva-cita.component.html',
  styleUrl: './nueva-cita.component.css'
})
export class NuevaCitaComponent implements OnInit{

  veterinarios : Usuario[] = [];
  selectedVeterinario : number | undefined;
  citasOcupadas: string[] = [];
  horasDisponibles: string[] = [];
  selectedHora: string = '';
  motivo: string = '';
  selectedFecha: string = '';
  mascotaId : number | undefined;

  constructor(public usuarioService : UsuarioService,
              public dialog : MatDialog,
              public consultaService : ConsultaService,
              private route : ActivatedRoute ) { }

  calendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    locale: 'es',
    firstDay: 1,
    editable: true,
    selectable: true,
    events: [],
    dateClick: this.handleDateClick.bind(this)
  };

  ngOnInit(): void {
    this.mascotaId = +this.route.snapshot.paramMap.get('idMascota')!;
    this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
      this.veterinarios = data;
    });
    this.generarHorasDisponibles();
  }

  handleDateClick(arg: any) {
    this.selectedFecha = arg.dateStr;
    console.log(`Fecha seleccionada: ${this.selectedFecha}`);
  }

  goBack(){
    window.history.back();
  }

  crearCita(){

    if (!this.selectedFecha || !this.selectedHora || !this.selectedVeterinario || !this.motivo) {
      alert("Por favor, complete todos los campos (fecha, hora, veterinario, y motivo).");
      return;
    }

    // Crea un objeto de cita con la información que se ha seleccionado
    const cita: Consulta = {
      idConsulta: 0,
      mascota: this.mascotaId!,
      idMascota: this.mascotaId!,
      idVeterinario: this.selectedVeterinario!,
      fechaCitaConsulta: this.selectedFecha,
      motivo: this.motivo,
      horaCita: this.selectedHora,
      esCita: 1,
      fechaAlta: new Date().toISOString(),
      diagnostico: '',
      observaciones: '',
      fechaModificacion: '',
      tratamientos: [],
    };

    const dialogRef = this.dialog.open(ConfirmCitaComponent, {
      data: cita
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.consultaService.crearCita(cita).subscribe({
          next: (response) => {
            console.log('Cita creada exitosamente:', response);
            alert("Cita creada exitosamente.");
          },
          error: (error) => {
            console.error('Error al crear la cita:', error);
            alert("Hubo un error al crear la cita.");
          }
        });
      } else {
        console.log('Cita no confirmada');
      }
    });
  }

  onVeterinarioChange() {
    if (!this.selectedVeterinario) return;

    this.generarHorasDisponibles();

    this.consultaService.getCitasByIdVeterinario(this.selectedVeterinario).subscribe(citas => {
      this.citasOcupadas = citas.map((cita : Consulta) => this.formatHora(cita.horaCita));

      this.actualizarHorasDisponibles();
    });
  }

  generarHorasDisponibles() {
    const horas = [];
    const inicio = 8;
    const fin = 20;

    for (let h = inicio; h < fin; h++) {
      horas.push(this.formatHora(`${h}:00:00`));
      horas.push(this.formatHora(`${h}:30:00`));
    }

    this.horasDisponibles = horas; // Reinicia la lista de horas disponibles
  }

  actualizarHorasDisponibles() {
    this.horasDisponibles = this.horasDisponibles.filter(hora => !this.citasOcupadas.includes(hora));
  }

  formatHora(hora: string): string {
    const [h, m] = hora.split(':');
    const horaInt = parseInt(h, 10);
    return `${horaInt.toString().padStart(2, '0')}:${m}`;
  }

}

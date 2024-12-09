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

  constructor(public usuarioService : UsuarioService,
              public dialog : MatDialog,
              public consultaService : ConsultaService) { }

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
    this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
      this.veterinarios = data;
    });
    this.generarHorasDisponibles();
  }

  handleDateClick(arg: any) {
    alert(`Fecha seleccionada: ${arg.dateStr}`);
  }

  goBack(){
    window.history.back();
  }

  crearCita(){
    const dialogRef = this.dialog.open(ConfirmCitaComponent, {

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  onVeterinarioChange() {
    if (!this.selectedVeterinario) return;

    // Reinicia el listado de horas disponibles
    this.generarHorasDisponibles();

    // Llama al servicio para obtener las citas del veterinario seleccionado
    this.consultaService.getCitasByIdVeterinario(this.selectedVeterinario).subscribe(citas => {
      this.citasOcupadas = citas.map((cita : Consulta) => this.formatHora(cita.horaCita));

      // Filtra las horas ocupadas solo para el veterinario seleccionado
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

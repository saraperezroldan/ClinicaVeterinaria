import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
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
import {FullCalendarComponent} from "@fullcalendar/angular";
import { EventApi } from '@fullcalendar/core';  // Importa EventApi


@Component({
  selector: 'app-nueva-cita',
  templateUrl: './nueva-cita.component.html',
  styleUrl: './nueva-cita.component.css'
})
export class NuevaCitaComponent implements OnInit{

  @ViewChild(FullCalendarComponent) calendarComponent: FullCalendarComponent | undefined;

  veterinarios : Usuario[] = [];
  selectedVeterinario : number | undefined;
  selectedVeterinarioNombre : string = '';
  citasOcupadas: string[] = [];
  horasDisponibles: string[] = [];
  selectedHora: string = '';
  motivo: string = '';
  selectedFecha: string = '';
  mascotaId : number | undefined;
  errorMensaje : string = '';
  isEditMode: boolean = false;
  citaToEdit?: Consulta;

  constructor(public usuarioService : UsuarioService,
              public dialog : MatDialog,
              public consultaService : ConsultaService,
              private route : ActivatedRoute,
              private cdr:ChangeDetectorRef) { }

  calendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    locale: 'es',
    firstDay: 1,
    editable: true,
    selectable: true,
    events: [],
    dateClick: this.handleDateClick.bind(this),
    initialDate: new Date(),
  };

  ngOnInit(): void {
    const idCita = this.route.snapshot.paramMap.get('idCita');
    if (idCita) {
      this.isEditMode = true;
      this.consultaService.getCitaById(+idCita).subscribe((cita: Consulta) => {
        this.citaToEdit = cita;
        this.selectedFecha = cita.fechaCitaConsulta;
        this.selectedHora = this.formatHora(cita.horaCita);
        this.selectedVeterinario = cita.idVeterinario;
        this.motivo = cita.motivo;
        this.mascotaId = cita.mascota;

        this.usuarioService.getUsuarioById(cita.idVeterinario).subscribe((veterinario: Usuario) => {
          this.selectedVeterinarioNombre = veterinario.nombre + ' ' + veterinario.apellidos;
        });

        this.agregarEventoCita(cita.fechaCitaConsulta);

        this.cdr.detectChanges();
        console.log(cita);
        console.log(this.selectedVeterinario);
        console.log(this.selectedVeterinarioNombre);
        console.log(this.selectedFecha);
        console.log(this.selectedHora);
        console.log(this.mascotaId);
      });
    } else {
      this.isEditMode = false;
      this.mascotaId = +this.route.snapshot.paramMap.get('idMascota')!;
      this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
        this.veterinarios = data;
      });
      this.generarHorasDisponibles();
    }
  }


  handleDateClick(arg: any) {
    this.selectedFecha = arg.dateStr;
    console.log(`Fecha seleccionada: ${this.selectedFecha}`);

    let calendarApi = arg.view.calendar;

    const existingEvents = calendarApi.getEvents();
    existingEvents.forEach((event: EventApi) => {
      event.remove();
    });

    calendarApi.addEvent({
      title: 'Día seleccionado',
      start: arg.date,
      allDay: true,
      color: '#8cb4d2',
      textColor: '#fff',
    });

    calendarApi.select(arg.date);
  }

  agregarEventoCita(fecha: string): void {
    if (!this.calendarComponent) {
      return;
    }

    const calendarApi = this.calendarComponent.getApi();

    const existingEvents = calendarApi.getEvents();
    existingEvents.forEach((event: any) => event.remove());

    calendarApi.addEvent({
      title: 'Día de la cita',
      start: fecha,
      allDay: true,
      color: '#8cb4d2',
      textColor: '#fff',
    });
  }

  goBack(){
    window.history.back();
  }

  crearOActualizarCita(){

    if (!this.selectedFecha || !this.selectedHora || !this.selectedVeterinario || !this.motivo) {
      this.errorMensaje = "Por favor, complete todos los campos (fecha, hora, veterinario, y motivo).";
      return;
    }

    const cita: Consulta = {
      idConsulta: this.isEditMode ? this.citaToEdit?.idConsulta! : 0,
      mascota: this.mascotaId!,
      idMascota: this.mascotaId!,
      idVeterinario: this.citaToEdit?.idVeterinario || this.selectedVeterinario!,
      fechaCitaConsulta: this.selectedFecha,
      motivo: this.motivo,
      horaCita: this.selectedHora,
      esCita: 1,
      fechaAlta: this.isEditMode ? this.citaToEdit?.fechaAlta! : new Date().toISOString(),
      diagnostico: '',
      observaciones: '',
      fechaModificacion: '',
      tratamientos: [],
    };

    const dialogRef = this.dialog.open(ConfirmCitaComponent, {
      data: {
        cita : cita,
        nombreVeterinario: this.selectedVeterinarioNombre,
        isEditMode: this.isEditMode
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        if (this.isEditMode) {
          this.consultaService.modificarCita(cita).subscribe({
            next: () => {
              console.log('Cita actualizada exitosamente');
              alert('Cita actualizada exitosamente.');
            },
            error: (error) => {
              console.error('Error al actualizar la cita:', error);
              alert('Hubo un error al actualizar la cita.');
            },
          });
        } else {
          // Crear nueva cita
          this.consultaService.crearCita(cita).subscribe({
            next: () => {
              console.log('Cita creada exitosamente');
              alert('Cita creada exitosamente.');
            },
            error: (error) => {
              console.error('Error al crear la cita:', error);
              alert('Hubo un error al crear la cita.');
            },
          });
        }
      } else {
        console.log('Cita no confirmada.');
      }
    });
  }

  onVeterinarioChange(){
    if (!this.selectedVeterinario) return;

    this.generarHorasDisponibles();

    this.consultaService.getCitasByIdVeterinario(this.selectedVeterinario).subscribe(citas => {
      this.citasOcupadas = citas.map((cita : Consulta) => this.formatHora(cita.horaCita));

      this.actualizarHorasDisponibles();
    });
    this.usuarioService.getUsuarioById(this.selectedVeterinario).subscribe((veterinario: Usuario) => {
      this.selectedVeterinarioNombre = veterinario.nombre + ' ' + veterinario.apellidos;
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

    this.horasDisponibles = horas;
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

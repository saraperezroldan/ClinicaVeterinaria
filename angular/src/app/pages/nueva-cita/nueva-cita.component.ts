import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmCitaComponent} from "../../shared/confirm-cita/confirm-cita.component";
import {ConsultaService} from "../../services/consulta.service";
import {Consulta} from "../../models/consulta.model";
import {ActivatedRoute} from "@angular/router";
import {FullCalendarComponent} from "@fullcalendar/angular";
import { EventApi } from '@fullcalendar/core';



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
  selectedHoraOriginal: string = '';

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
    events: [] as any[],
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

        this.generarHorasDisponibles();
        this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
          this.veterinarios = data;
        });

        this.usuarioService.getUsuarioById(cita.idVeterinario).subscribe((veterinario: Usuario) => {
          this.selectedVeterinarioNombre = veterinario.nombre + ' ' + veterinario.apellidos;
        });

        this.agregarEventoCita(cita.fechaCitaConsulta);

        this.cdr.detectChanges();
      });
    } else {
      this.isEditMode = false;
      this.mascotaId = +this.route.snapshot.paramMap.get('idMascota')!;
      this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
        this.veterinarios = data;
      });
      this.generarHorasDisponibles();
    }
    if (this.mascotaId) {
      this.consultaService.getCitasByIdMascota(this.mascotaId).subscribe((citas: Consulta[]) => {

        console.log('Consultas obtenidas para la mascota:', citas);
        const eventos = citas.map(cita => ({
          title: `${this.formatHora(cita.horaCita)} Cita Veterinaria`,
          start: new Date(cita.fechaCitaConsulta),
          allDay: true,
          color: '#ff5733',
        }));
        console.log('Eventos a agregar al calendario:', eventos);


        this.calendarOptions.events = [...this.calendarOptions.events, ...eventos];

        this.cdr.detectChanges();
      });
    }
  }


  handleDateClick(arg: any) {
    this.selectedFecha = arg.dateStr;
    console.log(`Fecha seleccionada: ${this.selectedFecha}`);

    if (this.selectedVeterinario) {
      this.consultaService.getCitasByIdVeterinario(this.selectedVeterinario, this.selectedFecha).subscribe(citas => {
        this.citasOcupadas = citas.map((cita: Consulta) => this.formatHora(cita.horaCita));
        this.generarHorasDisponibles();
      });
    }

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

    console.log("Datos enviados al dialogo de confirmación: ", cita, this.selectedVeterinarioNombre, this.isEditMode ? "Modificación" : "Creación")

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
          this.consultaService.crearCita(cita).subscribe({
            next: () => {
              console.log('Cita creada exitosamente');
              alert('Cita creada exitosamente.');
              window.history.back();
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

    this.consultaService.getCitasByIdVeterinario(this.selectedVeterinario, this.selectedFecha).subscribe(citas => {
      this.citasOcupadas = citas.map((cita: Consulta) => this.formatHora(cita.horaCita));
      this.generarHorasDisponibles();
    });
    this.usuarioService.getUsuarioById(this.selectedVeterinario).subscribe((veterinario: Usuario) => {
      this.selectedVeterinarioNombre = veterinario.nombre + ' ' + veterinario.apellidos;
    });

    console.log('Veterinario seleccionado:', this.selectedVeterinario);
    console.log('Fecha seleccionada:', this.selectedFecha);
  }

  onHoraChange(event: Event): void {
    console.log('Hora seleccionada antes de detectar cambios:', this.selectedHora);
    this.cdr.detectChanges();
    console.log('Hora seleccionada después de detectar cambios:', this.selectedHora);
  }


  generarHorasDisponibles() {
    const horas = [];
    const inicio = 8;
    const fin = 20;

    for (let h = inicio; h < fin; h++) {
      const horaCompleta = `${h}:00:00`;
      const mediaHora = `${h}:30:00`;

      if (!this.citasOcupadas.includes(this.formatHora(horaCompleta))) {
        horas.push(this.formatHora(horaCompleta));
      }
      if (!this.citasOcupadas.includes(this.formatHora(mediaHora))) {
        horas.push(this.formatHora(mediaHora));
      }
    }

    console.log('Horas disponibles:', horas);
    console.log('Horas ocupadas:', this.citasOcupadas);

    this.horasDisponibles = horas;
  }


  formatHora(hora: string): string {
    const [h, m] = hora.split(':');
    const horaInt = parseInt(h, 10);
    return `${horaInt.toString().padStart(2, '0')}:${m}`;
  }

}

import {Component, OnInit} from '@angular/core';
import {Tratamiento} from "../../models/tratamiento.model";
import {ActivatedRoute} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {TratamientoService} from "../../services/tratamiento.service";
import {MascotaService} from "../../services/mascota.service";
import {Mascota} from "../../models/mascota.model";
import {Consulta} from "../../models/consulta.model";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-nueva-consulta',
  templateUrl: './nueva-consulta.component.html',
  styleUrl: './nueva-consulta.component.css'
})
export class NuevaConsultaComponent implements OnInit {

  mascota!: Mascota;
  idMascota!: number;
  nombreMascota: string = '';
  nombreVeterinario: string = '';
  currentUser: any = null;
  usuario!: Usuario;
  fecha: string = new Date().toLocaleDateString();

  tratamientos: Tratamiento[] = [];
  listaTratamientos: Tratamiento[] = [];
  tratamientoSeleccionado!: Tratamiento;
  total: number = 0;

  consulta: Consulta = {
    idConsulta: 0,
    fechaCitaConsulta: this.fecha,
    horaCita: new Date().toLocaleTimeString(),
    idMascota: this.idMascota,
    mascota: this.idMascota,
    idVeterinario: 0,
    motivo: '',
    diagnostico: '',
    observaciones: '',
    fechaAlta: '',
    fechaModificacion: '',
    tratamientos: [],
    esCita : 0,
  };


  constructor(
    private route: ActivatedRoute,
    private consultaService: ConsultaService,
    private usuarioService: UsuarioService,
    private tratamientoService: TratamientoService,
    private mascotaService: MascotaService
  ) {
  }

  ngOnInit(): void {
    this.idMascota = this.route.snapshot.params['idMascota'];
    if (this.idMascota) {
      this.mascotaService.getInfoMascotaById(this.idMascota).subscribe(
        (mascota: Mascota) => {
          this.mascota = mascota;
          this.nombreMascota = mascota.nombre;
        });

      this.currentUser = this.usuarioService.getCurrentUser();
      console.log(this.currentUser);
      this.nombreVeterinario = this.currentUser.nombre + ' ' + this.currentUser.apellidos;

      this.tratamientoService.getTratamientos().subscribe((data: Tratamiento[]) => {
        this.listaTratamientos = data;
        console.log(this.listaTratamientos);
      });

      this.verificarCitaExistente();
    }
  }

  verificarCitaExistente(): void {
    const fechaActual = new Date().toISOString().split('T')[0];

    this.consultaService.getCitasByIdMascota(this.idMascota).subscribe({
      next: (citas: any[]) => {
        const citaExistente = citas.find(cita => cita.fechaCitaConsulta === fechaActual);

        if (citaExistente) {
          console.log('Cita existente encontrada:', citaExistente);

          this.consulta.idConsulta = citaExistente.idConsulta;
          this.consulta.motivo = citaExistente.motivo;
          this.consulta.horaCita = citaExistente.horaCita;
          this.consulta.idVeterinario = citaExistente.idVeterinario;
        } else {
          console.log('No hay citas para hoy.');
        }
      },
      error: (error) => {
        console.error('Error al obtener las citas:', error);
      }
    });
  }


  onTratamientoSeleccionado(tratamientoSeleccionado: Tratamiento): void {
    if (tratamientoSeleccionado) {
      const existe = this.tratamientos.some(t => t.idTratamiento === tratamientoSeleccionado.idTratamiento);

      if (!existe) {
        this.tratamientos.push(tratamientoSeleccionado);

        this.total += tratamientoSeleccionado.precio;
      } else {
        alert('El tratamiento ya está agregado.');
      }

      this.tratamientoSeleccionado = null!;
    }
  }

  eliminarTratamiento(index: number): void {
    this.total -= this.tratamientos[index].precio;

    this.tratamientos.splice(index, 1);
  }

  goBack() {
    window.history.back();
  }

  nuevaConsulta(): void {
    if (this.consulta.idConsulta !== 0) {
      this.convertirCitaEnConsulta();
    } else {
      this.crearConsulta();
    }
  }


  crearConsulta(): void {

    this.consulta.tratamientos = [];
    this.consulta.idVeterinario = this.currentUser.idUsuario;
    this.consulta.idMascota = this.idMascota;
    this.consulta.mascota = this.idMascota;
    this.consulta.fechaCitaConsulta = new Date().toISOString().split('T')[0];
    console.log('Creando consulta:', this.consulta);

    this.consultaService.crearConsulta(this.consulta).subscribe({
      next: (response) => {
        console.log('Consulta creada:', response);

        this.consulta.idConsulta = response.idConsulta;

        this.modificarConsultaConTratamientos();
      },
      error: (error) => {
        console.error('Error al crear la consulta:', error);
      }
    });
  }

  modificarConsultaConTratamientos(): void {
    const consultaModificada = {
      ...this.consulta,
      tratamientosConsulta: this.tratamientos.map(t => ({idTratamiento: t.idTratamiento}))
    };

    this.consultaService.modificarConsulta(consultaModificada).subscribe({
      next: (response) => {
        console.log('Consulta actualizada con tratamientos:', response);
        alert('Consulta creada correctamente');
        window.history.back();

      },
      error: (error) => {
        console.error('Error al actualizar la consulta con tratamientos:', error);

      }
    });
  }

  convertirCitaEnConsulta(): void {
    const consultaConvertida = {
      idConsulta: this.consulta.idConsulta,
      diagnostico: this.consulta.diagnostico,
      observaciones: this.consulta.observaciones,
      mascota: Number(this.idMascota),
      idVeterinario: this.currentUser.idUsuario,
      tratamientosConsulta: this.tratamientos.map(t => ({
        idConsulta: this.consulta.idConsulta,
        idTratamiento: t.idTratamiento
      }))
    };

    console.log('Datos enviados al backend para convertir cita:', consultaConvertida);

    this.consultaService.convertirCitaEnConsulta(consultaConvertida).subscribe({
      next: (response) => {
        console.log('Cita convertida en consulta:', response);
        alert('La cita ha sido convertida exitosamente en una consulta.');
        window.history.back();
      },
      error: (error) => {
        console.error('Error al convertir la cita en consulta:', error);
        alert('Hubo un error al intentar convertir la cita en consulta.');
      }
    });
  }


}


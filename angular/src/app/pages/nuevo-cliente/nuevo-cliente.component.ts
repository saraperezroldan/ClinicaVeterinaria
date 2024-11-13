import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EspecieService} from "../../services/especie.service";
import {RazaService} from "../../services/raza.service";
import {Especie} from "../../models/especie.model";
import {Raza} from "../../models/raza.model";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
  selector: 'app-nuevo-cliente',
  templateUrl: './nuevo-cliente.component.html',
  styleUrl: './nuevo-cliente.component.css'
})
export class NuevoClienteComponent {

  especies: Especie[] = [];
  razaOptions: Raza[] = [];
  selectedEspecie: number | null = null;
  selectedRaza: number | null = null;
  errorMensaje: string = '';
  mascota : Mascota = {
    idMascota: 0,
    nombre: "",
    edad: null,
    peso: null,
    genero: "",
    complexion: "",
    imagen: "https://www.shutterstock.com/image-vector/vector-flat-illustration-grayscale-avatar-600nw-2264922221.jpg",
    activo: 1,
    fechaNacimiento: "",
    fechaAlta: "",
    fechaModificacion: "",
    fechaBaja: "",
    usuario : 0,
    raza: {
      idRaza: 0,
      nombre: "",
      especie: {
        idEspecie: 0,
        nombre: ""
      }
    }
  };
  usuario: Usuario = {
    idUsuario: 0,
    nombre: '',
    apellidos: '',
    dni: '',
    email: '',
    password: 'password',
    telefono: '',
    direccion: '',
    poblacion: '',
    provincia: '',
    codigoPostal: '',
    fechaNacimiento: '',
    imagen: "https://www.shutterstock.com/image-vector/vector-flat-illustration-grayscale-avatar-600nw-2264922221.jpg",
    fechaAlta: new Date().toISOString(),
    fechaModificacion: '',
    fechaBaja: '',
    rol: 3,
    activo: 1
  };

  constructor(
      private especieService: EspecieService,
      private razaService: RazaService,
      private mascotaService : MascotaService,
      private usuarioService : UsuarioService,
  ) {}

  ngOnInit(): void {
    this.getEspecies();
  }

  getEspecies() {
    this.especieService.getEspecies().subscribe(
        (data: Especie[]) => {
          this.especies = data;
        },
        (error) => {
          console.error('Error al cargar las especies:', error);
        }
    );
  }

  onEspecieChange(selectedEspecie: Especie | null) {
    if (!selectedEspecie) {
      this.razaOptions = [];
      this.selectedRaza = null;
      this.mascota.raza = { idRaza: 0, nombre: "", especie: { idEspecie: 0, nombre: "" } };
      return;
    }

    this.mascota.raza.especie = selectedEspecie;

    const idEspecie = selectedEspecie.idEspecie;

    this.razaOptions = [];
    this.selectedRaza = null;

    this.getRazasPorEspecie(idEspecie);
  }

  onRazaChange(selectedRaza: Raza | null) {
    if (!selectedRaza) {
      this.mascota.raza = { idRaza: 0, nombre: "", especie: this.mascota.raza.especie };
      return;
    }

    this.mascota.raza = selectedRaza;
  }

  getRazasPorEspecie(idEspecie: number) {
    this.razaService.getRazas().subscribe(
        (data: Raza[]) => {

          this.razaOptions = data.filter((raza) => raza.especie && raza.especie.idEspecie === idEspecie);
        },
        (error) => {
          console.error('Error al cargar las razas:', error);
        }
    );
  }

  goBack() {
    window.history.back();
  }

  onSubmit() {
    if (!this.isUsuarioValido() || !this.isMascotaValida()) {
      console.log('Faltan datos obligatorios para el usuario o la mascota. No se puede crear.');
        this.errorMensaje = 'Faltan datos obligatorios para el usuario o la mascota. No se puede crear.';
      return;
    }

    this.usuarioService.crearUsuario(this.usuario).subscribe(
        (usuarioCreado) => {
          console.log('Usuario creado:', usuarioCreado);
          this.mascota.usuario = usuarioCreado.idUsuario;

          this.mascotaService.crearMascota(this.mascota).subscribe(
              (mascotaCreada) => {
                console.log('Mascota creada y asociada al cliente:', mascotaCreada);
                this.goBack();
              },
              (error) => {
                console.error('Error al crear la mascota:', error);
              }
          );
        },
        (error) => {
          console.error('Error al crear el cliente:', error);
        }
    );
  }

  isUsuarioValido(): boolean {
    return (
        this.usuario.nombre.trim() !== '' &&
        this.usuario.apellidos.trim() !== '' &&
        this.usuario.dni.trim() !== '' &&
        this.usuario.email.trim() !== '' &&
        this.usuario.telefono.trim() !== '' &&
        this.usuario.direccion.trim() !== '' &&
        this.usuario.poblacion.trim() !== '' &&
        this.usuario.provincia.trim() !== '' &&
        this.usuario.codigoPostal.trim() !== ''
    );
  }

  isMascotaValida(): boolean {
    return (
        this.mascota.nombre.trim() !== '' &&
        this.mascota.edad !== null &&
        this.mascota.peso !== null &&
        this.mascota.genero.trim() !== '' &&
        this.mascota.complexion.trim() !== '' &&
        this.mascota.raza.idRaza !== 0 &&
        this.mascota.raza.especie.idEspecie !== 0 &&
        this.mascota.fechaNacimiento.trim() !== ''

    );
  }


}



import { Component } from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nuevo-veterinario',
  templateUrl: './nuevo-veterinario.component.html',
  styleUrl: './nuevo-veterinario.component.css'
})
export class NuevoVeterinarioComponent {
  usuario: Usuario = {
    idUsuario: 0,
    nombre: '',
    apellidos: '',
    dni: '',
    email: '',
    password: '',
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
    rol: 2,
    activo: 1
  };

  constructor(private usuarioService: UsuarioService, private route : Router) { }

  crearVeterinario() {
    console.log('Creando veterinario:', this.usuario);
    this.usuarioService.crearUsuario(this.usuario).subscribe(
      (response) => {
        console.log('Usuario creado:', response);
        alert('Veterinario creado correctamente');
        this.route.navigate(['/usuario/inicio-administrador']);
      },
      (error) => {
        console.error('Error al crear el usuario:', error);
      }
    );
  }

  goBack(){
    this.route.navigate(['/usuario/inicio-administrador']);
  }
}

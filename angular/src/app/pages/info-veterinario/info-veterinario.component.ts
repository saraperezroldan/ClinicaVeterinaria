import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Mascota} from "../../models/mascota.model";

@Component({
  selector: 'app-info-veterinario',
  templateUrl: './info-veterinario.component.html',
  styleUrl: './info-veterinario.component.css'
})
export class InfoVeterinarioComponent implements OnInit{

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
    rol: 2,
    activo: 1
  };

  veterinario! : Usuario;
  idUsuario! : number;

  constructor(private usuarioService: UsuarioService, private route : ActivatedRoute, private ruta : Router) { }

  ngOnInit(): void {
    this.idUsuario = this.route.snapshot.params['idUsuario'];


    this.usuarioService.getUsuarioById(this.idUsuario).subscribe(
      (data: Usuario) => {
        this.usuario = data;
      },
      (error) => {
        console.error("Error al cargar los datos del veterinario:", error);
      }
    );
  }

  editarVeterinario() {
    this.usuarioService.editarUsuario(this.usuario).subscribe(
      () => {
        alert("Veterinario actualizado con éxito");

      },
      (error) => {
        console.error("Error al actualizar el veterinario:", error);
      }
    );
  }

  goBack(){
    this.ruta.navigate(['/usuario/inicio-administrador']);
  }



}

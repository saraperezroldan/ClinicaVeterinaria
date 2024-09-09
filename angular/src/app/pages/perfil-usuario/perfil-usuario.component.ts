import { Component } from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent {

  public usuario : Usuario = {idUsuario: 0, nombre: "", apellidos: "",dni:"", email: "", telefono: "", password:"", direccion: "",poblacion:"", provincia: "", codigoPostal: "", imagen: "", fechaAlta: "", fechaModificacion: "", fechaNacimiento: "", fechaBaja: "", rol: {idRol: 0, nombre: ""}};

  constructor(public usuarioService : UsuarioService) { }

  ngOnInit( ): void {
    this.getInfoUsuario();
  }

  getInfoUsuario(){
    this.usuarioService.getInfoUsuario().subscribe( usuario => {
      this.usuario = usuario;
    });
  }

}

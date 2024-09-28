import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent implements OnInit{

  usuario : Usuario | undefined;
  constructor(private usuarioService : UsuarioService, private  route : ActivatedRoute) { }

  ngOnInit( ): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.usuario = JSON.parse(usuarioJSON);
    }
  }


}

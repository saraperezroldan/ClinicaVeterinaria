import { Component } from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent {

  usuario : Usuario | undefined;
  constructor(private usuarioService : UsuarioService, private  route : ActivatedRoute) { }

  ngOnInit( ): void {
    this.getInfoUsuario();
  }

  getInfoUsuario(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.usuarioService.getInfoUsuarioById(id).subscribe( usuario => {
      this.usuario = usuario;
    });
  }

}

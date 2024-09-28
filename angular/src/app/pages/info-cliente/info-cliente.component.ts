import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-info-cliente',
  templateUrl: './info-cliente.component.html',
  styleUrl: './info-cliente.component.css'
})
export class InfoClienteComponent implements OnInit {

  cliente : Usuario | undefined;

  constructor( private usuarioService : UsuarioService, private route : ActivatedRoute) {}

  ngOnInit( ): void {
    const id = this.route.snapshot.params['idUsuario'];
    this.usuarioService.getUsuarioById(id).subscribe(
      (data) => {
        this.cliente = data;
      },
      (error) => {
        console.error('Error al buscar cliente', error);
      }
    );
  }

}

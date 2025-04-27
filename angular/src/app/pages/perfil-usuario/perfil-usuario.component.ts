import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ProximosTratamientosComponent} from "../../shared/proximos-tratamientos/proximos-tratamientos.component";
import {MatDialog} from "@angular/material/dialog";
import {CambiarPasswordComponent} from "../../shared/cambiar-password/cambiar-password.component";
import {CambiarFotoComponent} from "../../shared/cambiar-foto/cambiar-foto.component";

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent implements OnInit{

  usuario : Usuario | undefined;
  constructor(private usuarioService : UsuarioService,
              private router: Router,
              private  route : ActivatedRoute,
              public dialog : MatDialog) { }

  ngOnInit( ): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.usuario = JSON.parse(usuarioJSON);
    }
  }

  cambiarPassword(){
    const dialogRef = this.dialog.open(CambiarPasswordComponent, {
      data: {usuario: this.usuario}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  cambiarFoto() {
    const dialogRef = this.dialog.open(CambiarFotoComponent, {
      data: { usuario: this.usuario }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.usuario = result;
        localStorage.setItem('currentUser', JSON.stringify(this.usuario));
      }
    });
  }



}

import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-inicio-usuario',
  templateUrl: './inicio-usuario.component.html',
  styleUrl: './inicio-usuario.component.css'
})
export class InicioUsuarioComponent implements OnInit{

  usuario : Usuario | undefined;

  ngOnInit( ): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.usuario = JSON.parse(usuarioJSON);
    }
  }


}

import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";

@Component({
  selector: 'app-inicio-usuario',
  templateUrl: './inicio-usuario.component.html',
  styleUrl: './inicio-usuario.component.css'
})
export class InicioUsuarioComponent implements OnInit{

  usuario! : Usuario;
  mascotas : Mascota[] = [];

  constructor(private mascotaService : MascotaService) {}

  ngOnInit( ): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.usuario = JSON.parse(usuarioJSON);
      this.mascotaService.getMascotasByUsuarioId(this.usuario.idUsuario).subscribe(
        (mascotas) => {
          this.mascotas = mascotas;
        }
      );
    }
  }


}

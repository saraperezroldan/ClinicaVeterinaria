import { Component } from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute} from "@angular/router";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";

@Component({
  selector: 'app-info-mascota',
  templateUrl: './info-mascota.component.html',
  styleUrl: './info-mascota.component.css'
})
export class InfoMascotaComponent {

  mascota : Mascota | undefined;
  constructor(private mascotaService : MascotaService, private  route : ActivatedRoute) { }

  ngOnInit( ): void {
    this.getInfoMascota();
  }

  getInfoMascota(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.mascotaService.getInfoMascotaById(id).subscribe( mascota => {
      this.mascota = mascota;
    });
  }

}

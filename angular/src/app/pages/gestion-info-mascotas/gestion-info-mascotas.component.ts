import {Component, OnInit} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-gestion-info-mascotas',
  templateUrl: './gestion-info-mascotas.component.html',
  styleUrl: './gestion-info-mascotas.component.css'
})
export class GestionInfoMascotasComponent implements OnInit{

  mascota! : Mascota;
  idMascota! : number;
  constructor(private mascotaService : MascotaService, private  route : ActivatedRoute, private ruta: Router) { }

  ngOnInit( ): void {
    this.idMascota = this.route.snapshot.params['idMascota'];
    if (this.idMascota) {
      this.getInfoMascota(this.idMascota);
    }
  }

  getInfoMascota(idMascota: number){
    this.mascotaService.getInfoMascotaById(idMascota).subscribe(
        (mascota: Mascota) => {
          this.mascota = mascota;
        }
    );
  }

  goBack(){
    window.history.back();
  }

}

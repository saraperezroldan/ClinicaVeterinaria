import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";

@Component({
  selector: 'app-info-mascota',
  templateUrl: './info-mascota.component.html',
  styleUrl: './info-mascota.component.css'
})
export class InfoMascotaComponent implements OnInit{

  mascota! : Mascota;
  idMascota! : number;
  constructor(private mascotaService : MascotaService, private  route : ActivatedRoute) { }

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

}

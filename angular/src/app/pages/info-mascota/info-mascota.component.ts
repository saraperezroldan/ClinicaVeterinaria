import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {
  ConfirmDeleteTratamientoComponent
} from "../../shared/confirm-delete-tratamiento/confirm-delete-tratamiento.component";
import {ProximosTratamientosComponent} from "../../shared/proximos-tratamientos/proximos-tratamientos.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-info-mascota',
  templateUrl: './info-mascota.component.html',
  styleUrl: './info-mascota.component.css'
})
export class InfoMascotaComponent implements OnInit{

  mascota! : Mascota;
  idMascota! : number;
  constructor(private mascotaService : MascotaService,
              private  route : ActivatedRoute,
              private ruta: Router,
              public dialog : MatDialog) { }

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

  verTratamientos(){
    const dialogRef = this.dialog.open(ProximosTratamientosComponent, {
      data: {idMascota: this.idMascota}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  goBack(){
    this.ruta.navigate(['/usuario/inicio-usuario']);
  }

}

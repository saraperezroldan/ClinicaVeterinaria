import {Component, OnInit} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ActivatedRoute, Router} from "@angular/router";
import {resetParseTemplateAsSourceFileForTest} from "@angular/compiler-cli/src/ngtsc/typecheck/diagnostics";

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
          if (this.mascota.fechaAlta) {
            this.mascota.fechaAlta = this.extractDate(this.mascota.fechaAlta);
          }
          if (this.mascota.fechaNacimiento) {
            this.mascota.fechaNacimiento = this.extractDate(this.mascota.fechaNacimiento);
          }
          console.log(this.mascota);
          console.log(this.mascota.complexion);
        }
    );
  }

  extractDate(isoDate: string): string {
    return isoDate.split('T')[0]; // Retorna solo la parte de la fecha
  }

  goBack(){
    window.history.back();
  }

  modificarMascota(){
    this.mascotaService.editarMascota(this.mascota).subscribe(
        () => {
          alert("Mascota actualizada con éxito");

        },
        (error) => {
          console.error("Error al actualizar la mascota:", error);
        }
    );
  }

}

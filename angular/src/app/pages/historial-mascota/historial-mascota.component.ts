import {Component, OnInit} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {MatTableDataSource} from "@angular/material/table";
import {TratamientoService} from "../../services/tratamiento.service";
import {forkJoin, map, switchMap} from "rxjs";

@Component({
  selector: 'app-historial-mascota',
  templateUrl: './historial-mascota.component.html',
  styleUrl: './historial-mascota.component.css'
})
export class HistorialMascotaComponent implements OnInit{

  mascota! : Mascota;
  idMascota! : number;

  displayedColumns: string[] = ['fechaConsulta', 'veterinario', 'motivo', 'diagnostico', 'tratamiento', 'detalles'];
  dataSource = new MatTableDataSource<any>([]);

  constructor(private mascotaService : MascotaService,
              private consultaService : ConsultaService,
              private tratamientoService : TratamientoService,
              private  route : ActivatedRoute,
              private ruta: Router) { }

  ngOnInit( ): void {
    this.idMascota = this.route.snapshot.params['idMascota'];
    if (this.idMascota) {
      this.getInfoMascota(this.idMascota);
      this.cargarConsultas();
    }
  }

  getInfoMascota(idMascota: number){
    this.mascotaService.getInfoMascotaById(idMascota).subscribe(
        (mascota: Mascota) => {
          this.mascota = mascota;
        }
    );
  }

  cargarConsultas(): void {
      this.consultaService.getConsultasByMascota(this.idMascota).subscribe(
            (consultas: any) => {
                this.dataSource.data = consultas;
            }
      )
  }



}

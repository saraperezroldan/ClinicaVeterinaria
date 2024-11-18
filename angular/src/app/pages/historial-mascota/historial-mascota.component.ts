import {Component, OnInit} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {MatTableDataSource} from "@angular/material/table";
import {TratamientoService} from "../../services/tratamiento.service";
import {catchError, forkJoin, map, of, switchMap} from "rxjs";
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {Consulta} from "../../models/consulta.model";
import {Tratamiento} from "../../models/tratamiento.model";

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
              private usuarioService : UsuarioService,
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
        this.consultaService.getConsultasByMascota(this.idMascota).pipe(
            switchMap((consultas: Consulta[]) => {
                const consultaObservables = consultas.map((consulta: Consulta) =>
                    this.usuarioService.getUsuarioById(consulta.idVeterinario).pipe(
                        switchMap((veterinario: Usuario) =>
                            this.tratamientoService.getTratamientosByIdConsulta(consulta.idConsulta).pipe(
                                switchMap((tratamientos: Tratamiento[]) => {
                                    if (tratamientos.length === 0) {
                                        return [{ ...consulta, nombreVeterinario: veterinario.nombre, tratamientos: 'Sin tratamiento' }];
                                    }

                                    const tratamientoObservables = tratamientos.map((tratamiento: Tratamiento) =>
                                        this.tratamientoService.getTratamientoById(tratamiento.idTratamiento).pipe(
                                            map((tratamientoInfo: Tratamiento) => tratamientoInfo.nombre)
                                        )
                                    );

                                    return forkJoin(tratamientoObservables).pipe(
                                        map((nombresTratamientos: string[]) => ({
                                            ...consulta,
                                            nombreVeterinario: veterinario.nombre,
                                            tratamientos: nombresTratamientos.join(', ')
                                        }))
                                    );
                                })
                            )
                        )
                    )
                );
                return forkJoin(consultaObservables);
            })
        ).subscribe(
            (consultasConVeterinarioYTratamiento) => {
                console.log('Consultas con veterinario y tratamiento:', consultasConVeterinarioYTratamiento);
                this.dataSource.data = consultasConVeterinarioYTratamiento;
            }
        );
    }


  goBack(){
      window.history.back();
  }
}

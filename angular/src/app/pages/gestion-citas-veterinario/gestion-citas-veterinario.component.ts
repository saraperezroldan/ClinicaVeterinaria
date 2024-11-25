import { Component } from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MatTableDataSource} from "@angular/material/table";
import {MascotaService} from "../../services/mascota.service";
import {ConsultaService} from "../../services/consulta.service";
import {TratamientoService} from "../../services/tratamiento.service";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {forkJoin, map, switchMap} from "rxjs";
import {Consulta} from "../../models/consulta.model";
import {Usuario} from "../../models/usuario.model";
import {Tratamiento} from "../../models/tratamiento.model";

@Component({
  selector: 'app-gestion-citas-veterinario',
  templateUrl: './gestion-citas-veterinario.component.html',
  styleUrl: './gestion-citas-veterinario.component.css'
})
export class GestionCitasVeterinarioComponent {

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

  eliminarConsulta(idConsulta: number){}


  goBack(){
    window.history.back();
  }

}

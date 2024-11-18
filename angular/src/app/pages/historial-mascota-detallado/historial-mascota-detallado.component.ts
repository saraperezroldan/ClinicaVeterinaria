import {Component, OnInit} from '@angular/core';
import {map, switchMap} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {TratamientoService} from "../../services/tratamiento.service";
import {MascotaService} from "../../services/mascota.service";
import {Tratamiento} from "../../models/tratamiento.model";

@Component({
  selector: 'app-historial-mascota-detallado',
  templateUrl: './historial-mascota-detallado.component.html',
  styleUrl: './historial-mascota-detallado.component.css'
})
export class HistorialMascotaDetalladoComponent implements OnInit{

  consulta: any = null;
  tratamientos: Tratamiento[] = [];
  total: number = 0;
  nombreMascota: string = '';
  nombreVeterinario: string = '';

  constructor(
      private route: ActivatedRoute,
      private consultaService: ConsultaService,
      private usuarioService: UsuarioService,
      private tratamientoService: TratamientoService,
      private mascotaService: MascotaService
  ) {}

  ngOnInit(): void {
    const idConsulta = Number(this.route.snapshot.paramMap.get('idConsulta'));
    if (!isNaN(idConsulta)) {
      this.cargarConsultaDetallada(idConsulta);
    } else {
      console.error('El idConsulta no es válido.');
    }
  }

  cargarConsultaDetallada(idConsulta: number): void {
    this.consultaService.getConsultaById(idConsulta).subscribe(consulta => {
      this.consulta = consulta;
      console.log(consulta);

      this.mascotaService.getInfoMascotaById(consulta.mascota).subscribe(mascota => {
        console.log(mascota)
        this.nombreMascota = mascota.nombre;
      });

      this.usuarioService.getUsuarioById(consulta.idVeterinario).subscribe(veterinario => {
        this.nombreVeterinario = veterinario.nombre;
      });

      this.tratamientoService.getTratamientosByIdConsulta(idConsulta).subscribe(tratamientosIds => {
        this.tratamientos = [];
        this.total = 0;
        console.log(tratamientosIds);

        tratamientosIds.forEach((tratamiento:any) => {
          const tratamientoId = tratamiento.idTratamiento;
          this.tratamientoService.getTratamientoById(tratamientoId).subscribe(tratamiento => {
            this.tratamientos.push(tratamiento);
            this.total += tratamiento.precio;
          });
        });
      });
    });
  }

  goBack(){
    window.history.back();
  }

}

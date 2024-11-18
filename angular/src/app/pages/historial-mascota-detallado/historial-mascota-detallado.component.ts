import {Component, OnInit} from '@angular/core';
import {map, switchMap} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {TratamientoService} from "../../services/tratamiento.service";

@Component({
  selector: 'app-historial-mascota-detallado',
  templateUrl: './historial-mascota-detallado.component.html',
  styleUrl: './historial-mascota-detallado.component.css'
})
export class HistorialMascotaDetalladoComponent implements OnInit{

  consulta: any = null; // Guardará la información completa de la consulta
  tratamientos: any[] = []; // Lista de tratamientos asociados
  total: number = 0; // Costo total de la consulta y tratamientos

  constructor(
      private route: ActivatedRoute,
      private consultaService: ConsultaService,
      private usuarioService: UsuarioService,
      private tratamientoService: TratamientoService
  ) {}

  ngOnInit(): void {
    // Convertir idConsulta a número
    const idConsulta = Number(this.route.snapshot.paramMap.get('idConsulta'));
    if (!isNaN(idConsulta)) {
      this.cargarConsultaDetallada(idConsulta);
    } else {
      console.error('El idConsulta no es válido.');
    }
  }

  cargarConsultaDetallada(idConsulta: number): void {
    this.consultaService.getConsultaById(idConsulta).pipe(
        switchMap((consulta: any) =>
            this.usuarioService.getUsuarioById(consulta.idVeterinario).pipe(
                switchMap((veterinario: any) =>
                    this.tratamientoService.getTratamientosByIdConsulta(consulta.idConsulta).pipe(
                        map((tratamientos: any[]) => {
                          // Calcular el costo total (tratamientos + consulta)
                          const totalTratamientos = tratamientos.reduce((sum, t) => sum + t.precio, 0);
                          this.total = totalTratamientos + consulta.precioConsulta;

                          // Preparar los datos de tratamientos con iconos y precios
                          this.tratamientos = tratamientos.map(t => ({
                            nombre: t.nombre,
                            precio: t.precio,
                            icono: t.icono // Asegúrate de que el backend devuelva este campo
                          }));

                          // Devuelve la consulta con el nombre del veterinario agregado
                          return { ...consulta, nombreVeterinario: veterinario.nombre };
                        })
                    )
                )
            )
        )
    ).subscribe(
        (consultaConVeterinario: any) => {
          this.consulta = consultaConVeterinario; // Asignar la consulta para mostrarla en el HTML
        },
        error => console.error('Error cargando consulta:', error)
    );
  }

  goBack(){
    window.history.back();
  }

}

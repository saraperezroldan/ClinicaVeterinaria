import {Component, ViewChild} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {Usuario} from "../../models/usuario.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MascotaService} from "../../services/mascota.service";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {forkJoin, map, Observable} from "rxjs";
import {Consulta} from "../../models/consulta.model";

@Component({
  selector: 'app-gestion-consultas-administrador',
  templateUrl: './gestion-consultas-administrador.component.html',
  styleUrl: './gestion-consultas-administrador.component.css'
})
export class GestionConsultasAdministradorComponent {

  mascota! : Mascota;
  idMascota! : number;
  idVeterinario! : number;
  currentUser! : Usuario;

  displayedColumns: string[] = ['idConsulta', 'idMascota', 'nombreMascota', 'especieMascota', 'fechaConsulta', 'horaConsulta', 'motivo', 'nombreVeterinario', 'acciones'];
  dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalItems = 0;
  pageSize = 5;
  pageIndex = 0;

  constructor(private mascotaService : MascotaService,
              private consultaService : ConsultaService,
              private usuarioService : UsuarioService) { }

  ngOnInit( ): void {
    this.idVeterinario = 0;
    this.obtenerCitas();
  }

  obtenerCitas(): void {
    this.consultaService.getConsultasByVeterinario(this.idVeterinario, this.pageIndex, this.pageSize).subscribe({
      next: (response) => {
        console.log(response);
        this.totalItems = response.count;
        const citas = response.results;

        const observables: Observable<any>[] = citas.map((cita: Consulta) => {
          const mascotaObservable = this.mascotaService.getInfoMascotaById(cita.mascota).pipe(
              map((mascota: Mascota) => ({
                ...cita,
                idMascota: mascota?.idMascota,
                nombreMascota: mascota?.nombre,
                especieMascota: mascota?.raza?.especie?.nombre
              }))
          );

          const veterinarioObservable = this.usuarioService.getUsuarioById(cita.idVeterinario).pipe(
              map((veterinario: Usuario) => ({
                ...cita,
                nombreVeterinario: `${veterinario.nombre} ${veterinario.apellidos}`
              }))
          );

          return forkJoin([mascotaObservable, veterinarioObservable]).pipe(
              map(([citaConMascota, citaConVeterinario]) => ({
                ...citaConMascota,
                nombreVeterinario: citaConVeterinario.nombreVeterinario
              }))
          );
        });

        forkJoin(observables).subscribe({
          next: (result) => {
            this.dataSource.data = result;
            if (this.paginator) {
              this.paginator.pageIndex = this.pageIndex;
              this.paginator.pageSize = this.pageSize;
            }
          },
          error: (err) => {
            console.error('Error al obtener detalles de mascotas o veterinarios:', err);
          }
        });
      },
      error: (err) => {
        console.error('Error al obtener citas:', err);
      }
    });
  }


  eliminarConsulta(idConsulta: number): void {
    console.log(`Eliminar consulta con ID: ${idConsulta}`);
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.obtenerCitas();
  }

  goBack(){
    window.history.back();
  }

}

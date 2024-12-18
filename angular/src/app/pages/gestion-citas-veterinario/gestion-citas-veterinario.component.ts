import {Component, OnInit, ViewChild} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MatTableDataSource} from "@angular/material/table";
import {MascotaService} from "../../services/mascota.service";
import {ConsultaService} from "../../services/consulta.service";
import {TratamientoService} from "../../services/tratamiento.service";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {forkJoin, map, Observable, switchMap} from "rxjs";
import {Consulta} from "../../models/consulta.model";
import {Usuario} from "../../models/usuario.model";
import {Tratamiento} from "../../models/tratamiento.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {ConfirmDeleteCitaComponent} from "../../shared/confirm-delete-cita/confirm-delete-cita.component";
import {MatDialog} from "@angular/material/dialog";


@Component({
  selector: 'app-gestion-citas-veterinario',
  templateUrl: './gestion-citas-veterinario.component.html',
  styleUrl: './gestion-citas-veterinario.component.css'
})

export class GestionCitasVeterinarioComponent implements OnInit{

  mascota! : Mascota;
  idMascota! : number;
  idVeterinario! : number;
  currentUser! : Usuario;

  displayedColumns: string[] = ['idConsulta', 'idMascota', 'nombreMascota', 'especieMascota', 'fechaConsulta', 'horaConsulta', 'motivo', 'acciones'];
  dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalItems = 0;
  pageSize = 5;
  pageIndex = 0;

  constructor(private mascotaService : MascotaService,
              private consultaService : ConsultaService,
              private usuarioService : UsuarioService,
              public dialog : MatDialog,
              private router : Router) { }

  ngOnInit( ): void {
    this.currentUser = this.usuarioService.getCurrentUser();
    this.idVeterinario = this.currentUser.idUsuario;
    this.obtenerCitas();
  }

    obtenerCitas(): void {
        this.consultaService.getCitasByVeterinario(this.idVeterinario, this.pageIndex, this.pageSize).subscribe({
            next: (response) => {
              console.log(response);
                this.totalItems = response.count;
                const citas = response.results;

                const observables: Observable<any>[] = citas.map((cita: Consulta) =>
                    this.mascotaService.getInfoMascotaById(cita.mascota).pipe(
                        map((mascota: Mascota) => ({
                            ...cita,
                            idMascota: mascota?.idMascota,
                            nombreMascota: mascota?.nombre,
                            especieMascota: mascota?.raza?.especie?.nombre
                        }))
                    )
                );

                forkJoin(observables).subscribe({
                    next: (result) => {
                        this.dataSource.data = result;
                        if (this.paginator) {
                            this.paginator.pageIndex = this.pageIndex;
                            this.paginator.pageSize = this.pageSize;
                        }
                    },
                    error: (err) => {
                        console.error('Error al obtener detalles de mascotas:', err);
                    }
                });
            },
            error: (err) => {
                console.error('Error al obtener citas:', err);
            }
        });
    }


  eliminarCita(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDeleteCitaComponent, {});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.consultaService.eliminarCita(id).subscribe(
          response => {
            console.log('Cita eliminada:', response);
            alert('La cita se ha eliminado correctamente');
            this.ngOnInit();
          },
          error => {
            console.error('Error al eliminar la cita:', error);
          }
        );
      }
    });
  }

    onPageChange(event: PageEvent): void {
        this.pageIndex = event.pageIndex;
        this.pageSize = event.pageSize;
        this.obtenerCitas();
    }

    editarCita(id: number): void {
        this.router.navigate(['/usuario/editar-cita', id]);
    }


}

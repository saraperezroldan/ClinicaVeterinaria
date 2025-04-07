import {Component, OnInit, ViewChild} from '@angular/core';
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
import {TratamientoService} from "../../services/tratamiento.service";
import {ConfirmDeleteMascotaComponent} from "../../shared/confirm-delete-mascota/confirm-delete-mascota.component";
import {
  ConfirmDeleteTratamientoComponent
} from "../../shared/confirm-delete-tratamiento/confirm-delete-tratamiento.component";
import {MatDialog} from "@angular/material/dialog";
import {NuevoTratamientoComponent} from "../../shared/nuevo-tratamiento/nuevo-tratamiento.component";

@Component({
  selector: 'app-gestion-tratamientos',
  templateUrl: './gestion-tratamientos.component.html',
  styleUrl: './gestion-tratamientos.component.css'
})
export class GestionTratamientosComponent implements OnInit{

  displayedColumns: string[] = ['idTratamiento', 'nombreTratamiento', 'precio', 'stock', 'aviso', 'acciones'];
  dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalItems = 0;
  pageSize = 5;
  pageIndex = 0;

  constructor(private tratamientoService : TratamientoService,
              public dialog : MatDialog) { }

  ngOnInit(): void {
    this.obtenerTratamientos(this.pageSize, this.pageIndex);
  }

  obtenerTratamientos(pageElements:number, pageNumber:number): void {

    this.tratamientoService.getTratamientosConFiltro(pageElements, pageNumber)
        .subscribe({
          next: (response) => {
            this.dataSource.data = response.results;
            this.totalItems = response.count;
          },
          error: (error) => {
            console.error('Error al cargar los tratamientos:', error);
          }
        });
  }
  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.obtenerTratamientos(this.pageSize, this.pageIndex);
  }

  eliminarTratamiento(idTratamiento: number): void {
    const dialogRef = this.dialog.open(ConfirmDeleteTratamientoComponent, { });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.tratamientoService.eliminarTratamiento(idTratamiento).subscribe(
            response => {
              console.log('Tratamiento eliminado:', response);
              alert('El tratamiento ha sido eliminado correctamente');
              this.ngOnInit();
            },
            error => {
              console.error('Error al eliminar el tratamiento:', error);
            }
        );
      }
    });
  }

  nuevoTratamiento(): void {
    const dialogRef = this.dialog.open(NuevoTratamientoComponent, { });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ngOnInit();
      }
    });
  }
}

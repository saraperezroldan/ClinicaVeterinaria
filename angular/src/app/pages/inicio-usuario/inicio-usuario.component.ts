import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ConsultaService} from "../../services/consulta.service";
import {
  ConfirmDeleteVeterinarioComponent
} from "../../shared/confirm-delete-veterinario/confirm-delete-veterinario.component";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeleteCitaComponent} from "../../shared/confirm-delete-cita/confirm-delete-cita.component";

@Component({
  selector: 'app-inicio-usuario',
  templateUrl: './inicio-usuario.component.html',
  styleUrl: './inicio-usuario.component.css'
})
export class InicioUsuarioComponent implements OnInit{

  usuario! : Usuario;
  mascotas : Mascota[] = [];

  constructor(private mascotaService : MascotaService,
              private consultaService : ConsultaService,
              public dialog : MatDialog) {}

  ngOnInit(): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if (usuarioJSON) {
      this.usuario = JSON.parse(usuarioJSON);

      this.mascotaService.getMascotasByUsuarioId(this.usuario.idUsuario).subscribe((mascotas) => {
        this.mascotas = mascotas.filter(mascota => mascota.activo === 1);

        this.mascotas.forEach(mascota => {
          this.consultaService.getCitasByIdMascota(mascota.idMascota).subscribe((citas) => {
            console.log(citas);
            mascota.citas = citas;
          });
        });
      });
    }
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
}

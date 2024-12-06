import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Mascota} from "../../models/mascota.model";
import {Consulta} from "../../models/consulta.model";
import {ConsultaService} from "../../services/consulta.service";
import {ConfirmDeleteCitaComponent} from "../../shared/confirm-delete-cita/confirm-delete-cita.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-info-veterinario',
  templateUrl: './info-veterinario.component.html',
  styleUrl: './info-veterinario.component.css'
})
export class InfoVeterinarioComponent implements OnInit{

  usuario! : Usuario;
  idUsuario! : number;
  citas! : Consulta[];

  constructor(private usuarioService: UsuarioService,
              private consultaService: ConsultaService,
              private route : ActivatedRoute,
              private ruta : Router,
              public dialog : MatDialog) { }

  ngOnInit(): void {
    this.idUsuario = this.route.snapshot.params['idUsuario'];


    this.usuarioService.getUsuarioById(this.idUsuario).subscribe(
      (data: Usuario) => {
        this.usuario = data;
      },
      (error) => {
        console.error("Error al cargar los datos del veterinario:", error);
      }
    );

    this.consultaService.getCitasByIdVeterinario(this.idUsuario).subscribe(
      (data: Consulta[]) => {
        this.citas = data.slice(0, 3);
      },
      (error) => {
        console.error("Error al cargar las citas del veterinario:", error);
      }
    );
  }

  editarVeterinario() {
    this.usuarioService.editarUsuario(this.usuario).subscribe(
      () => {
        alert("Veterinario actualizado con éxito");

      },
      (error) => {
        console.error("Error al actualizar el veterinario:", error);
      }
    );
  }

  goBack(){
    this.ruta.navigate(['/usuario/inicio-administrador']);
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

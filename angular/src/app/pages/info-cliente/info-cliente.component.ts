import {Component, OnInit} from '@angular/core';
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MascotaService} from "../../services/mascota.service";
import {MatTableDataSource} from "@angular/material/table";
import {Mascota} from "../../models/mascota.model";

@Component({
  selector: 'app-info-cliente',
  templateUrl: './info-cliente.component.html',
  styleUrl: './info-cliente.component.css'
})
export class InfoClienteComponent implements OnInit {

  cliente : Usuario | undefined;
  displayedColumns: string[] = ['idMascota','nombre', 'especie', 'raza', 'fechaAlta', 'acciones'];
  dataSource: MatTableDataSource<Mascota> = new MatTableDataSource<Mascota>([]);

  constructor( private usuarioService : UsuarioService, private mascotaService : MascotaService,  private route : ActivatedRoute, private router : Router) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['idUsuario'];

    this.usuarioService.getUsuarioById(id).subscribe(
      (data) => {
        this.cliente = data;

        if (this.cliente && this.cliente.idUsuario) {
          this.mascotaService.getMascotasByUsuarioId(this.cliente.idUsuario).subscribe(
            (mascotas) => {
              this.dataSource.data = mascotas; // Asigna las mascotas a la tabla
            },
            (error) => {
              console.error('Error al buscar mascotas', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error al buscar cliente', error);
      }
    );
  }

  nuevaMascota(): void {
    this.router.navigate(['/usuario/nueva-mascota']);
  }


}

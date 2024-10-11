import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {Usuario} from "../../models/usuario.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeleteClienteComponent} from "../../shared/confirm-delete/confirm-delete-cliente.component";

@Component({
  selector: 'app-inicio-veterinario',
  templateUrl: './inicio-veterinario.component.html',
  styleUrl: './inicio-veterinario.component.css'
})
export class InicioVeterinarioComponent implements OnInit{

  usuario : Usuario | undefined;
  veterinario: Usuario | undefined;
  searchDNI: string = '';
  errorMensaje: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router, public dialog : MatDialog) {}

  public dataSource = new MatTableDataSource<Usuario>();
  public displayedColumns: string[] = ['idUsuario', 'nombre', 'apellidos', 'dni', 'telefono', 'email', 'fechaAlta', 'acciones'];

  ngOnInit( ): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.veterinario = JSON.parse(usuarioJSON);
    }
  }


  buscarUsuario(): void {
    if (this.searchDNI) {
      this.usuarioService.getUsuarioByDNI(this.searchDNI).subscribe(
        (data) => {
          this.usuario = data;
          this.searchDNI = '';
          this.errorMensaje = '';
        },
        (error) => {
          console.error('Error al buscar cliente', error);
          this.errorMensaje = 'No se ha encontrado ningún cliente con ese DNI';
        }
      );
    }
  }

  nuevoUsuario(): void {
    this.router.navigate(['/usuario/nuevo-cliente']);
  }

  eliminarUsuario(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDeleteClienteComponent, { });
  }


}

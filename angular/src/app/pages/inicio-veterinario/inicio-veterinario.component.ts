import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {Usuario} from "../../models/usuario.model";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-inicio-veterinario',
  templateUrl: './inicio-veterinario.component.html',
  styleUrl: './inicio-veterinario.component.css'
})
export class InicioVeterinarioComponent implements OnInit{

  usuario : Usuario | undefined;
  veterinario: Usuario | undefined;
  searchDNI: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

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
        },
        (error) => {
          console.error('Error al buscar cliente', error);
        }
      );
    }
  }

  nuevoUsuario(): void {
    this.router.navigate(['/cliente/nuevo']);
  }

  editarUsuario(id: number): void {
    this.router.navigate([`/cliente/editar/${id}`]);
  }

  eliminarUsuario(id: number): void {
    this.usuarioService.eliminarUsuario(id).subscribe(
      (data) => {
        this.usuario = undefined;
      },
      (error) => {
        console.error('Error al eliminar cliente', error);
      }
    );
  }


}

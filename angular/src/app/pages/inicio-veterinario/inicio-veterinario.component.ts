import { Component } from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-inicio-veterinario',
  templateUrl: './inicio-veterinario.component.html',
  styleUrl: './inicio-veterinario.component.css'
})
export class InicioVeterinarioComponent {

  usuario : Usuario | undefined;
  searchDNI: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}


  buscarUsuario(): void {
    if (this.searchDNI) {
      this.usuarioService.getUsuarioByDNI(this.searchDNI).subscribe(
        (data) => {
          this.usuario = data;
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

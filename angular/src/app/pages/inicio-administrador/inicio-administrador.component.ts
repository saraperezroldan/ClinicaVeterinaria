import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {Router} from "@angular/router";
import {ConfirmDeleteClienteComponent} from "../../shared/confirm-delete-cliente/confirm-delete-cliente.component";
import {
  ConfirmDeleteVeterinarioComponent
} from "../../shared/confirm-delete-veterinario/confirm-delete-veterinario.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-inicio-administrador',
  templateUrl: './inicio-administrador.component.html',
  styleUrl: './inicio-administrador.component.css'
})
export class InicioAdministradorComponent implements OnInit{

  veterinarios : Usuario[] = [];
  veterinariosFiltered : Usuario[] = [];
  searchQuery : string = '';

  constructor(private usuarioService : UsuarioService, private router: Router, public dialog : MatDialog) { }

  ngOnInit() {
    this.getVeterinarios();
  }

  getVeterinarios(){
    this.usuarioService.getUsariosByRol(2).subscribe(
      veterinarios => {
        console.log(veterinarios);
        this.veterinarios = veterinarios;
        this.veterinariosFiltered = veterinarios;
      }
    )
  }

  searchVeterinarios(){
    const query = this.searchQuery.trim().toLowerCase();
    if (query === ''){
      this.veterinariosFiltered = this.veterinarios;
      return;
    }else{
      this.veterinariosFiltered = this.veterinarios.filter(
        veterinario => veterinario.nombre.toLowerCase().includes(query) || veterinario.apellidos.toLowerCase().includes(query)
      )
    }
    this.searchQuery = '';
  }

  nuevoVeterinario(){
    this.router.navigate(['/usuario/nuevo-veterinario']);
  }

  eliminarVeterinario(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDeleteVeterinarioComponent, { });
  }

}

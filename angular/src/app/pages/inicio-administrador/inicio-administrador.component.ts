import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-inicio-administrador',
  templateUrl: './inicio-administrador.component.html',
  styleUrl: './inicio-administrador.component.css'
})
export class InicioAdministradorComponent implements OnInit{

  veterinarios : Usuario[] = [];
  veterinariosFiltered : Usuario[] = [];
  searchQuery : string = '';

  constructor(private usuarioService : UsuarioService, private router: Router) { }

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

}

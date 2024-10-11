import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

export interface Menu{
  label: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-cabecera',
  templateUrl: './cabecera.component.html',
  styleUrl: './cabecera.component.css'
})
export class CabeceraComponent implements OnInit{

  @ViewChild('sidenav') sidenav!: MatSidenav;

  opened = false;
  currentUser: any;

  ususarioMenuOpciones: Menu[] = [
    {label: 'Inicio', icon: 'home', route: '/usuario/inicio-usuario'},
    {label: 'Mi perfil', icon: 'person', route: '/usuario/perfil'},
  ];
  veterinarioMenuOpciones: Menu[] = [
    {label: 'Inicio', icon: 'home', route: '/usuario/inicio-veterinario'},
    {label: 'Gestion citas', icon: 'calendar_month', route: '/usuario/citas'},
    {label: 'Gestion consultas', icon: 'content_paste', route: '/usuario/consultas'},
    {label: 'Mi perfil', icon: 'person', route: '/usuario/perfil'},
  ];
  administradorMenuOpciones: Menu[] = [
    {label: 'Personal', icon: 'person', route: '/usuario/inicio-administrador'},
    {label: 'Gestion citas', icon: 'calendar_month', route: '/usuario/citas'},
    {label: 'Gestion consultas', icon: 'content_paste', route: '/usuario/consultas'},
    {label: 'Tratamientos', icon: 'medical_services', route: '/usuario/tratamientos'},
  ];

  constructor(private usuarioService : UsuarioService, private loginService : LoginService, private router : Router) {}

  ngOnInit(): void {
    this.currentUser = this.usuarioService.getCurrentUser();
    console.log(this.currentUser);
  }

  onLogout(){
    this.loginService.logout();
    this.router.navigate(['']);
  }

  toggleSidenav() {
    this.sidenav.toggle();
  }

  isUsuario(){
    return this.currentUser.rol.nombre === 'Usuario';
  }

  isVeterinario(){
    return this.currentUser.rol.nombre === 'Veterinario';
  }

  isAdministador(){
    return this.currentUser.rol.nombre === 'Administrador';
  }





}

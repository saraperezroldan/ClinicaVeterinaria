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
    {label: 'Inicio', icon: 'home', route: '/inicio'},
    {label: 'Mi perfil', icon: 'person', route: '/perfil'},
  ];
  veterinarioMenuOpciones: Menu[] = [
    {label: 'Inicio', icon: 'home', route: '/inicio'},
    {label: 'Gestion citas', icon: 'person', route: '/perfil'},
    {label: 'Gestion consultas', icon: 'pets', route: '/pacientes'},
    {label: 'Mi perfil', icon: 'event', route: '/citas'},
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





}

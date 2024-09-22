import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

export interface Menu {
  title:          string;
  selected:      boolean;
  icon:           string;
  rol:            string;
  links:         string[];
}

@Component({
  selector: 'app-cabecera',
  templateUrl: './cabecera.component.html',
  styleUrl: './cabecera.component.css'
})
export class CabeceraComponent implements OnInit{

  public urlImage: string = 'http://localhost:4200/assets/images/logo.png';
  public completeName: string = "";
  public unauthorizedMsg: string = "";

  public menuList: Menu[] = [];

  public menuListOptions: Menu[] = [
    {
      title: "Inicio",
      selected: false,
      rol: "ESMAD_TALEND_PROPUESTAS_AFV",
      icon: "shopping_basket",
      links: ["/inicio"],
    },
    {
      title: "Gestion de citas",
      selected: false,
      rol: "ESMAD_TALEND_PROPUESTAS_AFV",
      icon: "shopping_basket",
      links: ["/propuestas","/agrupaciones","/crearPropuesta","/verPropuesta","/verAgrupacion"],
    },
    {
      title: "Gestion de consultas",
      selected: false,
      rol: "ESMAD_TALEND_PROPUESTAS_AFV",
      icon: "shopping_basket",
      links: ["/propuestas","/agrupaciones","/crearPropuesta","/verPropuesta","/verAgrupacion"],
    },
    {
      title: "Mi perfil",
      selected: false,
      rol: "ESMAD_TALEND_PROPUESTAS_AFV",
      icon: "shopping_basket",
      links: ["/perfil"],
    }
  ];


  @ViewChild('sidenav') sidenav!: MatSidenav;

  currentUser: any;

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

  public selectOptionMenu(menuSelected: string){
    this.menuList.forEach(menu => {
      menu.selected = menuSelected == menu.title;
    });

  }


}

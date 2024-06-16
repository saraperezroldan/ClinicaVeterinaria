import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";

export interface Menu {
  title:          string;
  selected:      boolean;
  icon:           string;
  rol:            string;
  links:         string[];
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
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

  toggleSidenav() {
    this.sidenav.toggle();
  }

  public selectOptionMenu(menuSelected: string){
    this.menuList.forEach(menu => {
      menu.selected = menuSelected == menu.title;
    });

  }
}

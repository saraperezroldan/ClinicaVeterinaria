import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nuevo-cliente',
  templateUrl: './nuevo-cliente.component.html',
  styleUrl: './nuevo-cliente.component.css'
})
export class NuevoClienteComponent {

  constructor(private route: Router) {
  }

  goBack(){
    this.route.navigate(['/usuario/inicio-veterinario']);
  }
}

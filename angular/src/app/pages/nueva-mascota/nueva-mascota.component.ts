import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nueva-mascota',
  templateUrl: './nueva-mascota.component.html',
  styleUrl: './nueva-mascota.component.css'
})
export class NuevaMascotaComponent {

  constructor(private route : Router) { }

  goBack(){
    window.history.back();
  }

}

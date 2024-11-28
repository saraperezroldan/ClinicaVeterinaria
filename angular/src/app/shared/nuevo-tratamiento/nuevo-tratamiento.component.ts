import { Component } from '@angular/core';
import {Tratamiento} from "../../models/tratamiento.model";
import {TratamientoService} from "../../services/tratamiento.service";

@Component({
  selector: 'app-nuevo-tratamiento',
  templateUrl: './nuevo-tratamiento.component.html',
  styleUrl: './nuevo-tratamiento.component.css'
})
export class NuevoTratamientoComponent {

  tratamiento! : Tratamiento;

  constructor(private tartamientoService : TratamientoService){}

  onSubmit(){};

}

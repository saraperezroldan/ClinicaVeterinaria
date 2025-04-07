import {Component, ViewEncapsulation} from '@angular/core';
import {Tratamiento} from "../../models/tratamiento.model";
import {TratamientoService} from "../../services/tratamiento.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-nuevo-tratamiento',
  templateUrl: './nuevo-tratamiento.component.html',
  styleUrl: './nuevo-tratamiento.component.css',
  encapsulation: ViewEncapsulation.None
})
export class NuevoTratamientoComponent {

  tratamiento: Tratamiento = {
    idTratamiento: 0,
    nombre: '',
    descripcion: '',
    esVacuna: 0,
    precio: 0,
    stock: 0
  };
  errorMensaje = '';

  constructor(private tartamientoService : TratamientoService,
              public dialogRef: MatDialogRef<NuevoTratamientoComponent>){}

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    if (!this.tratamiento.nombre.trim() || !this.tratamiento.descripcion.trim() || this.tratamiento.precio <= 0) {
      this.errorMensaje = 'Por favor rellene todos los campos correctamente.';
      return;
    }
    this.tartamientoService.crearTratamiento(this.tratamiento).subscribe(
      (response) => {
        alert('Tratamiento creado correctamente');
        this.dialogRef.close(true);
        console.log('Tratamiento creado:', response);
      },
      (error) => {
        this.errorMensaje = 'Hubo un problema al crear el tratamiento. Inténtelo de nuevo';
      }
    );
  }

}

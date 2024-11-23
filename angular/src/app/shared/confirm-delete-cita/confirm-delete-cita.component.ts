import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete-cita',
  templateUrl: './confirm-delete-cita.component.html',
  styleUrl: './confirm-delete-cita.component.css'
})
export class ConfirmDeleteCitaComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteCitaComponent>) { }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    this.dialogRef.close(true);
  }

}

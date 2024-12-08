import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-cita',
  templateUrl: './confirm-cita.component.html',
  styleUrl: './confirm-cita.component.css'
})
export class ConfirmCitaComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmCitaComponent>) { }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    this.dialogRef.close(true);
  }

}

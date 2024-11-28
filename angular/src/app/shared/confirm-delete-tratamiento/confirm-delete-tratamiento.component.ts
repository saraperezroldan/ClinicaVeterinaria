import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete-tratamiento',
  templateUrl: './confirm-delete-tratamiento.component.html',
  styleUrl: './confirm-delete-tratamiento.component.css'
})
export class ConfirmDeleteTratamientoComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteTratamientoComponent>) { }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    this.dialogRef.close(true);
  }

}

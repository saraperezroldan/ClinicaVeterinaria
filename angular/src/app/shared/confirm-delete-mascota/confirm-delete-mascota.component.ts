import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete-mascota',
  templateUrl: './confirm-delete-mascota.component.html',
  styleUrl: './confirm-delete-mascota.component.css'
})
export class ConfirmDeleteMascotaComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteMascotaComponent>) { }

  onClose(){
    this.dialogRef.close();
  }

  onSubmit(){
    this.dialogRef.close();
  }

}

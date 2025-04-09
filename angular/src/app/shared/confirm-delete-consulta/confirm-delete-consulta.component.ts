import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete-consulta',
  templateUrl: './confirm-delete-consulta.component.html',
  styleUrl: './confirm-delete-consulta.component.css'
})
export class ConfirmDeleteConsultaComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteConsultaComponent>) { }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    this.dialogRef.close(true);
  }

}

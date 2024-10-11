import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete-cliente.component.html',
  styleUrl: './confirm-delete-cliente.component.css'
})
export class ConfirmDeleteClienteComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteClienteComponent>) { }

  onClose(){
    this.dialogRef.close();
  }

  onSubmit(){
    this.dialogRef.close();
  }


}

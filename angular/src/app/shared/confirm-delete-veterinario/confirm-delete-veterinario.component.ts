import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete-veterinario',
  templateUrl: './confirm-delete-veterinario.component.html',
  styleUrl: './confirm-delete-veterinario.component.css'
})
export class ConfirmDeleteVeterinarioComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteVeterinarioComponent>) { }

  onClose(){
    this.dialogRef.close();
  }

  onSubmit(){
    this.dialogRef.close();
  }

}

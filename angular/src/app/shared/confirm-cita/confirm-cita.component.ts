import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Consulta} from "../../models/consulta.model";

@Component({
  selector: 'app-confirm-cita',
  templateUrl: './confirm-cita.component.html',
  styleUrl: './confirm-cita.component.css'
})
export class ConfirmCitaComponent {

  cita: Consulta;
  nombreVeterinario: string = '';
  constructor(
    public dialogRef: MatDialogRef<ConfirmCitaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { cita: Consulta; nombreVeterinario: string }
  ) {
    this.cita = data.cita;
    this.nombreVeterinario = data.nombreVeterinario
  }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit(){
    this.dialogRef.close(true);
  }

}

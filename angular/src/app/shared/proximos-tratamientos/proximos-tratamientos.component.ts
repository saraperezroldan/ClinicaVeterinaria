import {Component, Inject, OnInit} from '@angular/core';
import {ConsultaService} from "../../services/consulta.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ConfirmDeleteCitaComponent} from "../confirm-delete-cita/confirm-delete-cita.component";

@Component({
  selector: 'app-proximos-tratamientos',
  templateUrl: './proximos-tratamientos.component.html',
  styleUrl: './proximos-tratamientos.component.css'
})
export class ProximosTratamientosComponent implements OnInit{

  vacunas: any[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: { idMascota: number },
              public consultaService : ConsultaService,
              public dialogRef: MatDialogRef<ProximosTratamientosComponent>) {
  }

  ngOnInit(): void {
    this.getProximosTratamientos();
  }

  getProximosTratamientos(){
    this.consultaService.getVacunasByIdMascota(this.data.idMascota).subscribe((data: any) => {
      this.vacunas = data.slice(0, 5);
    });
  }

  cerrar() {
    this.dialogRef.close();
  }

}

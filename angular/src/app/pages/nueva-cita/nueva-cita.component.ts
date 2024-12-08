import {Component, OnInit} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";
import {ProximosTratamientosComponent} from "../../shared/proximos-tratamientos/proximos-tratamientos.component";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmCitaComponent} from "../../shared/confirm-cita/confirm-cita.component";

@Component({
  selector: 'app-nueva-cita',
  templateUrl: './nueva-cita.component.html',
  styleUrl: './nueva-cita.component.css'
})
export class NuevaCitaComponent implements OnInit{

  veterinarios : Usuario[] = [];
  selectedVeterinario : Usuario | undefined;

  constructor(public usuarioService : UsuarioService,
              public dialog : MatDialog) { }

  calendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    locale: 'es',
    firstDay: 1,
    editable: true,
    selectable: true,
    events: [],
    dateClick: this.handleDateClick.bind(this)
  };

  ngOnInit(): void {
    this.usuarioService.getUsariosByRol(2).subscribe((data: Usuario[]) => {
      this.veterinarios = data;
    });
  }

  handleDateClick(arg: any) {
    alert(`Fecha seleccionada: ${arg.dateStr}`);
  }

  goBack(){
    window.history.back();
  }

  crearCita(){
    const dialogRef = this.dialog.open(ConfirmCitaComponent, {

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}

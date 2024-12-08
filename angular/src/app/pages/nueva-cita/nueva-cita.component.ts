import { Component } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

@Component({
  selector: 'app-nueva-cita',
  templateUrl: './nueva-cita.component.html',
  styleUrl: './nueva-cita.component.css'
})
export class NuevaCitaComponent {

  calendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    locale: 'es',
    firstDay: 1,
    editable: true, // Permite mover eventos
    selectable: true,
    events: [],
    dateClick: this.handleDateClick.bind(this),
  };

  handleDateClick(arg: any) {
    alert(`Fecha seleccionada: ${arg.dateStr}`);
  }


  goBack(){
    window.history.back();
  }

}

import { Component } from '@angular/core';

@Component({
  selector: 'app-nueva-cita',
  templateUrl: './nueva-cita.component.html',
  styleUrl: './nueva-cita.component.css'
})
export class NuevaCitaComponent {

  dateValue: Date | null = new Date();


  goBack(){
    window.history.back();
  }

}

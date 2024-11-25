import { Component } from '@angular/core';
import {Tratamiento} from "../../models/tratamiento.model";
import {ActivatedRoute} from "@angular/router";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {TratamientoService} from "../../services/tratamiento.service";
import {MascotaService} from "../../services/mascota.service";
import {Mascota} from "../../models/mascota.model";
import {Consulta} from "../../models/consulta.model";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-nueva-consulta',
  templateUrl: './nueva-consulta.component.html',
  styleUrl: './nueva-consulta.component.css'
})
export class NuevaConsultaComponent {

  mascota!: Mascota;
  idMascota!: number;
  nombreMascota: string = '';
  nombreVeterinario: string = '';
  currentUser: any = null;
  usuario!: Usuario;
  consulta!: Consulta;
  fecha: string = new Date().toLocaleDateString();

  tratamientos: Tratamiento[] = [];
  listaTratamientos: Tratamiento[] = [];
  tratamientoSeleccionado!: Tratamiento;
  total: number = 0;


  constructor(
    private route: ActivatedRoute,
    private consultaService: ConsultaService,
    private usuarioService: UsuarioService,
    private tratamientoService: TratamientoService,
    private mascotaService: MascotaService
  ) {}

  ngOnInit(): void {
    this.idMascota = this.route.snapshot.params['idMascota'];
    if (this.idMascota) {
      this.mascotaService.getInfoMascotaById(this.idMascota).subscribe(
        (mascota: Mascota) => {
          this.mascota = mascota;
          this.nombreMascota = mascota.nombre;
      });

      this.currentUser = this.usuarioService.getCurrentUser();
      console.log(this.currentUser);
      this.nombreVeterinario = this.currentUser.nombre + ' ' + this.currentUser.apellidos;

      this.tratamientoService.getTratamientos().subscribe((data: Tratamiento[]) => {
        this.listaTratamientos = data;
        console.log(this.listaTratamientos);
      });
    }
  }

  onTratamientoSeleccionado(tratamientoSeleccionado: Tratamiento): void {
    if (tratamientoSeleccionado) {
      const existe = this.tratamientos.some(t => t.idTratamiento === tratamientoSeleccionado.idTratamiento);

      if (!existe) {
        this.tratamientos.push(tratamientoSeleccionado);

        this.total += tratamientoSeleccionado.precio;
      } else {
        alert('El tratamiento ya está agregado.');
      }

      this.tratamientoSeleccionado = null!;
    }
  }

  eliminarTratamiento(index: number): void {
    this.total -= this.tratamientos[index].precio;

    this.tratamientos.splice(index, 1);
  }

  goBack(){
    window.history.back();
  }

}

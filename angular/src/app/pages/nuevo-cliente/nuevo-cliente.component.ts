import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {EspecieService} from "../../services/especie.service";
import {RazaService} from "../../services/raza.service";
import {Especie} from "../../models/especie.model";
import {Raza} from "../../models/raza.model";
import {Mascota} from "../../models/mascota.model";

@Component({
  selector: 'app-nuevo-cliente',
  templateUrl: './nuevo-cliente.component.html',
  styleUrl: './nuevo-cliente.component.css'
})
export class NuevoClienteComponent {

  mascota : Mascota = {
    idMascota: 0,
    nombre: "",
    edad: 0,
    peso: 0,
    genero: "",
    complexion: "",
    imagen: "https://tse4.mm.bing.net/th?id=OIP.fgL7rbtfZsEahPyuW9t0PQHaIO&pid=Api",
    activo: 1,
    fechaNacimiento: "",
    fechaAlta: "",
    fechaModificacion: "",
    fechaBaja: "",
    usuario : 0,
    raza: {
      idRaza: 0,
      nombre: "",
      especie: {
        idEspecie: 0,
        nombre: ""
      }
    }
  };


  especies: any[] = [];
  razas: any[] = [];
  razaOptions: any[] = [];

  constructor(
      private especieService: EspecieService,
      private razaService: RazaService,
      private route: Router
  ) { }

  private allRazas: Raza[] = [];

  ngOnInit() {
    this.getEspecies();
    this.getAllRazas();
  }

  getAllRazas() {
    this.razaService.getRazas().subscribe(
        (data: Raza[]) => {
          this.allRazas = data;
        },
        (error) => {
          console.error("Error al cargar las razas:", error);
        }
    );
  }

  getRazasPorEspecie(idEspecie: number) {
    console.log('Razas obtenidas:', this.allRazas);
    this.razaOptions = this.allRazas.filter(raza => raza.especie.idEspecie === idEspecie);
    console.log('Razas filtradas:', this.razaOptions);
  }

  getEspecies() {
    this.especieService.getEspecies().subscribe(
        (data: Especie[]) => {
          this.especies = data;
        },
        (error) => {
          console.error("Error al cargar las especies:", error);
        }
    );
  }

  onEspecieChange(idEspecie: number) {
    console.log('Especie seleccionada:', idEspecie);
    this.getRazasPorEspecie(idEspecie);
    this.mascota.raza.idRaza = null;
  }

  onRazaChange(event: any) {
    console.log('Raza seleccionada:', event);
  }

  save() {
    // Lógica para guardar la mascota...
    console.log(this.mascota);
  }

  goBack() {
    this.route.navigate(['/usuario/inicio-veterinario']);
  }
}

//cambiar css de genero
//cambiar css input::hover
//recolocar atributos
//css de input y selector

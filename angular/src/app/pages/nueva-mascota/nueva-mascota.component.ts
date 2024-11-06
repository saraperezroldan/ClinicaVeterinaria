import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EspecieService} from "../../services/especie.service";
import {RazaService} from "../../services/raza.service";
import {Especie} from "../../models/especie.model";
import {Raza} from "../../models/raza.model";
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";

@Component({
  selector: 'app-nueva-mascota',
  templateUrl: './nueva-mascota.component.html',
  styleUrl: './nueva-mascota.component.css'
})
export class NuevaMascotaComponent implements OnInit{

    especies: Especie[] = [];
    razaOptions: Raza[] = [];
    selectedEspecie: number | null = null;
    selectedRaza: number | null = null;
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

    constructor(
        private route: Router,
        private  ruta : ActivatedRoute,
        private especieService: EspecieService,
        private razaService: RazaService,
        private mascotaService : MascotaService
    ) {}

    ngOnInit(): void {
        this.getEspecies();
        this.mascota.usuario = this.ruta.snapshot.params['idUsuario'];
    }

    getEspecies() {
        this.especieService.getEspecies().subscribe(
            (data: Especie[]) => {
                this.especies = data;
            },
            (error) => {
                console.error('Error al cargar las especies:', error);
            }
        );
    }

    onEspecieChange(selectedEspecie: Especie | null) {
        if (!selectedEspecie) {
            this.razaOptions = [];
            this.selectedRaza = null;
            this.mascota.raza = { idRaza: 0, nombre: "", especie: { idEspecie: 0, nombre: "" } };
            return;
        }

        this.mascota.raza.especie = selectedEspecie;

        console.log('Especie seleccionada:', selectedEspecie);
        const idEspecie = selectedEspecie.idEspecie;

        this.razaOptions = [];
        this.selectedRaza = null;

        this.getRazasPorEspecie(idEspecie);
    }

    onRazaChange(selectedRaza: Raza | null) {
        if (!selectedRaza) {
            this.mascota.raza = { idRaza: 0, nombre: "", especie: this.mascota.raza.especie };
            return;
        }

        this.mascota.raza = selectedRaza;
        console.log('Raza seleccionada:', selectedRaza);
    }

    getRazasPorEspecie(idEspecie: number) {
        this.razaService.getRazas().subscribe(
            (data: Raza[]) => {
                data.forEach(raza => console.log(`Raza: ${raza.nombre}, Especie ID: ${raza.especie?.idEspecie}`));

                this.razaOptions = data.filter((raza) => raza.especie && raza.especie.idEspecie === idEspecie);
            },
            (error) => {
                console.error('Error al cargar las razas:', error);
            }
        );
    }

    goBack() {
        window.history.back();
    }

    crearMascota() {
        console.log('Creando mascota:', this.mascota);
        this.mascotaService.crearMascota(this.mascota).subscribe(
            (response) => {
                console.log('Mascota creada:', response);
                alert('Mascota creada correctamente');
                this.goBack();
            },
            (error) => {
                console.error('Error al crear la mascota:', error);
            }
        );
    }

}

import {Component, OnInit} from '@angular/core';
import {Mascota} from "../../models/mascota.model";
import {MascotaService} from "../../services/mascota.service";
import {ActivatedRoute, Router} from "@angular/router";
import {resetParseTemplateAsSourceFileForTest} from "@angular/compiler-cli/src/ngtsc/typecheck/diagnostics";
import {EspecieService} from "../../services/especie.service";
import {RazaService} from "../../services/raza.service";
import {Especie} from "../../models/especie.model";
import {Raza} from "../../models/raza.model";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-gestion-info-mascotas',
  templateUrl: './gestion-info-mascotas.component.html',
  styleUrl: './gestion-info-mascotas.component.css'
})
export class GestionInfoMascotasComponent implements OnInit{

    mascota!: Mascota;
    idMascota!: number;
    especies: Especie[] = [];
    razaOptions: Raza[] = [];

    constructor(
        private mascotaService: MascotaService,
        private route: ActivatedRoute,
        private especieService: EspecieService,
        private razaService: RazaService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.idMascota = this.route.snapshot.params['idMascota'];
        if (this.idMascota) {
            this.getInfoMascota(this.idMascota);
        }
        this.getEspecies();
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

    getRazasPorEspecie(idEspecie: number) {
        this.razaService.getRazas().subscribe(
            (data: Raza[]) => {
                this.razaOptions = data.filter(raza => raza.especie.idEspecie === idEspecie);
            },
            (error) => {
                console.error("Error al cargar las razas:", error);
            }
        );
    }

    onEspecieChange(idEspecie: number) {
        this.getRazasPorEspecie(idEspecie);
        this.mascota.raza.idRaza = null;
    }

    getInfoMascota(idMascota: number) {
        this.mascotaService.getInfoMascotaById(idMascota).subscribe(
            (mascota: Mascota) => {
                this.mascota = mascota;
                if (this.mascota.fechaAlta) {
                    this.mascota.fechaAlta = this.extractDate(this.mascota.fechaAlta);
                }
                if (this.mascota.fechaNacimiento) {
                    this.mascota.fechaNacimiento = this.extractDate(this.mascota.fechaNacimiento);
                }
                this.getRazasPorEspecie(this.mascota.raza.especie.idEspecie);
            }
        );
    }

    extractDate(isoDate: string): string {
        return isoDate.split('T')[0];
    }

    goBack() {
        window.history.back();
    }

    modificarMascota() {
        this.mascotaService.editarMascota(this.mascota).subscribe(
            () => {
                alert("Mascota actualizada con éxito");
            },
            (error) => {
                console.error("Error al actualizar la mascota:", error);
            }
        );
    }
}

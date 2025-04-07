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
import {ConsultaService} from "../../services/consulta.service";
import {Consulta} from "../../models/consulta.model";

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
    ultimaConsulta: string = '';

    constructor(
        private mascotaService: MascotaService,
        private route: ActivatedRoute,
        private especieService: EspecieService,
        private razaService: RazaService,
        private router: Router,
        private consultaService: ConsultaService
    ) { }

    ngOnInit(): void {
        this.idMascota = this.route.snapshot.params['idMascota'];
        if (this.idMascota) {
            this.getInfoMascota(this.idMascota);
        }
        this.getEspecies();
        this.getUltimaConsulta();
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

  getUltimaConsulta() {
    this.consultaService.getConsultasByMascota(this.idMascota).subscribe(
      (consultas: Consulta[]) => {
        if (consultas.length > 0) {
          console.log('Consultas:', consultas);
          const ultima = consultas[consultas.length - 1];
          this.ultimaConsulta = ultima.fechaCitaConsulta;
          console.log('Ultima consulta:', this.ultimaConsulta);
        }
      }
    );
  }


  extractDate(isoDate: string): string {
        const date = new Date(isoDate);
        date.setMinutes(date.getMinutes() + date.getTimezoneOffset());
        return date.toISOString().split('T')[0];
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
                console.log(this.mascota);
                console.error("Error al actualizar la mascota:", error);
            }
        );
    }
}

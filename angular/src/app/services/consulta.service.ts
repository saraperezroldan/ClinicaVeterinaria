import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../models/usuario.model";
import {Mascota} from "../models/mascota.model";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';

@Injectable({
    providedIn: 'root'
})

export class ConsultaService{

    constructor(private http: HttpClient) {
    }

    getConsultasByMascota(idMascota: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consulta/getConsultasByIdMascota/${idMascota}`);
    }

    getConsultaById(idConsulta: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consulta/getConsultaById/${idConsulta}`);
    }

    getCitasByIdMascota(idMascota: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consulta/getCitasByIdMascota/${idMascota}`);
    }

    getCitasByVeterinario(idVeterinario: number, pageNumber: number = 0, pageElements: number = 5 ) : Observable<any>{
      const body = {
        idVeterinario: idVeterinario,
        pageNumber: pageNumber,
        pageElements: pageElements,
        pageable : true,
        orderDesc : true
      };
      return this.http.post<any>(`${URLSERVER}consulta/getCitasConFiltro`, body);
    }

    getConsultasByVeterinario(idVeterinario: number, pageNumber: number = 0, pageElements: number = 5 ) : Observable<any>{
        const body = {
            idVeterinario: idVeterinario,
            pageNumber: pageNumber,
            pageElements: pageElements,
            pageable : true,
            orderDesc : true
        };
        return this.http.post<any>(`${URLSERVER}consulta/getConsultasConFiltro`, body);
    }

    eliminarCita(idCita: number) : Observable<any>{
        return this.http.delete<any>(`${URLSERVER}consulta/eliminarCita/${idCita}`);
    }

    eliminarConsulta(idConsulta: number) : Observable<any>{
        return this.http.delete<any>(`${URLSERVER}consulta/eliminarConsulta/${idConsulta}`);
    }
    getVacunasByIdMascota(idMascota: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consulta/getVacunasByIdMascota/${idMascota}`);
    }

    getCitasByIdVeterinario(idVterinario: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consulta/getCitasByIdVeterinario/${idVterinario}`);
    }



}

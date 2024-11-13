import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../models/usuario.model";
import {Mascota} from "../models/mascota.model";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';

@Injectable({
    providedIn: 'root'
})

export class TratamientoService{

    constructor(private http: HttpClient) {
    }

    getTratamientosByIdConsulta(idConsulta: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}consultaTratamiento/getTratamientosByIdConsulta/${idConsulta}`);
    }

    getTratamientoById(idTratamiento: number) : Observable<any>{
        return this.http.get<any>(`${URLSERVER}tratamiento/getTratamientoById/${idTratamiento}`);
    }

}

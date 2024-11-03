import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../models/usuario.model";
import {Mascota} from "../models/mascota.model";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';

@Injectable({
    providedIn: 'root'
})

export class RazaService {

    constructor(private http: HttpClient) {
    }

    getRazas() : Observable<any>{
        return this.http.get<any>(`${URLSERVER}raza/getRazas`);
    }

}

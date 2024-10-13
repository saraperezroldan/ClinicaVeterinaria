import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../models/usuario.model";
import {Injectable} from "@angular/core";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {
  private currentUser: any = null;

  constructor(private http: HttpClient) {
  }

  getInfoUsuarioById(id: number) : Observable<Usuario>{
    return this.http.get<Usuario>(`${URLSERVER}usuario/getUsuarioById/${id}`);
  }

  setCurrentUser(user: any){
    this.currentUser = user;
  }

  getCurrentUser(){
    return this.currentUser;
  }

  clearUser(){
    this.currentUser = null;
  }

  getUsuarioByDNI(dni: string) : Observable<Usuario>{
    return this.http.get<Usuario>(`${URLSERVER}usuario/getUsuarioByDni/${dni}`);
  }

  getUsuarioById(id: number) : Observable<Usuario>{
    return this.http.get<Usuario>(`${URLSERVER}usuario/getUsuarioById/${id}`);
  }

  eliminarUsuario(id: number) : Observable<any>{
    return this.http.delete(`${URLSERVER}usuario/eliminarUsuario/${id}`);
  }

  getUsariosByRol(rol: number) : Observable<Usuario[]>{
    return this.http.get<Usuario[]>(`${URLSERVER}usuario/getUsuariosByIdRol/${rol}`);
  }
}

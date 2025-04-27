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

  crearUsuario(usuario: Usuario) : Observable<Usuario>{
    return this.http.post<Usuario>(`${URLSERVER}usuario/crearUsuario`, usuario);
  }

  borrarUsuario(id: number) : Observable<any>{
    return this.http.delete(`${URLSERVER}usuario/eliminarUsuario/${id}`);
  }

  editarUsuario(usuario: Usuario) : Observable<Usuario>{
    return this.http.post<Usuario>(`${URLSERVER}usuario/modificarUsuario`, usuario);
  }

  getUsuariosConFiltro(idRol: number, pageNumber: number = 0, pageElements: number = 5 ) : Observable<any>{
    const body = {
      idRol: idRol,
      pageNumber: pageNumber,
      pageElements: pageElements,
      pageable : true,
      orderDesc : true
    };
    return this.http.post<any>(`${URLSERVER}usuario/getUsuarioConFiltro`, body);
  }

  cambiarFoto(idUsuario: number, formData: FormData): Observable<Usuario> {
    return this.http.post<Usuario>(`${URLSERVER}usuario/cambiarFoto/${idUsuario}`, formData);
  }

  cambiarPassword(idUsuario: number, nuevaPassword: string): Observable<any> {
    const body = { password: nuevaPassword };
    return this.http.post(`${URLSERVER}usuario/cambiarPassword/${idUsuario}`, body);
  }

}

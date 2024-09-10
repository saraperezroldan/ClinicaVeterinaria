import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Usuario} from "../models/usuario.model";
import {Injectable} from "@angular/core";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {
  private roles: string[] = [];

  constructor(private http: HttpClient) {
  }

  public getRoles(): string[]{
    return this.roles;
  }

  public setRoles(roles: string[]){
    this.roles = roles;
  }

  // Info usuario
  getInfoUsuarioById(id: number) : Observable<Usuario>{
    return this.http.get<Usuario>(`${URLSERVER}usuario/getUsuarioById/${id}`);
  }

}

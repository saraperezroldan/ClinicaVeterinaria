import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Usuario} from "../models/usuario.model";
import {Observable} from "rxjs";

const URLSERVER = 'http://localhost:8080/clinica_veterinaria/rest/';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  getUsuarioByEmail(email: string) : Observable<Usuario>{
    return this.http.get<Usuario>(`${URLSERVER}usuario/getUsuarioByEmail/${email}`);
  }

  saveUserToLocalStorage(user: any){
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  getUserFromLocalStorage(){
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }

  removeUserFromLocalStorage(){
    localStorage.removeItem('currentUser');
  }

  logout(){
    this.removeUserFromLocalStorage();
  }
}

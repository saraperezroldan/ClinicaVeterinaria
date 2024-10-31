import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {UsuarioService} from "../../services/usuario.service";
import {Rol} from "../../models/usuario.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  email!: string;
  password!: string;

  loginForm!: FormGroup;

  errorMensaje : string = '';

  constructor(private fb: FormBuilder, private loginService : LoginService, private router : Router, private usuarioService : UsuarioService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit(){
    console.log(this.loginForm.value);
    this.loginService.getUsuarioByEmail(this.loginForm.value.email).subscribe( usuario => {
      console.log(usuario);
      if(usuario.password === this.loginForm.value.password){
        console.log('Login correcto');
        const rol : number = usuario.rol;
        console.log(rol)
        console.log(usuario.rol);
        this.usuarioService.setCurrentUser(usuario);
        this.loginService.saveUserToLocalStorage(usuario);

        if(rol === 3){
          console.log('Eres usuario');
          this.router.navigate(['/usuario/inicio-usuario']);
        }else if(rol === 2){
          console.log('Eres veterinario');
          this.router.navigate(['/usuario/inicio-veterinario']);
        }else if(rol === 1){
          console.log('Eres administrador');
          this.router.navigate(['/usuario/inicio-administrador']);
        }
      } else {
        this.errorMensaje = 'Usuario o contraseña incorrectos';
      }
    });
  }
}

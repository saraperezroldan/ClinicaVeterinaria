import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  email!: string;
  password!: string;

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private loginService : LoginService) { }

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
        const rol = usuario.rol.nombre;

        if(rol === 'Administrador'){
          console.log('Eres admin');
        }else if(rol === 'Veterinario'){
          console.log('Eres veterinario');
        }
      }
    });
  }
}

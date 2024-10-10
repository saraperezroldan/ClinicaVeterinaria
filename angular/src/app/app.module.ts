import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MaterialModule} from "./material/material.module";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { PerfilUsuarioComponent } from './pages/perfil-usuario/perfil-usuario.component';
import { InicioUsuarioComponent } from './pages/inicio-usuario/inicio-usuario.component';
import {HttpClientModule} from "@angular/common/http";
import {DateFormatPipe} from "./shared/pipes/DateFormatPipe";
import {InfoMascotaComponent} from "./pages/info-mascota/info-mascota.component";
import {EdadFormatPipe} from "./shared/pipes/EdadFormatPipe";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepicker, MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import { CabeceraComponent } from './pages/cabecera/cabecera.component';
import { LoginComponent } from './pages/login/login.component';
import { InicioVeterinarioComponent } from './pages/inicio-veterinario/inicio-veterinario.component';
import { InfoClienteComponent } from './pages/info-cliente/info-cliente.component';
import { NuevoClienteComponent } from './pages/nuevo-cliente/nuevo-cliente.component';
import { NuevaMascotaComponent } from './pages/nueva-mascota/nueva-mascota.component';

@NgModule({
  declarations: [
    AppComponent,
    PerfilUsuarioComponent,
    InicioUsuarioComponent,
    InfoMascotaComponent,
    DateFormatPipe,
    EdadFormatPipe,
    CabeceraComponent,
    LoginComponent,
    InicioVeterinarioComponent,
    InfoClienteComponent,
    NuevoClienteComponent,
    NuevaMascotaComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

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
import { ConfirmDeleteClienteComponent } from './shared/confirm-delete-cliente/./confirm-delete-cliente.component';
import { ConfirmDeleteMascotaComponent } from './shared/confirm-delete-mascota/confirm-delete-mascota.component';
import { InicioAdministradorComponent } from './pages/inicio-administrador/inicio-administrador.component';
import { NuevoVeterinarioComponent } from './pages/nuevo-veterinario/nuevo-veterinario.component';
import { ConfirmDeleteVeterinarioComponent } from './shared/confirm-delete-veterinario/confirm-delete-veterinario.component';
import { InfoVeterinarioComponent } from './pages/info-veterinario/info-veterinario.component';
import { GestionInfoMascotasComponent } from './pages/gestion-info-mascotas/gestion-info-mascotas.component';
import {NgSelectModule} from "@ng-select/ng-select";
import { HistorialMascotaComponent } from './pages/historial-mascota/historial-mascota.component';
import { HistorialMascotaDetalladoComponent } from './pages/historial-mascota-detallado/historial-mascota-detallado.component';
import {HoraFormatPipe} from "./shared/pipes/HoraFormatPipe";
import { ConfirmDeleteCitaComponent } from './shared/confirm-delete-cita/confirm-delete-cita.component';
import { GestionCitasVeterinarioComponent } from './pages/gestion-citas-veterinario/gestion-citas-veterinario.component';
import { GestionConsultasVeterinarioComponent } from './pages/gestion-consultas-veterinario/gestion-consultas-veterinario.component';
import { NuevaConsultaComponent } from './pages/nueva-consulta/nueva-consulta.component';
import { GestionCitasAdministradorComponent } from './pages/gestion-citas-administrador/gestion-citas-administrador.component';
import { GestionConsultasAdministradorComponent } from './pages/gestion-consultas-administrador/gestion-consultas-administrador.component';
import { ConfirmDeleteTratamientoComponent } from './shared/confirm-delete-tratamiento/confirm-delete-tratamiento.component';
import { NuevoTratamientoComponent } from './shared/nuevo-tratamiento/nuevo-tratamiento.component';
import { GestionTratamientosComponent } from './pages/gestion-tratamientos/gestion-tratamientos.component';
import { ProximosTratamientosComponent } from './shared/proximos-tratamientos/proximos-tratamientos.component';
import { HistorialCitasVeterinarioComponent } from './pages/historial-citas-veterinario/historial-citas-veterinario.component';
import { NuevaCitaComponent } from './pages/nueva-cita/nueva-cita.component';
import {FullCalendarModule} from "@fullcalendar/angular";
import dayGridPlugin from "@fullcalendar/daygrid";
import { ConfirmCitaComponent } from './shared/confirm-cita/confirm-cita.component';
import { ConfirmDeleteConsultaComponent } from './shared/confirm-delete-consulta/confirm-delete-consulta.component';

@NgModule({
  declarations: [
    AppComponent,
    PerfilUsuarioComponent,
    InicioUsuarioComponent,
    InfoMascotaComponent,
    DateFormatPipe,
    EdadFormatPipe,
    HoraFormatPipe,
    CabeceraComponent,
    LoginComponent,
    InicioVeterinarioComponent,
    InfoClienteComponent,
    NuevoClienteComponent,
    NuevaMascotaComponent,
    ConfirmDeleteClienteComponent,
    ConfirmDeleteMascotaComponent,
    InicioAdministradorComponent,
    NuevoVeterinarioComponent,
    ConfirmDeleteVeterinarioComponent,
    InfoVeterinarioComponent,
    GestionInfoMascotasComponent,
    HistorialMascotaComponent,
    HistorialMascotaDetalladoComponent,
    ConfirmDeleteCitaComponent,
    GestionCitasVeterinarioComponent,
    GestionConsultasVeterinarioComponent,
    NuevaConsultaComponent,
    GestionCitasAdministradorComponent,
    GestionConsultasAdministradorComponent,
    ConfirmDeleteTratamientoComponent,
    NuevoTratamientoComponent,
    GestionTratamientosComponent,
    ProximosTratamientosComponent,
    HistorialCitasVeterinarioComponent,
    NuevaCitaComponent,
    ConfirmCitaComponent,
    ConfirmDeleteConsultaComponent,
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
    MatNativeDateModule,
    NgSelectModule,
    FullCalendarModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

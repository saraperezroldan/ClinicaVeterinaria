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

@NgModule({
  declarations: [
    AppComponent,
    PerfilUsuarioComponent,
    InicioUsuarioComponent,
    InfoMascotaComponent,
    DateFormatPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

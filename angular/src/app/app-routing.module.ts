import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PerfilUsuarioComponent} from "./pages/perfil-usuario/perfil-usuario.component";
import {InicioUsuarioComponent} from "./pages/inicio-usuario/inicio-usuario.component";
import {InfoMascotaComponent} from "./pages/info-mascota/info-mascota.component";
import {CabeceraComponent} from "./pages/cabecera/cabecera.component";
import {LoginComponent} from "./pages/login/login.component";

const routes: Routes = [
  {
    path: 'inicio',
    component : InicioUsuarioComponent
  },
  {
    path: 'perfil-usuario/:id',
    component : PerfilUsuarioComponent
  },
  {
    path: 'mascota/:id',
    component : InfoMascotaComponent
  },
  {
    path: '',
    component : LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

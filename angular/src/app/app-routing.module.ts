import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PerfilUsuarioComponent} from "./pages/perfil-usuario/perfil-usuario.component";
import {InicioUsuarioComponent} from "./pages/inicio-usuario/inicio-usuario.component";
import {InfoMascotaComponent} from "./pages/info-mascota/info-mascota.component";

const routes: Routes = [
  {
    path: 'inicio',
    component : InicioUsuarioComponent
  },
  {
    path: 'perfil',
    component : PerfilUsuarioComponent
  },
  {
    path : 'inicio/mascota',
    component: InfoMascotaComponent
  },
  {
    path : '**',
    redirectTo: 'inicio'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

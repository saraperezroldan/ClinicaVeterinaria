import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PerfilUsuarioComponent} from "./pages/perfil-usuario/perfil-usuario.component";
import {InicioUsuarioComponent} from "./pages/inicio-usuario/inicio-usuario.component";
import {InfoMascotaComponent} from "./pages/info-mascota/info-mascota.component";
import {CabeceraComponent} from "./pages/cabecera/cabecera.component";
import {LoginComponent} from "./pages/login/login.component";
import {InicioVeterinarioComponent} from "./pages/inicio-veterinario/inicio-veterinario.component";
import {InfoClienteComponent} from "./pages/info-cliente/info-cliente.component";
import {NuevoClienteComponent} from "./pages/nuevo-cliente/nuevo-cliente.component";

const routes: Routes = [
  {
    path: '',
    component : LoginComponent
  },
  {
    path: 'usuario',
    component : CabeceraComponent,
    children: [
      {
        path: 'inicio',
        component : InicioUsuarioComponent
      },
      {
        path: 'perfil',
        component : PerfilUsuarioComponent
      },
      {
        path: 'mascota/:id',
        component : InfoMascotaComponent
      },
      {
        path: 'inicio-veterinario',
        component : InicioVeterinarioComponent
      },
      {
        path: 'info-cliente/:idUsuario',
        component: InfoClienteComponent
      },
      {
        path: 'nuevo-cliente',
        component: NuevoClienteComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

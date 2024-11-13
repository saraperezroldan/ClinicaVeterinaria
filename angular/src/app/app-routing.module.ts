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
import {NuevaMascotaComponent} from "./pages/nueva-mascota/nueva-mascota.component";
import {InicioAdministradorComponent} from "./pages/inicio-administrador/inicio-administrador.component";
import {NuevoVeterinarioComponent} from "./pages/nuevo-veterinario/nuevo-veterinario.component";
import {InfoVeterinarioComponent} from "./pages/info-veterinario/info-veterinario.component";
import {GestionInfoMascotasComponent} from "./pages/gestion-info-mascotas/gestion-info-mascotas.component";
import {HistorialMascotaComponent} from "./pages/historial-mascota/historial-mascota.component";

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
        path: 'inicio-usuario',
        component : InicioUsuarioComponent
      },
      {
        path: 'inicio-veterinario',
        component : InicioVeterinarioComponent
      },
      {
        path: 'inicio-administrador',
        component: InicioAdministradorComponent
      },
      {
        path: 'perfil',
        component : PerfilUsuarioComponent
      },
      {
        path: 'info-mascota/:idMascota',
        component : InfoMascotaComponent
      },
      {
        path: 'info-cliente/:idUsuario',
        component: InfoClienteComponent
      },
      {
        path: 'nuevo-cliente',
        component: NuevoClienteComponent
      },
      {
        path: 'nueva-mascota/:idUsuario',
        component: NuevaMascotaComponent
      },
      {
        path: 'nuevo-veterinario',
        component: NuevoVeterinarioComponent
      },
      {
        path: 'info-veterinario/:idUsuario',
        component: InfoVeterinarioComponent
      },
      {
        path: 'gestion-info-mascota/:idMascota',
        component : GestionInfoMascotasComponent
      },
      {
        path: 'historial-mascota/:idMascota',
        component: HistorialMascotaComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

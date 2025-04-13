import {Component, OnInit, ViewChild} from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";
import {Usuario} from "../../models/usuario.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeleteClienteComponent} from "../../shared/confirm-delete-cliente/./confirm-delete-cliente.component";
import {MatPaginator, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-inicio-veterinario',
  templateUrl: './inicio-veterinario.component.html',
  styleUrl: './inicio-veterinario.component.css'
})
export class InicioVeterinarioComponent implements OnInit{

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalItems = 0;
  pageSize = 7;
  pageIndex = 0;

  usuario : Usuario | undefined;
  veterinario: Usuario | undefined;
  searchDNI: string = '';
  errorMensaje: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router, public dialog : MatDialog) {}

  public dataSource = new MatTableDataSource<Usuario>();
  public displayedColumns: string[] = ['idUsuario', 'nombre', 'apellidos', 'dni', 'telefono', 'email', 'fechaAlta', 'acciones'];

  ngOnInit(): void {
    const usuarioJSON = localStorage.getItem('currentUser');
    if(usuarioJSON){
      this.veterinario = JSON.parse(usuarioJSON);
    }

    this.obtenerClientes();
  }

  obtenerClientes(): void {
    this.usuarioService.getUsuariosConFiltro(3, this.pageIndex, this.pageSize).subscribe(
      (response) => {
        console.log(response);
        this.totalItems = response.count;
        this.dataSource.data = response.results;
      },
      (error) => {
        console.error('Error al obtener los usuarios con rol 3:', error);
      }
    );
  }


  buscarUsuario(): void {
    if (this.searchDNI) {
      this.usuarioService.getUsuarioByDNI(this.searchDNI).subscribe(
        (data) => {
          if(data.activo === 1){
            this.usuario = data;
            this.searchDNI = '';
            this.errorMensaje = '';
          }else{
            this.errorMensaje = 'No se ha encontrado ningún cliente con ese DNI';
            this.usuario = undefined;
          }

        },
        (error) => {
          console.error('Error al buscar cliente', error);
          this.errorMensaje = 'No se ha encontrado ningún cliente con ese DNI';
          this.usuario = undefined;
        }
      );
    }
  }

  nuevoUsuario(): void {
    this.router.navigate(['/usuario/nuevo-cliente']);
  }

  eliminarUsuario(): void {
    const dialogRef = this.dialog.open(ConfirmDeleteClienteComponent, { });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if(this.usuario)
        {
            this.usuarioService.eliminarUsuario(this.usuario.idUsuario).subscribe(
                response => {
                    console.log('Cliente eliminado:', response);
                    alert('El cliente ha sido eliminado correctamente');
                    this.usuario = undefined;
                },
                error => {
                    console.error('Error al eliminar el cliente:', error);
                }
            );
        }

      }
    });
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.obtenerClientes();

  }




}

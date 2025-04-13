import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ConsultaService} from "../../services/consulta.service";
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-cambiar-password',
  templateUrl: './cambiar-password.component.html',
  styleUrl: './cambiar-password.component.css'
})
export class CambiarPasswordComponent {

  nuevaPassword: string = '';
  confirmarPassword: string = '';
  error: string = '';
  usuario!: Usuario;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { usuario: Usuario },
              public dialogRef: MatDialogRef<CambiarPasswordComponent>,
              private usuarioService: UsuarioService) {
  }

  onClose(){
    this.dialogRef.close(false);
  }

  onSubmit() {
    if (this.nuevaPassword !== this.confirmarPassword) {
      this.error = 'Las contraseñas no coinciden';
      return;
    }

    this.usuario = this.data.usuario;

    this.usuarioService.editarUsuario(this.usuario).subscribe({
      next: () => {
        this.dialogRef.close(true);
      },
      error: err => {
        this.error = 'Error al actualizar la contraseña';
        console.error(err);
      }
    });
  }

}

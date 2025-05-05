import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {UsuarioService} from "../../services/usuario.service";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-cambiar-foto',
  templateUrl: './cambiar-foto.component.html',
  styleUrl: './cambiar-foto.component.css'
})
export class CambiarFotoComponent {

  selectedFile: File | null = null;

  constructor(
    private dialogRef: MatDialogRef<CambiarFotoComponent>,
    private usuarioService: UsuarioService,
    @Inject(MAT_DIALOG_DATA) public data: { usuario: Usuario }
  ) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadImage() {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('image', this.selectedFile);

    this.usuarioService.cambiarFoto(this.data.usuario.idUsuario, formData).subscribe({
      next: (updatedUser) => {
        this.dialogRef.close(updatedUser);
        alert('Foto actualizada correctamente');
      },
      error: (error) => {
        console.error('Error al subir la foto', error);
        alert('Error al subir la foto');
      }
    });
  }

  cancelar() {
    this.dialogRef.close();
  }

}

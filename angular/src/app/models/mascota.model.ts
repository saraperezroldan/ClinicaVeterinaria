import {Usuario} from "./usuario.model";
import {Raza} from "./raza.model";

export interface Mascota{
    idMascota: number;
    nombre: string;
    edad: number | null
    peso: number | null;
    genero: string;
    complexion: string;
    fechaNacimiento: string;
    imagen: string;
    fechaAlta: string;
    fechaModificacion: string;
    fechaBaja: string;
    usuario: number;
    raza: Raza;
    activo : number;
}

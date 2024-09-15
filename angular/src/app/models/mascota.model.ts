import {Usuario} from "./usuario.model";
import {Raza} from "./raza.model";

export interface Mascota{
    idMascota: number;
    nombre: string;
    edad: number;
    peso: number;
    genero: string;
    complexion: string;
    /*especie: string;
    raza: string;*/
    fechaNacimiento: string;
    imagen: string;
    fechaAlta: string;
    fechaModificacion: string;
    fechaBaja: string;
    usuario: Usuario;
    raza: Raza;
}

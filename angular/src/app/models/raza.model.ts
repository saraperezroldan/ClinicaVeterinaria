import {Especie} from "./especie.model";

export interface Raza {
  idRaza: number;
  nombre: string;
  especie: Especie;
}

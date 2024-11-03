import {Especie} from "./especie.model";

export interface Raza {
  idRaza: number | null;
  nombre: string;
  especie: Especie;
}

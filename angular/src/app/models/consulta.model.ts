export interface Consulta{
    idConsulta: number;
    idMascota: number;
    mascota: number;
    idVeterinario: number;
    fechaCitaConsulta: string;
    horaCita: string;
    motivo: string;
    diagnostico: string;
    observaciones: string;
    fechaAlta: string;
    fechaModificacion: string;
    tratamientos: any[];
    esCita: number;
}

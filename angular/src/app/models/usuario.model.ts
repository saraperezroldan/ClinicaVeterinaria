export interface Usuario {
    idUsuario: number;
    nombre: string;
    apellidos: string;
    dni: string;
    email: string;
    password: string;
    telefono: string;
    direccion: string;
    poblacion: string;
    provincia: string;
    codigoPostal: string;
    imagen: string;
    fechaAlta: string;
    fechaModificacion: string;
    fechaNacimiento: string;
    fechaBaja: string;
    rol: Rol;
}

export interface Rol {
    idRol: number;
    nombre: string;
}

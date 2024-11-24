package com.clinica.clinicaVeterinaria.domain.utils;

public class Constantes {

    //GENERAL
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String TIME_PATTERN = "HHmmss";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + TIME_PATTERN;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /*ROL*/
    public static int ROL_ADMIN = 1;
    public static int ROL_VETERINARIO = 2;
    public static int ROL_CLIENTE = 3;

    /* USUARIO */
    public static int USUARIO_NOMBRE_MAX = 50;
    public static int USUARIO_APELLIDOS_MAX = 100;
    public static int USUARIO_DNI_MAX = 9;
    public static int USUARIO_DIRECCION_MAX = 150;
    public static int USUARIO_TELEFONO_MAX = 100;
    public static int USUARIO_EMAIL_MAX = 100;
    public static int USUARIO_CODIGO_POSTAL_MAX = 100;

    /* MASCOTA */
    public static int MASCOTA_NOMBRE_MAX = 100;
    public static int MASCOTA_EDAD_MAX = 100;
    public static int MASCOR_DNI_MAX = 9;
    public static int MASCOTA_DIRECCION_MAX = 150;

    /* ESPECIE */
    public static int ESPECIE_NOMBRE_MAX = 100;

    /* RAZA */
    public static int RAZA_NOMBRE_MAX = 100;

    /*TRATAMIENTO*/
    public static int TRATAMIENTO_URGENCIA = 12;



}


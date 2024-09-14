package com.clinica.clinicaVeterinaria.domain.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static LocalDate convertirDateALocalDate(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        return fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String calcularEdadEnAniosYMeses(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();

        if (fechaNacimiento != null && fechaNacimiento.isBefore(fechaActual)) {
            Period periodo = Period.between(fechaNacimiento, fechaActual);

            int anios = periodo.getYears();
            int meses = periodo.getMonths();

            return anios + " años y " + meses + " meses";
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura o nula");
        }
    }
}

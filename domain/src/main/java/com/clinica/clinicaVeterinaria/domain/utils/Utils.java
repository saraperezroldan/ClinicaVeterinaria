package com.clinica.clinicaVeterinaria.domain.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

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
            if (anios == 1) {
                return anios + " año y " + meses + " meses";
            }
            return anios + " años y " + meses + " meses";
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura o nula");
        }
    }
    /**
     * Devuelve la fecha actual como un String con el formato que se indique.
     * @return Fecha actual como un String con el formato que se indique.
     */
    public static String convertToDateFormatted(Date date, String pattern) {
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        String fechaAhoraFormateada = formato.format(new Date());

        return fechaAhoraFormateada;
    }
    /**
     * Parsea / valida a un tipo de dato fecha a partir de una cadena formateada de fecha
     * @param dateFormatted Cadena con la fecha formateada
     * @param pattern Patrón de fecha
     * @return Date con la fecha
     * @throws ParseException Excepción que se lanzará cuando el patrón no coincida con una fecha.
     */
    public static Date parseDate(String dateFormatted, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        dateFormat.setLenient(false);
        Date date = dateFormat.parse(dateFormatted);
        return date;
    }

    /**
     * Compara un rango de fechas con formato y devuelve:
     * 	1. El valor 0 si el argumento dateInitialFormatted es igual a dateFinishFormatted.
     *  2. Un valor menor que 0 si dateInitialFormatted es anterior al argumento dateFinishFormatted.
     *  3. Un valor mayor que 0 si dateInitialFormatted es posterior al argumento dateFinishFormatted.
     * @param dateFromFormatted Cadena de fecha desde
     * @param dateUntilFormatted  Cadena de fecha hasta
     * @param pattern			   Patrón de fecha del rango de fechas
     * @return
     * @throws ParseException Excepción si alguna de las fechas no es parseable.
     */
    public static Integer compareDatesFormatted(String dateFromFormatted, String dateUntilFormatted, String pattern) throws ParseException {
        Date dateFrom = parseDate(dateFromFormatted, pattern);
        Date dateUntil = parseDate(dateUntilFormatted, pattern);

        return dateFrom.compareTo(dateUntil);
    }

    public static String replaceChars(final String str, final String searchChars, String replaceChars) {
        if (!StringUtils.hasText(str) || !StringUtils.hasText(searchChars)) {
            return str;
        }
        if (replaceChars == null) {
            replaceChars = "";
        }
        boolean modified = false;
        final int replaceCharsLength = replaceChars.length();
        final int strLength = str.length();
        final StringBuilder buf = new StringBuilder(strLength);
        for (int i = 0; i < strLength; i++) {
            final char ch = str.charAt(i);
            final int index = searchChars.indexOf(ch);
            if (index >= 0) {
                modified = true;
                if (index < replaceCharsLength) {
                    buf.append(replaceChars.charAt(index));
                }
            } else {
                buf.append(ch);
            }
        }
        if (modified) {
            return buf.toString();
        }
        return str;
    }
}

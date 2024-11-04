package com.clinica.clinicaVeterinaria.rest;
import java.sql.Timestamp;

import com.clinica.clinicaVeterinaria.business.locale.ILocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class AppRestExceptionHandler {

    @Autowired
    private ILocaleService localeService;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<AppRestException> handleException(ResponseStatusException exception) {
        AppRestException error = new AppRestException(exception.getStatus(),
                this.localeService.getMessage(exception.getReason()),
                new Timestamp(System.currentTimeMillis()).toString());

        return new ResponseEntity<>(error, error.getStatus());
    }
}

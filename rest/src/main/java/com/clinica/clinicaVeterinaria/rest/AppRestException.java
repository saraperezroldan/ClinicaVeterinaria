package com.clinica.clinicaVeterinaria.rest;

import org.springframework.http.HttpStatus;

public class AppRestException {
    private String timestamp;
    private HttpStatus status;
    private String message;

    public AppRestException(HttpStatus status, String message, String timestamp){
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ConsultaTratamientoID implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idConsulta;
    private int idTratamiento;

    public ConsultaTratamientoID() {
    }

    public ConsultaTratamientoID(int idConsulta, int idTratamiento) {
        this.idConsulta = idConsulta;
        this.idTratamiento = idTratamiento;
    }

    public int getIdConsulta() {return idConsulta;}
    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}
    public int getIdTratamiento() {return idTratamiento;}
    public void setIdTratamiento(int idTratamiento) {this.idTratamiento = idTratamiento;}
}

package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "consulta_tratamiento")
public class ConsultaTratamiento {
    @EmbeddedId
    private ConsultaTratamientoID id;
    private float importe;

    public ConsultaTratamientoID getId() {return id;}
    public void setId(ConsultaTratamientoID id) {this.id = id;}
    public float getImporte() {return importe;}
    public void setImporte(float importe) {this.importe = importe;}
}
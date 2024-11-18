package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "consulta_tratamiento")
public class ConsultaTratamiento {
    @EmbeddedId
    private ConsultaTratamientoID id;
    private float importe;

    @ManyToOne
    //@JoinColumn(name = "idConsulta", insertable = false, updatable = false)
    @MapsId("idConsulta")
    @JoinColumn(name = "idConsulta")
    private Consulta consulta;
    @ManyToOne
    //@JoinColumn(name = "idTratamiento", insertable = false, updatable = false)
    @MapsId("idTratamiento")
    @JoinColumn(name = "idTratamiento")
    private Tratamiento tratamiento;

    public ConsultaTratamientoID getId() {return id;}
    public void setId(ConsultaTratamientoID id) {this.id = id;}
    public float getImporte() {return importe;}
    public void setImporte(float importe) {this.importe = importe;}

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
}
package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "consulta_tratamiento")
public class ConsultaTratamiento {
    @EmbeddedId
    private ConsultaTratamientoID id;

    @MapsId("idConsulta")
    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;
    @MapsId("idTratamiento")
    @ManyToOne
    @JoinColumn(name = "id_tratamiento", nullable = false)
    private Tratamiento tratamiento;


    public ConsultaTratamientoID getId() {return id;}
    public void setId(ConsultaTratamientoID id) {this.id = id;}
    public Consulta getConsulta() {
        return consulta;
    }
    public void setConsulta(Consulta consulta) {this.consulta = consulta;}
    public Tratamiento getTratamiento() {
        return tratamiento;
    }
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
}
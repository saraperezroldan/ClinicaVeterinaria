package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "consulta_tratamiento")
public class ConsultaTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsultaTratamiento;
    private float importe;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="id_consulta")
    private Consulta consulta;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="id_tratamiento")
    private Tratamiento tratamiento;

    public ConsultaTratamiento() {
    }

    public ConsultaTratamiento(int idConsultaTratamiento, float importe, Consulta consulta, Tratamiento tratamiento) {
        this.idConsultaTratamiento = idConsultaTratamiento;
        this.importe = importe;
        this.consulta = consulta;
        this.tratamiento = tratamiento;
    }

    public int getIdConsultaTratamiento() {
        return idConsultaTratamiento;
    }

    public void setIdConsultaTratamiento(int idConsultaTratamiento) {
        this.idConsultaTratamiento = idConsultaTratamiento;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

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

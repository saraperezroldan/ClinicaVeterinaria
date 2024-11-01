package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;
    private String observaciones;
    private String diagnostico;
    private Date fechaAlta;
    private Date fechaCita;
    private Date fechaUltima;

    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    //@OneToMany(mappedBy = "consulta", cascade = {CascadeType.REMOVE})
    //private Set<ConsultaTratamiento> tratamientosConsulta;

    public Consulta() {}

    public Consulta(int idConsulta, String observaciones, String diagnostico, Date fechaCita, Date fechaAlta, Date fechaUltima, Mascota mascota) {
        this.idConsulta = idConsulta;
        this.observaciones = observaciones;
        this.diagnostico = diagnostico;
        this.fechaCita = fechaCita;
        this.fechaAlta = fechaAlta;
        this.fechaUltima = fechaUltima;
        this.mascota = mascota;
    }

    public int getIdConsulta() {return idConsulta;}

    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}

    public String getObservaciones() {return observaciones;}

    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    public String getDiagnostico() {return diagnostico;}

    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}

    public Date getFechaCita() {return fechaCita;}

    public void setFechaCita(Date fechaCita) {this.fechaCita = fechaCita;}

    public Date getFechaAlta() {return fechaAlta;}

    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}

    public Date getFechaUltima() {return fechaUltima;}

    public void setFechaUltima(Date fechaUltima) {this.fechaUltima= fechaUltima;}

    public Mascota getMascota() {return mascota;}

    public void setMascota(Mascota mascota) {this.mascota = mascota;}

    //public Set<ConsultaTratamiento> getTratamientosConsulta() {return tratamientosConsulta;}

    //public void setTratamientosConsulta(Set<ConsultaTratamiento> tratamientosConsulta) {this.tratamientosConsulta = tratamientosConsulta;}
}
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
    private Date fechaCita;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_mascota"
    )
    private Mascota mascota;

    @OneToMany(
            mappedBy = "consulta",
            cascade = {
                    CascadeType.REMOVE
            }
    )
    private Set<ConsultaTratamiento> tratamientosConsulta;

    public Consulta() {
    }

    public Consulta(int idConsulta, String observaciones, String diagnostico, Date fechaCita, Date fechaCreacion, Date fechaUltimaModificacion, Mascota mascota) {
        this.idConsulta = idConsulta;
        this.observaciones = observaciones;
        this.diagnostico = diagnostico;
        this.fechaCita = fechaCita;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.mascota = mascota;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Set<ConsultaTratamiento> getTratamientosConsulta() {
        return tratamientosConsulta;
    }

    public void setTratamientosConsulta(Set<ConsultaTratamiento> tratamientosConsulta) {
        this.tratamientosConsulta = tratamientosConsulta;
    }
}

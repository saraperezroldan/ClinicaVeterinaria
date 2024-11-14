package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;
    private String motivo;
    private String diagnostico;
    private String observaciones;
    private int esCita;
    private Date fechaAlta;
    private LocalDate fechaCita;
    private Date fechaUltima;

    private int idVeterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    //@OneToMany(mappedBy = "consulta", cascade = {CascadeType.REMOVE})
    //private Set<ConsultaTratamiento> tratamientosConsulta;

    public Consulta() {}

    public int getIdConsulta() {return idConsulta;}
    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}
    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
    public int getEsCita() {return esCita;}
    public void setEsCita(int esCita) {this.esCita = esCita;}
    public LocalDate getFechaCita() {return fechaCita;}
    public void setFechaCita(LocalDate fechaCita) {this.fechaCita = fechaCita;}
    public Date getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}

    public Date getFechaUltima() {return fechaUltima;}

    public void setFechaUltima(Date fechaUltima) {this.fechaUltima= fechaUltima;}

    public int getIdVeterinario() {
        return idVeterinario;
    }
    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public Mascota getMascota() {return mascota;}

    public void setMascota(Mascota mascota) {this.mascota = mascota;}

    //public Set<ConsultaTratamiento> getTratamientosConsulta() {return tratamientosConsulta;}

    //public void setTratamientosConsulta(Set<ConsultaTratamiento> tratamientosConsulta) {this.tratamientosConsulta = tratamientosConsulta;}
}
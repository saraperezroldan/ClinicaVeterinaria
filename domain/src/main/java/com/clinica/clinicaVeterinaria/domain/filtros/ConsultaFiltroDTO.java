package com.clinica.clinicaVeterinaria.domain.filtros;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaFiltroDTO extends BaseFiltroDTO {
    private int idConsulta;
    private int idVeterinario;
    private LocalDate fechaCitaConsulta;
    private LocalTime horaCita;

    public int getIdConsulta() {return idConsulta;}
    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}
    public int getIdVeterinario() {return idVeterinario;}
    public void setIdVeterinario(int idVeterinario) {this.idVeterinario = idVeterinario;}
    public LocalDate getFechaCitaConsulta() {return fechaCitaConsulta;}
    public void setFechaCitaConsulta(LocalDate fechaCitaConsulta) {this.fechaCitaConsulta = fechaCitaConsulta;}
    public LocalTime getHoraCita() {return horaCita;}
    public void setHoraCita(LocalTime horaCita) {this.horaCita = horaCita;}
}

package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaDTO {
    private int idConsulta;
    private String motivo;
    private String diagnostico;
    private String observaciones;
    private int esCita;
    private LocalDate fechaCita;
    private Date fechaAlta;
    private Date fechaUltima;
    private int idVeterinario;
    private Integer mascota;
    private List<ConsultaTratamientoDTO> tratamientosConsulta;

    public static ConsultaDTO toDTO(Consulta consulta){
        return ConsultaDTO.toDTO(consulta, Arrays.asList(MascotaDTO.class, ConsultaTratamientoDTO.class));
    }

    public static ConsultaDTO toDTO(Consulta consulta, List<Class<?>> includeRelacion){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        if(consulta == null){
            return consultaDTO;
        }

        consultaDTO.setIdConsulta(consulta.getIdConsulta());
        consultaDTO.setMotivo(consulta.getMotivo());
        consultaDTO.setDiagnostico(consulta.getDiagnostico());
        consultaDTO.setObservaciones(consulta.getObservaciones());
        consultaDTO.setEsCita(consulta.getEsCita());
        consultaDTO.setFechaCita(consulta.getFechaCita()!= null ? consulta.getFechaCita() : null);
        consultaDTO.setFechaAlta(consulta.getFechaAlta()!= null ? consulta.getFechaAlta() : new Date());
        consultaDTO.setFechaUltima(consulta.getFechaUltima()!= null ? consulta.getFechaUltima() : null);
        consultaDTO.setIdVeterinario(consulta.getIdVeterinario() > 0 ? consulta.getIdVeterinario() : 1003);
        consultaDTO.setMascota(consulta.getMascota() != null ? consulta.getMascota().getIdMascota() : null);

        return consultaDTO;
    }

    public static List<ConsultaDTO> toDTO(List<Consulta> consultas){
        if(consultas == null){
            return Collections.emptyList();
        }

        return consultas.stream()
                .map(ConsultaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ConsultaDTO> toDTO(List<Consulta> consultas, List<Class<?>> includeRelacion){
        if(consultas == null){
            return Arrays.asList();
        }

        return consultas.stream()
                .map(consulta -> ConsultaDTO.toDTO(consulta, includeRelacion))
                .collect(Collectors.toList());
    }

    public static Consulta toDomain(ConsultaDTO consultaDTO){
        Consulta consulta = new Consulta();

        if(consultaDTO == null){
            return null;
        }

        consulta.setIdConsulta(consultaDTO.getIdConsulta());
        consulta.setMotivo(consultaDTO.getMotivo());
        consulta.setDiagnostico(consultaDTO.getDiagnostico());
        consulta.setObservaciones(consultaDTO.getObservaciones());
        consulta.setEsCita(consultaDTO.getEsCita());
        consulta.setFechaCita(consultaDTO.getFechaCita());
        consulta.setFechaAlta(consultaDTO.getFechaAlta());
        consulta.setFechaUltima(consultaDTO.getFechaUltima());
        consulta.setIdVeterinario(consultaDTO.getIdVeterinario());
        Mascota mascota = new Mascota();
        mascota.setIdMascota(consultaDTO.getMascota());
        consulta.setMascota(mascota);

        return consulta;
    }

    public static List<Consulta> toDomain(List<ConsultaDTO> consultasDTO){
        if(consultasDTO == null){
            return Collections.emptyList();
        }

        return consultasDTO.stream()
                .map(consultaDTO -> ConsultaDTO.toDomain(consultaDTO))
                .collect(Collectors.toList());
    }


    public int getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
    public int getEsCita() {return esCita;}
    public void setEsCita(int esCita) {this.esCita = esCita;}
    public LocalDate getFechaCita() {return fechaCita;}
    public void setFechaCita(LocalDate fechaCita) {this.fechaCita = fechaCita;}
    public Date getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    public Date getFechaUltima() {
        return fechaUltima;
    }
    public void setFechaUltima(Date fechaUltima) {
        this.fechaUltima = fechaUltima;
    }
    public Integer getMascota() {return mascota;}
    public void setMascota(Integer mascota) {this.mascota = mascota;}
    public int getIdVeterinario() {return idVeterinario;}
    public void setIdVeterinario(int idVeterinario) {this.idVeterinario = idVeterinario;}

    public List<ConsultaTratamientoDTO> getTratamientosConsulta() {
        return tratamientosConsulta;
    }
    public void setTratamientosConsulta(List<ConsultaTratamientoDTO> tratamientosConsulta) {
        this.tratamientosConsulta = tratamientosConsulta;
    }
}
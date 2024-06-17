package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaDTO {

    private int idConsulta;
    private String observaciones;
    private String diagnostico;
    private Date fechaCita;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private MascotaDTO mascota;
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
        consultaDTO.setObservaciones(consulta.getObservaciones());
        consultaDTO.setDiagnostico(consulta.getDiagnostico());
        consultaDTO.setFechaCita(consulta.getFechaCita());
        consultaDTO.setFechaCreacion(consulta.getFechaCreacion());
        consultaDTO.setFechaUltimaModificacion(consulta.getFechaUltimaModificacion());
        if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(MascotaDTO.class)){
            consultaDTO.setMascota(MascotaDTO.toDTO(consulta.getMascota()));
        }
        if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(ConsultaTratamientoDTO.class)){
            consultaDTO.setTratamientosConsulta(ConsultaTratamientoDTO.toDTO(consulta.getTratamientosConsulta().stream().collect(Collectors.toList()), includeRelacion));
        }
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
        consulta.setObservaciones(consultaDTO.getObservaciones());
        consulta.setDiagnostico(consultaDTO.getDiagnostico());
        consulta.setFechaCita(consultaDTO.getFechaCita());
        consulta.setFechaCreacion(consultaDTO.getFechaCreacion());
        consulta.setFechaUltimaModificacion(consultaDTO.getFechaUltimaModificacion());
        consulta.setMascota(MascotaDTO.toDomain(consultaDTO.getMascota()));
        consulta.setTratamientosConsulta(ConsultaTratamientoDTO.toDomain(consultaDTO.getTratamientosConsulta()).stream().collect(Collectors.toSet()));

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

    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    public List<ConsultaTratamientoDTO> getTratamientosConsulta() {
        return tratamientosConsulta;
    }

    public void setTratamientosConsulta(List<ConsultaTratamientoDTO> tratamientosConsulta) {
        this.tratamientosConsulta = tratamientosConsulta;
    }
}

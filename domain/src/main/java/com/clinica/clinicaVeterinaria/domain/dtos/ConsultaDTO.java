package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaDTO {
    private int idConsulta;
    private String motivo;
    private String observaciones;
    private Date fechaCita;
    private Date fechaAlta;
    private Date fechaUltima;
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
        consultaDTO.setObservaciones(consulta.getObservaciones());
        consultaDTO.setMotivo(consulta.getMotivo());
        consultaDTO.setFechaCita(consulta.getFechaCita());
        consultaDTO.setFechaAlta(consulta.getFechaAlta());
        consultaDTO.setFechaUltima(consulta.getFechaUltima());
        consultaDTO.setMascota(consulta.getMascota() != null ? consulta.getMascota().getIdMascota() : null);

        /*if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(MascotaDTO.class)){
            consultaDTO.setMascota(MascotaDTO.toDTO(consulta.getMascota()));
        }*/
        /*if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(ConsultaTratamientoDTO.class)){
            consultaDTO.setTratamientosConsulta(ConsultaTratamientoDTO.toDTO(consulta.getTratamientosConsulta().stream().collect(Collectors.toList()), includeRelacion));
        }*/
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
        consulta.setMotivo(consultaDTO.getMotivo());
        consulta.setFechaCita(consultaDTO.getFechaCita());
        consulta.setFechaAlta(consultaDTO.getFechaAlta());
        consulta.setFechaUltima(consultaDTO.getFechaUltima());
        Mascota mascota = new Mascota();
        mascota.setIdMascota(consultaDTO.getMascota());
        consulta.setMascota(mascota);


        //consulta.setMascota(MascotaDTO.toDomain(consultaDTO.getMascota()));
        //consulta.setTratamientosConsulta(ConsultaTratamientoDTO.toDomain(consultaDTO.getTratamientosConsulta()).stream().collect(Collectors.toSet()));

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

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

    public List<ConsultaTratamientoDTO> getTratamientosConsulta() {
        return tratamientosConsulta;
    }

    public void setTratamientosConsulta(List<ConsultaTratamientoDTO> tratamientosConsulta) {
        this.tratamientosConsulta = tratamientosConsulta;
    }
}
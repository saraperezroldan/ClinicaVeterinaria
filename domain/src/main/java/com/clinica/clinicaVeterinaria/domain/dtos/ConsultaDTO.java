package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamientoID;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ConsultaDTO {
    private int idConsulta;
    private String motivo;
    private String diagnostico;
    private String observaciones;
    private int esCita;
    private LocalDate fechaCitaConsulta;
    private Date fechaAlta;
    private Date fechaUltima;
    private LocalTime horaCita; //TIME
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
        consultaDTO.setMotivo(StringUtils.hasText(consulta.getMotivo()) ? consulta.getMotivo().trim() : "");
        consultaDTO.setDiagnostico(StringUtils.hasText(consulta.getDiagnostico()) ? consulta.getDiagnostico().trim() : "");
        consultaDTO.setObservaciones(StringUtils.hasText(consulta.getObservaciones()) ? consulta.getObservaciones().trim() : "");
        consultaDTO.setEsCita(consulta.getEsCita());
        consultaDTO.setFechaCitaConsulta(consulta.getFechaCitaConsulta()!= null ? consulta.getFechaCitaConsulta() : null);
        consultaDTO.setHoraCita(consulta.getHoraCita()!= null ? consulta.getHoraFormateada(LocalTime.now()) : null);
        consultaDTO.setFechaAlta(consulta.getFechaAlta()!= null ? consulta.getFechaAlta() : new Date());
        consultaDTO.setFechaUltima(consulta.getFechaUltima()!= null ? consulta.getFechaUltima() : null);
        consultaDTO.setIdVeterinario(consulta.getIdVeterinario() > 0 ? consulta.getIdVeterinario() : 1003);
        consultaDTO.setMascota(consulta.getMascota() != null ? consulta.getMascota().getIdMascota() : null);
        Set<ConsultaTratamiento> tratamientos = consulta.getTratamientosConsulta();
        if (!tratamientos.isEmpty() && tratamientos.size() > 0) {
            List<ConsultaTratamientoDTO> tratamientosDTO = tratamientos.stream()
                    .map(tratamiento -> {
                        ConsultaTratamientoDTO dto = new ConsultaTratamientoDTO();

                        dto.setIdTratamiento(tratamiento.getId().getIdTratamiento());
                        dto.setIdConsulta(tratamiento.getId().getIdConsulta());
                        return dto;
                    })
                    .collect(Collectors.toList());

            consultaDTO.setTratamientosConsulta(tratamientosDTO);
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

        if (consultaDTO == null){
            return null;
        }

        consulta.setIdConsulta(consultaDTO.getIdConsulta());
        consulta.setMotivo(StringUtils.hasText(consultaDTO.getMotivo()) ? consultaDTO.getMotivo().trim() : "");
        consulta.setDiagnostico(StringUtils.hasText(consultaDTO.getDiagnostico()) ? consultaDTO.getDiagnostico().trim() : "");
        consulta.setObservaciones(StringUtils.hasText(consultaDTO.getObservaciones()) ? consultaDTO.getObservaciones().trim() : "");
        consulta.setEsCita(consultaDTO.getEsCita());
        consulta.setFechaCitaConsulta(consultaDTO.getFechaCitaConsulta());
        consulta.setHoraCita(consultaDTO.getHoraCita() != null ? consultaDTO.getHoraCita() : consultaDTO.getHoraFormateada(LocalTime.now()));
        consulta.setFechaAlta(consultaDTO.getFechaAlta());
        consulta.setFechaUltima(consultaDTO.getFechaUltima());
        consulta.setIdVeterinario(consultaDTO.getIdVeterinario());
        Mascota mascota = new Mascota();
        mascota.setIdMascota(consultaDTO.getMascota());
        consulta.setMascota(mascota);
        List<ConsultaTratamientoDTO> tratamientosDTO = consultaDTO.getTratamientosConsulta();
        if (tratamientosDTO != null) {
            Set<ConsultaTratamiento> tratamientos = tratamientosDTO.stream()
                    .map(tratamientoDTO -> {
                        ConsultaTratamiento tratamiento = new ConsultaTratamiento();
                        ConsultaTratamientoID id = new ConsultaTratamientoID();
                        id.setIdTratamiento(tratamientoDTO.getIdTratamiento());
                        id.setIdConsulta(tratamientoDTO.getIdConsulta());
                        tratamiento.setId(id);
                        return tratamiento;
                    })
                    .collect(Collectors.toSet());
            consulta.setTratamientosConsulta(tratamientos);
        } else {
            consulta.setTratamientosConsulta(new HashSet<>());
        }

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

    public int getIdConsulta() {return idConsulta;}
    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}
    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
    public int getEsCita() {return esCita;}
    public void setEsCita(int esCita) {this.esCita = esCita;}
    public LocalDate getFechaCitaConsulta() {return fechaCitaConsulta;}
    public void setFechaCitaConsulta(LocalDate fechaCitaConsulta) {this.fechaCitaConsulta = fechaCitaConsulta;}
    public LocalTime getHoraCita() {return horaCita;}
    public void setHoraCita(LocalTime horaCita) {this.horaCita = horaCita;}
    public Date getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}
    public Date getFechaUltima() {return fechaUltima;}
    public void setFechaUltima(Date fechaUltima) {this.fechaUltima = fechaUltima;}
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
    public LocalTime getHoraFormateada (LocalTime horaCita) {
        if (horaCita != null) {
            int hora = horaCita.getHour();
            int minutos = horaCita.getMinute();
            if (minutos > 0 && minutos <= 15) {
                minutos = 15;
            } else if (minutos > 15 && minutos <= 30) {
                minutos = 30;
            } else if (minutos > 30 && minutos <= 45) {
                minutos = 45;
            } else if (minutos > 45 && minutos <= 59) {
                minutos = 0;
                if (hora == 23) {
                    hora = 00;
                } else {
                    hora = hora + 1;
                }
            }
            return LocalTime.of(hora, minutos, 0);
        }
        return LocalTime.now();
    }
}
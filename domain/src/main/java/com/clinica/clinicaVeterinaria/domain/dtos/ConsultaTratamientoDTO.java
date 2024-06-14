package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConsultaTratamientoDTO {

    private int idConsultaTratamiento;
    private float importe;
    private ConsultaDTO consulta;
    private TratamientoDTO tratamiento;

    public static ConsultaTratamientoDTO toDTO(ConsultaTratamiento consultaTratamiento){
        return ConsultaTratamientoDTO.toDTO(consultaTratamiento, Arrays.asList(ConsultaDTO.class, TratamientoDTO.class));
    }

    public static ConsultaTratamientoDTO toDTO(ConsultaTratamiento consultaTratamiento, List<Class<?>> includeRelacion){
        ConsultaTratamientoDTO consultaTratamientoDTO = new ConsultaTratamientoDTO();

        if(consultaTratamiento == null){
            return consultaTratamientoDTO;
        }

        consultaTratamientoDTO.setIdConsultaTratamiento(consultaTratamiento.getIdConsultaTratamiento());
        consultaTratamientoDTO.setImporte(consultaTratamiento.getImporte());
        if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(ConsultaDTO.class)){
            consultaTratamientoDTO.setConsulta(ConsultaDTO.toDTO(consultaTratamiento.getConsulta()));
        }
        if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(TratamientoDTO.class)){
            consultaTratamientoDTO.setTratamiento(TratamientoDTO.toDTO(consultaTratamiento.getTratamiento()));
        }

        return consultaTratamientoDTO;
    }

    public static List<ConsultaTratamientoDTO> toDTO(List<ConsultaTratamiento> consultaTratamientos){
        if(consultaTratamientos == null){
            return Collections.emptyList();
        }

        return consultaTratamientos.stream()
                .map(ConsultaTratamientoDTO::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public static List<ConsultaTratamientoDTO> toDTO(List<ConsultaTratamiento> consultaTratamientos, List<Class<?>> includeRelacion){
        if(consultaTratamientos == null){
            return Arrays.asList();
        }

        return consultaTratamientos.stream()
                .map(consultaTratamiento -> ConsultaTratamientoDTO.toDTO(consultaTratamiento, includeRelacion))
                .collect(java.util.stream.Collectors.toList());

    }

    public static ConsultaTratamiento toDomain(ConsultaTratamientoDTO consultaTratamientoDTO){
        ConsultaTratamiento consultaTratamiento = new ConsultaTratamiento();

        if(consultaTratamientoDTO == null){
            return null;
        }

        consultaTratamiento.setIdConsultaTratamiento(consultaTratamientoDTO.getIdConsultaTratamiento());
        consultaTratamiento.setImporte(consultaTratamientoDTO.getImporte());
        consultaTratamiento.setConsulta(ConsultaDTO.toDomain(consultaTratamientoDTO.getConsulta()));
        consultaTratamiento.setTratamiento(TratamientoDTO.toDomain(consultaTratamientoDTO.getTratamiento()));

        return consultaTratamiento;
    }

    public static List<ConsultaTratamiento> toDomain(List<ConsultaTratamientoDTO> consultaTratamientosDTO){
        if(consultaTratamientosDTO == null){
            return Arrays.asList();
        }

        return consultaTratamientosDTO.stream()
                .map(ConsultaTratamientoDTO-> toDomain(ConsultaTratamientoDTO))
                .collect(java.util.stream.Collectors.toList());
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

    public ConsultaDTO getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDTO consulta) {
        this.consulta = consulta;
    }

    public TratamientoDTO getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(TratamientoDTO tratamiento) {
        this.tratamiento = tratamiento;
    }
}

package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamientoID;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConsultaTratamientoDTO {
    private int idConsulta;
    private int idTratamiento;
    private float importe;

    public static ConsultaTratamientoDTO toDTO(ConsultaTratamiento consultaTratamiento){
        return ConsultaTratamientoDTO.toDTO(consultaTratamiento, Arrays.asList(ConsultaDTO.class, TratamientoDTO.class));
    }

    public static ConsultaTratamientoDTO toDTO(ConsultaTratamiento consultaTratamiento, List<Class<?>> includeRelacion){
        ConsultaTratamientoDTO consultaTratamientoDTO = new ConsultaTratamientoDTO();

        if (consultaTratamiento == null){
            return consultaTratamientoDTO;
        }

        consultaTratamientoDTO.setIdConsulta(consultaTratamiento.getId().getIdConsulta());
        consultaTratamientoDTO.setIdTratamiento(consultaTratamiento.getId().getIdTratamiento());
        consultaTratamientoDTO.setImporte(consultaTratamiento.getImporte());

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
        ConsultaTratamientoID consultaTratamientoID = new ConsultaTratamientoID();

        if(consultaTratamientoDTO == null){
            return null;
        }
        consultaTratamientoID.setIdConsulta(consultaTratamientoDTO.getIdConsulta());
        consultaTratamientoID.setIdTratamiento(consultaTratamientoDTO.getIdTratamiento());
        consultaTratamiento.setId(consultaTratamientoID);
        consultaTratamiento.setImporte(consultaTratamientoDTO.getImporte());

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

    public int getIdConsulta() {return idConsulta;}

    public void setIdConsulta(int idConsulta) {this.idConsulta = idConsulta;}

    public int getIdTratamiento() {return idTratamiento;}

    public void setIdTratamiento(int idTratamiento) {this.idTratamiento = idTratamiento;}

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

}

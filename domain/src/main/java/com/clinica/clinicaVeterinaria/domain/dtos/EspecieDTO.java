package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Especie;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EspecieDTO {
    private int idEspecie;
    private String nombre;

    public static EspecieDTO toDTO(Especie especie){
        EspecieDTO especieDTO = new EspecieDTO();

        if(especie == null)
            return especieDTO;

        especieDTO.setIdEspecie(especie.getIdEspecie());
        especieDTO.setNombre(especie.getNombre());

        return especieDTO;
    }

    public static List<EspecieDTO> toDTO(List<Especie> especies){
        if(especies == null){
            return Collections.emptyList();
        }

        return especies.stream()
                .map(EspecieDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Especie toDomain(EspecieDTO especieDTO){
        Especie especie = new Especie();

        if(especieDTO == null){
            return null;
        }

        especie.setIdEspecie(especieDTO.getIdEspecie());
        especie.setNombre(especieDTO.getNombre());

        return especie;
    }

    public static List<Especie> toDomain(List<EspecieDTO> especiesDTO){
        if(especiesDTO == null){
            return Collections.emptyList();
        }

        return especiesDTO.stream()
                .map(EspecieDTO::toDomain)
                .collect(Collectors.toList());
    }



    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

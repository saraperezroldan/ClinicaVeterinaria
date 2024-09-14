package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RazaDTO {
    private int idRaza;
    private String nombre;
    private EspecieDTO especie;

    public static RazaDTO toDTO(Raza raza){
        return RazaDTO.toDTO(raza, Arrays.asList(EspecieDTO.class));
    }

    public static RazaDTO toDTO(Raza raza, List<Class<?>> includeRelacion){
        RazaDTO razaDTO = new RazaDTO();

        if(raza == null){
            return razaDTO;
        }

        razaDTO.setIdRaza(raza.getIdRaza());
        razaDTO.setNombre(raza.getNombre());
        if(!Collections.emptyList().equals(includeRelacion) && includeRelacion.contains(EspecieDTO.class)){
            razaDTO.setEspecie(EspecieDTO.toDTO(raza.getEspecie()));
        }
        return razaDTO;
    }

    public static List<RazaDTO> toDTO(List<Raza> razas){
        if(razas == null){
            return Collections.emptyList();
        }

        return razas.stream()
                .map(RazaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<RazaDTO> toDTO(List<Raza> razas, List<Class<?>> includeRelacion){
        if(razas == null){
            return Arrays.asList();
        }

        return razas.stream()
                .map(articulo -> RazaDTO.toDTO(articulo, includeRelacion))
                .collect(Collectors.toList());
    }

    public static Raza toDomain(RazaDTO razaDTO){
        Raza raza = new Raza();

        if(razaDTO == null){
            return null;
        }

        raza.setIdRaza(razaDTO.getIdRaza());
        raza.setNombre(razaDTO.getNombre());
        raza.setEspecie(EspecieDTO.toDomain(razaDTO.getEspecie()));

        return raza;
    }

    public static List<Raza> toDomain(List<RazaDTO> razasDTO){
        if(razasDTO == null){
            return Collections.emptyList();
        }

        return razasDTO.stream()
                .map(RazaDTO::toDomain)
                .collect(Collectors.toList());
    }

    public int getIdRaza() {return idRaza;}
    public void setIdRaza(int idRaza) {this.idRaza = idRaza;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
    public EspecieDTO getEspecie() {return especie;}
    public void setEspecie(EspecieDTO especie) {this.especie = especie;}
}
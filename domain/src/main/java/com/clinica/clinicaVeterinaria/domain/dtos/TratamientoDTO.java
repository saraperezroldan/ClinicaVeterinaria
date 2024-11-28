package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TratamientoDTO {
    private int idTratamiento;
    private String nombre;
    private String descripcion;
    private int esVacuna;
    private float precio;
    private int stock;

    public static TratamientoDTO toDTO(Tratamiento tratamiento){
        TratamientoDTO tratamientoDTO = new TratamientoDTO();

        if(tratamiento == null){
            return tratamientoDTO;
        }

        tratamientoDTO.setIdTratamiento(tratamiento.getIdTratamiento());
        tratamientoDTO.setNombre(tratamiento.getNombre());
        tratamientoDTO.setDescripcion(tratamiento.getDescripcion());
        tratamientoDTO.setEsVacuna(tratamiento.getEsVacuna());
        tratamientoDTO.setPrecio(tratamiento.getPrecio());
        tratamientoDTO.setPrecio(tratamiento.getPrecio());
        tratamientoDTO.setStock(tratamiento.getStock());

        return tratamientoDTO;
    }

    public static List<TratamientoDTO> toDTO(List<Tratamiento> tratamientos){
        if(tratamientos == null){
            return Collections.emptyList();
        }

        return tratamientos.stream()
                .map(TratamientoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Tratamiento toDomain(TratamientoDTO tratamientoDTO){
        Tratamiento tratamiento = new Tratamiento();

        if(tratamientoDTO == null){
            return null;
        }

        tratamiento.setIdTratamiento(tratamientoDTO.getIdTratamiento());
        tratamiento.setNombre(tratamientoDTO.getNombre());
        tratamiento.setDescripcion(tratamientoDTO.getDescripcion());
        tratamiento.setEsVacuna(tratamientoDTO.getEsVacuna());
        tratamiento.setPrecio(tratamientoDTO.getPrecio());
        tratamiento.setStock(tratamientoDTO.getStock());

        return tratamiento;
    }

    public static List<Tratamiento> toDomain(List<TratamientoDTO> tratamientosDTO){
        if(tratamientosDTO == null){
            return Collections.emptyList();
        }

        return tratamientosDTO.stream()
                .map(TratamientoDTO::toDomain)
                .collect(Collectors.toList());
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }
    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getEsVacuna() {return esVacuna;}
    public void setEsVacuna(int esVacuna) {this.esVacuna = esVacuna;}
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
}
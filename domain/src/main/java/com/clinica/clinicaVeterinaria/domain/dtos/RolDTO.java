package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RolDTO {
    private int idRol;
    private String nombre;
    private String descripcion;

    public static RolDTO toDTO(Rol rol)  {
        RolDTO rolDTO = new RolDTO();

        if (rol == null) {
            return rolDTO;
        }

        rolDTO.setIdRol(Math.max(rol.getIdRol(), 0));
        rolDTO.setNombre(StringUtils.hasText(rol.getNombre()) ? rol.getNombre().trim() : "");
        rolDTO.setDescripcion(StringUtils.hasText(rol.getDescripcion()) ? rol.getDescripcion().trim() : "");

        return rolDTO;
    }

    public static List<RolDTO> toDTO(List<Rol> tipoRoles) {
        if (tipoRoles == null) {
            return Collections.emptyList();
        }

        return tipoRoles.stream()
                .map(RolDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Rol toDomain(RolDTO rolDTO) {
        Rol rol = new Rol();

        if (rolDTO == null) {
            return null;
        }
        rol.setIdRol(Math.max(rolDTO.getIdRol(), 0));
        rol.setNombre(StringUtils.hasText(rolDTO.getNombre()) ? rolDTO.getNombre().trim() : "");
        rol.setDescripcion(StringUtils.hasText(rolDTO.getDescripcion()) ? rolDTO.getDescripcion().trim() : "");

        return rol;
    }

    public static List<Rol> toDomain(List<RolDTO> tipoRolesDTO) {
        if (tipoRolesDTO == null) {
            return Collections.emptyList();
        }

        return tipoRolesDTO.stream()
                .map(RolDTO::toDomain)
                .collect(Collectors.toList());
    }

    //----------------------------------------------------------------------------------------------
    public int getIdRol() {return idRol;}
    public void setIdRol(int idRol) {this.idRol = idRol;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}

package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MascotaDTO {
    private int idMascota;
    private String nombre;
    private int edad;
    private float peso;
    private String genero;
    private String complexion;
    private String imagen;
    private int activo;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
    private RazaDTO raza;
    private UsuarioDTO usuario;

    public MascotaDTO(){}

    public static MascotaDTO toDTO(Mascota mascota){
        return MascotaDTO.toDTO(mascota, Arrays.asList(RazaDTO.class, UsuarioDTO.class));
    }
    public static MascotaDTO toDTO(Mascota mascota, List<Class<?>> includeRelacion) {
        MascotaDTO mascotaDTO = new MascotaDTO();

        if(mascota == null)
            return mascotaDTO;

        mascotaDTO.setIdMascota(Math.max(mascota.getIdMascota(), 0));
        mascotaDTO.setNombre(StringUtils.hasText(mascota.getNombre()) ? mascota.getNombre().trim() : "");
        mascotaDTO.setEdad(Math.max(mascota.getEdad(), 0));
        mascotaDTO.setPeso(mascota.getPeso() > 0 ? mascota.getPeso() : 0);
        mascotaDTO.setGenero(StringUtils.hasText(mascota.getGenero()) ? mascota.getGenero().trim() : "");
        mascotaDTO.setComplexion(StringUtils.hasText(mascota.getComplexion()) ? mascota.getComplexion().trim() : "");
        mascotaDTO.setImagen(StringUtils.hasText(mascota.getImagen()) ? mascota.getImagen().trim() : "");
        mascotaDTO.setFechaNacimiento(mascota.getFechaNacimiento());
        mascotaDTO.setActivo(Math.max(mascota.getActivo(), 0));
        mascotaDTO.setFechaAlta(mascota.getFechaAlta()!= null ? mascota.getFechaAlta() : null);
        mascotaDTO.setFechaModificacion(mascota.getFechaModificacion()!= null ? mascota.getFechaBaja() : null);
        mascotaDTO.setFechaBaja(mascota.getFechaBaja() != null ? mascota.getFechaBaja() : null);

        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(RazaDTO.class)){
            mascotaDTO.setRaza(RazaDTO.toDTO(mascota.getRaza()));
        }
        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(UsuarioDTO.class)){
            mascotaDTO.setUsuario(UsuarioDTO.toDTO(mascota.getUsuario()));
        }
        return mascotaDTO;
    }

    public static List<MascotaDTO> toDTO(List<Mascota> mascotas) {
        if (mascotas == null) {
            return Collections.emptyList();
        }

        return mascotas.stream()
                .map(MascotaDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<MascotaDTO> toDTO(List<Mascota> mascotas, List<Class<?>> includeRelacion){
        if(mascotas == null){
            return Arrays.asList();
        }

        return mascotas.stream()
                .map(mascota -> MascotaDTO.toDTO(mascota, includeRelacion))
                .collect(Collectors.toList());
    }

    public static Mascota toDomain(MascotaDTO mascotaDTO) {
        Mascota mascota = new Mascota();

        if (mascotaDTO == null) {
            return null;
        }

        mascota.setIdMascota(Math.max(mascotaDTO.getIdMascota(), 0));
        mascota.setNombre(StringUtils.hasText(mascotaDTO.getNombre()) ? mascotaDTO.getNombre().trim() : "");
        mascota.setEdad(Math.max(mascotaDTO.getEdad(), 0));
        mascota.setPeso(mascotaDTO.getPeso() > 0 ? mascotaDTO.getPeso() : 0);
        mascota.setGenero(StringUtils.hasText(mascotaDTO.getGenero()) ? mascotaDTO.getGenero().trim() : "");
        mascota.setComplexion(StringUtils.hasText(mascotaDTO.getComplexion()) ? mascotaDTO.getComplexion().trim() : "");
        mascota.setImagen(StringUtils.hasText(mascotaDTO.getImagen()) ? mascotaDTO.getImagen().trim() : "");
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento() != null ? mascotaDTO.getFechaNacimiento() : null);
        mascota.setActivo(Math.max(mascotaDTO.getActivo(), 1));
        mascota.setFechaAlta(mascotaDTO.getFechaAlta()!= null ? mascotaDTO.getFechaAlta() : null);
        mascota.setFechaModificacion(mascotaDTO.getFechaModificacion() != null ? mascotaDTO.getFechaModificacion() : null);
        mascota.setFechaBaja(mascotaDTO.getFechaBaja()!= null ? mascotaDTO.getFechaBaja() : null);

        mascota.setRaza(RazaDTO.toDomain(mascotaDTO.getRaza()));
        mascota.setUsuario(UsuarioDTO.toDomain(mascotaDTO.getUsuario()));

        return mascota;
    }

    public static List<Mascota> toDomain(List<MascotaDTO> mascotasDTO) {
        if (mascotasDTO == null) {
            return Arrays.asList();
        }

        return mascotasDTO.stream()
                .map(MascotaDTO::toDomain)
                .collect(Collectors.toList());
    }

    public int getIdMascota() {return idMascota;}
    public void setIdMascota(int idMascota) {this.idMascota = idMascota;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
    public String getGenero() {return genero;}
    public void setGenero(String genero) {this.genero = genero;}
    public String getComplexion() {return complexion;}
    public void setComplexion(String complexion) {this.complexion = complexion;}
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}
    public Date getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento(Date fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public int getActivo() {return activo;}
    public void setActivo(int activo) {this.activo = activo;}
    public Date getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}
    public Date getFechaModificacion() {return fechaModificacion;}
    public void setFechaModificacion(Date fechaModificacion) {this.fechaModificacion = fechaModificacion;}
    public Date getFechaBaja() {return fechaBaja;}
    public void setFechaBaja(Date fechaBaja) {this.fechaBaja = fechaBaja;}
    public UsuarioDTO getUsuario() {return usuario;}
    public void setUsuario(UsuarioDTO usuario) {this.usuario = usuario;}
    public RazaDTO getRaza() {return raza;}
    public void setRaza(RazaDTO raza) {this.raza = raza;}
}
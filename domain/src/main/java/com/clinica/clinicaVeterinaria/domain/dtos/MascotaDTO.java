package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

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
    private boolean genero;
    private String complexion;
    private String urlImagen;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private Especie especie;
    private Usuario usuario;

    public static MascotaDTO toDTO(Mascota mascota){
        return MascotaDTO.toDTO(mascota, Arrays.asList(EspecieDTO.class, UsuarioDTO.class));
    }

    public static MascotaDTO toDTO(Mascota mascota, List<Class<?>> includeRelacion) {
        MascotaDTO mascotaDTO = new MascotaDTO();

        if(mascota == null)
            return mascotaDTO;

        mascotaDTO.setIdMascota(mascota.getIdMascota());
        mascotaDTO.setNombre(mascota.getNombre());
        mascotaDTO.setEdad(mascota.getEdad());
        mascotaDTO.setPeso(mascota.getPeso());
        mascotaDTO.setGenero(mascota.isGenero());
        mascotaDTO.setComplexion(mascota.getComplexion());
        mascotaDTO.setUrlImagen(mascota.getUrlImagen());
        mascotaDTO.setFechaNacimiento(mascota.getFechaNacimiento());
        mascotaDTO.setFechaAlta(mascota.getFechaAlta());
        mascotaDTO.setEspecie(mascota.getEspecie());
        mascotaDTO.setUsuario(mascota.getUsuario());

        /*if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(EspecieDTO.class)){
            mascotaDTO.setEspecie(EspecieDTO.toDTO(mascota.getEspecie()));
        }*/
        /*if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(UsuarioDTO.class)){
            mascotaDTO.setUsuario(UsuarioDTO.toDTO(mascota.getUsuario()));
        }*/
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

        mascota.setIdMascota(mascotaDTO.getIdMascota());
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setPeso(mascotaDTO.getPeso());
        mascota.setGenero(mascotaDTO.isGenero());
        mascota.setComplexion(mascotaDTO.getComplexion());
        mascota.setUrlImagen(mascotaDTO.getUrlImagen());
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        mascota.setFechaAlta(mascotaDTO.getFechaAlta());
        //mascota.setEspecie(EspecieDTO.toDomain(mascotaDTO.getEspecie()));
        //mascota.setUsuario(UsuarioDTO.toDomain(mascotaDTO.getUsuario()));

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

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

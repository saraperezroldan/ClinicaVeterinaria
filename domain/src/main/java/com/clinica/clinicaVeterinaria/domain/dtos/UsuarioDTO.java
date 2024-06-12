package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {

    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String imagen;
    private Date fechaAlta;
    private RolDTO rol;

    public static UsuarioDTO toDTO(Usuario usuario){
        return UsuarioDTO.toDTO(usuario, Arrays.asList(RolDTO.class));
    }
    public static UsuarioDTO toDTO(Usuario usuario, List<Class<?>> includeRelacion) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        if (usuario == null) {
            return usuarioDTO;
        }

        usuarioDTO.setIdUsuario(Math.max(usuario.getIdUsuario(), 0));
        usuarioDTO.setNombre(StringUtils.hasText(usuario.getNombre()) ? usuario.getNombre().trim() : "");
        usuarioDTO.setApellidos(StringUtils.hasText(usuario.getApellidos()) ? usuario.getApellidos().trim() : "");
        usuarioDTO.setDni(StringUtils.hasText(usuario.getDni()) ? usuario.getDni().trim() : "");
        usuarioDTO.setEmail(StringUtils.hasText(usuario.getEmail()) ? usuario.getEmail().trim() : "");
        usuarioDTO.setPassword(StringUtils.hasText(usuario.getPassword()) ? usuario.getPassword().trim() : "");
        usuarioDTO.setTelefono(StringUtils.hasText(usuario.getTelefono()) ? usuario.getTelefono().trim() : "");
        usuarioDTO.setDireccion(StringUtils.hasText(usuario.getDireccion()) ? usuario.getDireccion().trim() : "");
        usuarioDTO.setPoblacion(StringUtils.hasText(usuario.getCiudad()) ? usuario.getCiudad().trim() : "");
        usuarioDTO.setProvincia(StringUtils.hasText(usuario.getProvincia()) ? usuario.getProvincia().trim() : "");
        usuarioDTO.setCodigoPostal(StringUtils.hasText(usuario.getCodigoPostal()) ? usuario.getCodigoPostal().trim() : "");
        usuarioDTO.setImagen(StringUtils.hasText(usuario.getImagen()) ? usuario.getImagen().trim() : "");
        usuarioDTO.setFechaAlta(usuario.getFechaAlta());
        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(RolDTO.class)){
            usuarioDTO.setRol(RolDTO.toDTO(usuario.getRol()));
        }
        return usuarioDTO;
    }

    public static List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
        if (usuarios == null) {
            return Collections.emptyList();
        }

        return usuarios.stream()
                .map(UsuarioDTO::toDTO)
                .collect(Collectors.toList());
    }
    public static Usuario toDomain(UsuarioDTO usuarioDTO) {
        Usuario usuario = null;

        if (usuarioDTO == null) {
            return null;
        }
        usuario.setIdUsuario(Math.max(usuarioDTO.getIdUsuario(), 0));
        usuario.setNombre(StringUtils.hasText(usuarioDTO.getNombre()) ? usuarioDTO.getNombre().trim() : "");
        usuario.setApellidos(StringUtils.hasText(usuarioDTO.getApellidos()) ? usuarioDTO.getApellidos().trim() : "");
        usuario.setDni(StringUtils.hasText(usuarioDTO.getDni()) ? usuarioDTO.getDni().trim() : "");
        usuario.setEmail(StringUtils.hasText(usuarioDTO.getEmail()) ? usuarioDTO.getEmail().trim() : "");
        usuario.setCiudad(StringUtils.hasText(usuarioDTO.getPoblacion()) ? usuarioDTO.getPoblacion().trim() : "");
        usuario.setPassword(StringUtils.hasText(usuarioDTO.getPassword()) ? usuarioDTO.getPassword().trim() : "");
        usuario.setTelefono(StringUtils.hasText(usuarioDTO.getTelefono()) ? usuarioDTO.getTelefono().trim() : "");
        usuario.setDireccion(StringUtils.hasText(usuarioDTO.getDireccion()) ? usuarioDTO.getDireccion().trim() : "");
        usuario.setCiudad(StringUtils.hasText(usuarioDTO.getPoblacion()) ? usuarioDTO.getPoblacion().trim() : "");
        usuario.setProvincia(StringUtils.hasText(usuarioDTO.getProvincia()) ? usuarioDTO.getProvincia().trim() : "");
        usuario.setCodigoPostal(StringUtils.hasText(usuarioDTO.getCodigoPostal()) ? usuarioDTO.getCodigoPostal().trim() : "");
        usuario.setImagen(StringUtils.hasText(usuarioDTO.getImagen()) ? usuarioDTO.getImagen().trim() : "");
        usuario.setFechaAlta(usuarioDTO.getFechaAlta());
        usuario.setRol(RolDTO.toDomain(usuarioDTO.getRol()));

        return usuario;
    }

    public static List<Usuario> toDomain(List<UsuarioDTO> usuariosDTO) {
        if (usuariosDTO == null) {
            return Collections.emptyList();
        }

        return usuariosDTO.stream()
                .map(UsuarioDTO::toDomain)
                .collect(Collectors.toList());
    }

    //------------------------------------------------------------------------------------------------------------------
    public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public String getPoblacion() {return poblacion;}
    public void setPoblacion(String poblacion) {this.poblacion = poblacion;}
    public String getProvincia() {return provincia;}
    public void setProvincia(String provincia) {this.provincia = provincia;}
    public String getCodigoPostal() {return codigoPostal;}
    public void setCodigoPostal(String codigoPostal) {this.codigoPostal = codigoPostal;}
    public String getImagen() {return imagen;}
    public void setImagen(String imagenURL) {this.imagen = imagenURL;}
    public Date getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}
    public RolDTO getRol() {return rol;}
    public void setRol(RolDTO rol) {this.rol = rol;}
}

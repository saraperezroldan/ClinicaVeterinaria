package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.business.rol.IRolRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRolRepository rolRepository;

    @Value("${dir.imagenes}")
    private Path directorio;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findUsuariosActivos();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        existenUsuarios(usuarios);
        usuarios.forEach(usuario -> usuariosDTO.add(UsuarioDTO.toDTO(usuario)));

        return usuariosDTO;
    }

    @Override
    public UsuarioDTO getUsuarioById(int idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findUsuarioById(idUsuario);
        existeUsuario(usuarioEncontrado);

        return UsuarioDTO.toDTO(usuarioEncontrado);
    }

    @Override
    public List<UsuarioDTO> getUsuariosByIdRol(int idRol) {
        List<Usuario> usuarios = usuarioRepository.findUsuariosByIdRol(idRol);
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        existenUsuarios(usuarios);
        usuarios.forEach(usuario -> usuariosDTO.add(UsuarioDTO.toDTO(usuario)));

        return usuariosDTO;
    }

    @Override
    public UsuarioDTO getUsuarioByEmail(String email) {
        Usuario usuarioEncontrado = null;

        if (StringUtils.hasText(email)) {
            usuarioEncontrado = usuarioRepository.findUsuarioByEmail(email);
            if (usuarioEncontrado != null) {
                return UsuarioDTO.toDTO(usuarioEncontrado);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoEmail");
        }
    }

    @Override
    public UsuarioDTO getUsuarioByDni(String dni) {
        Usuario usuarioEncontrado = null;

        if (StringUtils.hasText(dni)) {
            usuarioEncontrado = usuarioRepository.findUsuarioByDni(dni);
            if (usuarioEncontrado != null) {
                return UsuarioDTO.toDTO(usuarioEncontrado);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoDNI");
        }
    }

    @Override
    public PageableResult<UsuarioDTO> getUsuarioConFiltro (UsuarioFiltroDTO filtro) {
        List<Usuario> usuarios = usuarioRepository.findUsuarioPorFiltro(filtro);
        int resultMax = usuarioRepository.getResultMax(filtro);
        List<UsuarioDTO> usuariosDTOs = UsuarioDTO.toDTO(usuarios);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,usuariosDTOs);
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioNuevo = UsuarioDTO.toDomain(usuarioDTO);

        Usuario usuarioEncontrado = usuarioRepository.findUsuarioById(usuarioDTO.getIdUsuario());
        if (usuarioEncontrado != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.yaExisteUsuario");
        }
        validarUsuario(usuarioNuevo);
        usuarioNuevo.setFechaAlta(new Date());
        usuarioRepository.save(usuarioNuevo);

        return UsuarioDTO.toDTO(usuarioNuevo);
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioUpdate = UsuarioDTO.toDomain(usuarioDTO);
        Usuario usuarioEncontrado = usuarioRepository.findUsuarioById(usuarioDTO.getIdUsuario());

        existeUsuario(usuarioEncontrado);
        validarUsuario(usuarioUpdate);

        usuarioUpdate.setFechaModificacion(new Date());
        usuarioRepository.save(usuarioUpdate);

        return UsuarioDTO.toDTO(usuarioUpdate);
    }

    @Override
    public UsuarioDTO eliminarUsuario(int idUsuario) {
        Usuario usuarioBorrar = usuarioRepository.findUsuarioById(idUsuario);

        existeUsuario(usuarioBorrar);
        if (usuarioBorrar.getActivo() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.yaEliminado");
        }
        usuarioBorrar.setActivo(0);
        usuarioBorrar.setFechaBaja(new Date());
        usuarioRepository.save(usuarioBorrar);

        return UsuarioDTO.toDTO(usuarioBorrar);
    }

    @Override
    public UsuarioDTO cambiarFoto(int idUsuario, MultipartFile image) {
        Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
        if (usuario == null)  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
        }
        try {
            String filename = "/images/" + image.getOriginalFilename();
            Files.createDirectories(Paths.get(String.valueOf(directorio)));

            Path path = Paths.get(directorio + filename);
            Files.write(path, image.getBytes());

            usuario.setImagen(filename);
            usuario.setFechaModificacion(new Date());
            usuarioRepository.save(usuario);

            return UsuarioDTO.toDTO(usuario);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }

    private void existeUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontrado");
        }
    }
    private void existenUsuarios(List<Usuario> usuarios) {
        if (usuarios == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontradoListado");
        }
    }
    private void validarUsuario(Usuario usuario) {
        if (!StringUtils.hasText(usuario.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoNombre");
        }
        if (usuario.getNombre().length() > Constantes.USUARIO_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxNombre");
        }
        if (!StringUtils.hasText(usuario.getApellidos())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoApellidos");
        }
        if (usuario.getApellidos().length() > Constantes.USUARIO_APELLIDOS_MAX) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxApellidos");
        }
        if (!StringUtils.hasText(usuario.getDni())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoDNI");
        }
        if (usuario.getDni().length() > Constantes.USUARIO_DNI_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxDNI");
        }
        if (!StringUtils.hasText(usuario.getDireccion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoDireccion");
        }
        if (usuario.getDireccion().length() > Constantes.USUARIO_DIRECCION_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxDireccion");
        }
        Rol rol = rolRepository.findById(usuario.getRol().getIdRol()).orElse(null);
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontradoRol");
        }
    }

}

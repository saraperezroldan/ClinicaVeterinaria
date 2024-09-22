package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.utils.Constantes;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = null;
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuarios = usuarioRepository.findUsuariosActivos();

        if (usuarios != null && !usuarios.isEmpty()) {
            usuarios.forEach(usuario -> usuariosDTO.add(UsuarioDTO.toDTO(usuario)));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO getUsuarioById(int idUsuario) {
        Usuario usuarioEncontrado = null;

        if (idUsuario > 0) {
            usuarioEncontrado  =  usuarioRepository.findUsuarioById(idUsuario);
            if (usuarioEncontrado != null) {
                return UsuarioDTO.toDTO(usuarioEncontrado);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
            }
        } else  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código del usuario debe ser mayor que cero.");
        }
    }

    @Override
    public List<UsuarioDTO> getUsuariosByIdRol(int idRol) {
        List<Usuario> usuarios = null;
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuarios = usuarioRepository.findUsuariosByIdRol(idRol);

        if (usuarios != null && !usuarios.isEmpty()) {
            usuarios.forEach(usuario -> usuariosDTO.add(UsuarioDTO.toDTO(usuario)));
        }
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
    public PageableResult<UsuarioDTO> getUsuarioConFiltro (UsuarioFiltroDTO filtro) {
        List<Usuario> usuarios = usuarioRepository.findUsuarioPorFiltro(filtro);
        int resultMax = usuarioRepository.getResultMax(filtro);
        List<UsuarioDTO> usuariosDTOs = UsuarioDTO.toDTO(usuarios);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,usuariosDTOs);
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioNuevo = UsuarioDTO.toDomain(usuarioDTO);
        validarUsuario(usuarioNuevo, 1);

        Usuario findUsuario = usuarioRepository.findUsuarioById(usuarioDTO.getIdUsuario());
        if (findUsuario != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.yaExisteUsuario");
        }
        usuarioNuevo.setFechaAlta(new Date());
        usuarioRepository.save(usuarioNuevo);

        return UsuarioDTO.toDTO(usuarioNuevo);
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioUpdate = usuarioRepository.findUsuarioById(usuarioDTO.getIdUsuario());

        existeUsuario(usuarioUpdate);
        validarUsuario(usuarioUpdate, 2);

        usuarioUpdate.setNombre(usuarioDTO.getNombre());
        usuarioUpdate.setApellidos(usuarioDTO.getApellidos());
        usuarioUpdate.setDireccion(usuarioDTO.getDireccion());
        usuarioUpdate.setTelefono(usuarioDTO.getTelefono());
        usuarioUpdate.setDni(usuarioDTO.getDni());
        usuarioUpdate.setFechaModificacion(new Date());

        usuarioRepository.save(usuarioUpdate);

        return UsuarioDTO.toDTO(usuarioUpdate);
    }

    @Override
    public UsuarioDTO eliminarUsuario(int idUsuario) {
        Usuario usuarioBorrar = usuarioRepository.findUsuarioById(idUsuario);

        if (usuarioBorrar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
        }
        usuarioBorrar.setActivo(0);
        usuarioBorrar.setFechaBaja(new Date());
        usuarioRepository.save(usuarioBorrar);

        return UsuarioDTO.toDTO(usuarioBorrar);
    }

    private void existeUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontrado");
        }
    }
    private void validarUsuario(Usuario usuario, int tipo) {
        if (tipo == 1) {
            if (usuario.getIdUsuario() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoIdUsuario");
            }
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
            if (usuario.getRol().getIdRol() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.rolNoEncontrado");
            }
        } else if (tipo == 2) {
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
        }
    }

}

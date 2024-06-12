package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = null;
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuarios = usuarioRepository.findAll();

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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado.");
            }
        } else  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código del usuario debe ser mayor que cero.");
        }
    }

    @Override
    public PageableResult<UsuarioDTO> filtradoUsuario(UsuarioFiltroDTO filtro) {
        List<Usuario> usuarios = usuarioRepository.findUsuarioPorFiltro(filtro);
        int resultMax = usuarioRepository.getResultMax(filtro);
        List<UsuarioDTO> canalesComercialesDTOs = UsuarioDTO.toDTO(usuarios);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,canalesComercialesDTOs);
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioNuevo = UsuarioDTO.toDomain(usuarioDTO);
        validarUsuario(usuarioNuevo);

        Usuario usuarioOld = usuarioRepository.findUsuarioById(usuarioNuevo.getIdUsuario());
        if (usuarioOld != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.yaExisteUsuario");
        }
        usuarioRepository.save(usuarioNuevo);

        return UsuarioDTO.toDTO(usuarioNuevo);
    }
    
    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioEditar = UsuarioDTO.toDomain(usuarioDTO);
        validarUsuario(usuarioEditar);

        Usuario usuarioOld = usuarioRepository.findUsuarioById(usuarioEditar.getIdUsuario());
        existeUsuario(usuarioOld);
        usuarioRepository.save(usuarioEditar);

        return UsuarioDTO.toDTO(usuarioEditar);
    }

    @Override
    public UsuarioDTO eliminarUsuario(int idUsuario) {
        Usuario usuarioBorrar = usuarioRepository.findUsuarioById(idUsuario);
        validarUsuario(usuarioBorrar);

        //usuarioBorrar.setActivo(0);
        //usuario.fechaModificacion(new Date());
        usuarioRepository.save(usuarioBorrar);

        return UsuarioDTO.toDTO(usuarioBorrar);
    }

    private void existeUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontrado");
        }
    }
    private void validarUsuario(Usuario usuario) {
    }

}

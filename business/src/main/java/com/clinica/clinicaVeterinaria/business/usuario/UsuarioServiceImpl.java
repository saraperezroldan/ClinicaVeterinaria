package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepositoryImpl;
    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = null;
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuarios = usuarioRepositoryImpl.findAll();

        if (usuarios != null && !usuarios.isEmpty()) {
            usuarios.forEach(usuario -> usuariosDTO.add(UsuarioDTO.toDTO(usuario)));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO getUsuarioPorId(int idUsuario) {
        UsuarioDTO usuarioDTO = null;
        Usuario usuarioEncontrado = null;

        if (idUsuario > 0) {
            //usuarioEncontrado =  usuarioRepositoryImpl.findByCodigoUsuario(idUsuario);
            if (usuarioEncontrado != null) {
                usuarioDTO = UsuarioDTO.toDTO(usuarioEncontrado);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado.");
            }
        } else  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código del usuario debe ser mayor que cero.");
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO anadirUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO eliminarUsuario(int idUsuario) {
        return null;
    }


}

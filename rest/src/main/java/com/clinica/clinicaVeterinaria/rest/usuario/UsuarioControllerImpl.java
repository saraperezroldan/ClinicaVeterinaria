package com.clinica.clinicaVeterinaria.rest.usuario;

import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioService;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UsuarioControllerImpl implements IUsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @Override
    public UsuarioDTO getUsuarioPorId(int idUsuario) throws URISyntaxException {
        return usuarioService.getUsuarioPorId(idUsuario);
    }

    @Override
    public UsuarioDTO anadirUsuario(UsuarioDTO usuarioDTO) {
       return usuarioService.anadirUsuario (usuarioDTO);

    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        return  usuarioService.modificarUsuario (usuarioDTO);
    }

    @Override
    public UsuarioDTO eliminarUsuario(int idUsuario) {
        return usuarioService.eliminarUsuario(idUsuario);
    }
}

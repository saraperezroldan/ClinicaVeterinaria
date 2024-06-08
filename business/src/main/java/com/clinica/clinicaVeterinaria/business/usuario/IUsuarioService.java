package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO getUsuarioPorId(int idUsuario);

    UsuarioDTO anadirUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO eliminarUsuario(int idUsuario);
}

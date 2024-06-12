package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> getUsuarios();
    UsuarioDTO getUsuarioById(int idUsuario);
    PageableResult<UsuarioDTO> filtradoUsuario (UsuarioFiltroDTO filtro);
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO eliminarUsuario(int idUsuario);
}

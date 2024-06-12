package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;

import java.util.List;

public interface IUsuarioRepositoryCustom {
    List<Usuario> findUsuarioPorFiltro(UsuarioFiltroDTO filtro);

    int getResultMax(UsuarioFiltroDTO filtro);
}

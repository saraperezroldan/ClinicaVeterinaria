
package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;

import java.util.List;

public interface IMascotaRepositoryCustom {

    List<Mascota> findMascotaPorFiltro(MascotaFiltroDTO filtro);

    int getResultMax(MascotaFiltroDTO filtro);
}
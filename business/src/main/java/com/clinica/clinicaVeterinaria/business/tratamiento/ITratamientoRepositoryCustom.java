package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.filtros.TratamientoFiltroDTO;
import java.util.List;

public interface ITratamientoRepositoryCustom {
    List<Tratamiento> findTratamientosPorFiltro(TratamientoFiltroDTO filtro);
    int getResultMax(TratamientoFiltroDTO filtro);
}

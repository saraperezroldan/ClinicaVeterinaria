package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import java.util.List;

public interface IConsultaRepositoryCustom {
    List<Consulta> findConsultasPorFiltro(ConsultaFiltroDTO filtro);
    int getResultMax(ConsultaFiltroDTO filtro);
}
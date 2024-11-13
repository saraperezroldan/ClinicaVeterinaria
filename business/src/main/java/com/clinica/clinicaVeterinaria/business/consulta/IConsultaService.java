package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;

import java.util.List;

public interface IConsultaService {

    List<ConsultaDTO> getConsultasByIdMascota(int idMascota);
    List<ConsultaDTO> getCitasByIdMascota(int idMascota);
    ConsultaDTO getConsultaById(int idConsulta);
    PageableResult<ConsultaDTO> getConsultasConFiltro(ConsultaFiltroDTO filtro);
    ConsultaDTO crearConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO eliminarConsulta(int idConsulta);
}
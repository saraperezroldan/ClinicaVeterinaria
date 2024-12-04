package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;

import java.util.List;

public interface IConsultaService {

    ConsultaDTO getCitaById(int idCita);
    ConsultaDTO getConsultaById(int idConsulta);
    List<ConsultaDTO> getCitasByIdMascota(int idMascota);
    List<ConsultaDTO> getConsultasByIdMascota(int idMascota);
    List<ConsultaDTO> getVacunasByIdMascota (int idMascota);
    List<ConsultaDTO> getCitasByIdVeterinario (int idVeterinario);
    PageableResult<ConsultaDTO> getCitasConFiltro(ConsultaFiltroDTO filtro);
    PageableResult<ConsultaDTO> getConsultasConFiltro(ConsultaFiltroDTO filtro);
    ConsultaDTO crearCita(ConsultaDTO citaDTO);
    ConsultaDTO crearConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO modificarCita(ConsultaDTO consultaDTO);
    ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO eliminarCita(int idCita);
    ConsultaDTO eliminarConsulta(int idConsulta);
}
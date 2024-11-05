package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import java.util.List;

public interface IConsultaService {

    List<ConsultaDTO> getConsultasByIdMascota(int idMascota);
    List<ConsultaDTO> getCitasByIdMascota(int idMascota);
    ConsultaDTO getConsultaById(int idConsulta);
    ConsultaDTO crearConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO);
    ConsultaDTO eliminarConsulta(int idConsulta);

    //PageableResult<ConsultaDTO> getConsultasFiltrado(ConsultaDTO consultaDTO);
}
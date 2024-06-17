package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;

public interface IConsultaService {

    public ConsultaDTO getConsultaById(int idConsulta);

    public ConsultaDTO crearConsulta(ConsultaDTO consultaDTO);

    //PageableResult<ConsultaDTO> getConsultasFiltrado(ConsultaDTO consultaDTO);
}

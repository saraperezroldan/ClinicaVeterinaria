package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import java.util.List;

public interface IConsultaService {

    public List<ConsultaDTO> getConsultasByIdMascota(int idMascota);
    public ConsultaDTO getConsultaById(int idConsulta);
    public ConsultaDTO crearConsulta(ConsultaDTO consultaDTO);

    //PageableResult<ConsultaDTO> getConsultasFiltrado(ConsultaDTO consultaDTO);
}
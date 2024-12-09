package com.clinica.clinicaVeterinaria.business.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;

import java.util.List;

public interface IConsultaTratamientoService {
    List<ConsultaTratamientoDTO> getTratamientosByIdConsulta(int idConsulta);
    List<ConsultaTratamientoDTO> getConsultasByIdTratamiento(int idTratamiento);
}

package com.clinica.clinicaVeterinaria.business.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IConsultaTratamientoService {
    List<ConsultaTratamientoDTO> getTratamientosByIdConsulta(int idConsulta);
}

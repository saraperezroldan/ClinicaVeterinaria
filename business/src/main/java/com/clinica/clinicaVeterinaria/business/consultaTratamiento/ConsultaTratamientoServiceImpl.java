package com.clinica.clinicaVeterinaria.business.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaTratamientoServiceImpl implements IConsultaTratamientoService {
    @Autowired
    private IConsultaTratamientoRepository ctRepository;
    @Override
    public List<ConsultaTratamientoDTO> getTratamientosByIdConsulta(int idConsulta) {
        List<ConsultaTratamiento> consultatratamientos = ctRepository.findTratamientosByIdConsulta(idConsulta);
        List<ConsultaTratamientoDTO> consultatratamientosDTO = new ArrayList<>();

        if (consultatratamientos != null && !consultatratamientos.isEmpty()) {
            consultatratamientos.forEach(ct -> consultatratamientosDTO.add(ConsultaTratamientoDTO.toDTO(ct)));
        }
        return consultatratamientosDTO;
    }

}

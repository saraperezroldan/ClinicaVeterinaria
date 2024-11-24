package com.clinica.clinicaVeterinaria.business.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        existeConsultaTratamiento(consultatratamientos);
        consultatratamientos.forEach(ct -> consultatratamientosDTO.add(ConsultaTratamientoDTO.toDTO(ct)));

        return consultatratamientosDTO;
    }

    @Override
    public List<ConsultaTratamientoDTO> getConsultasByIdTratamiento(int idTratamiento) {
        List<ConsultaTratamiento> tratamientosconsultas = ctRepository.findConsultasByIdTratamiento(idTratamiento);
        List<ConsultaTratamientoDTO> tratamientosconsultasDTO = new ArrayList<>();
        existeConsultaTratamiento(tratamientosconsultas);
        tratamientosconsultas.forEach(ct -> tratamientosconsultasDTO.add(ConsultaTratamientoDTO.toDTO(ct)));

        return tratamientosconsultasDTO;
    }

    private void existeConsultaTratamiento(List<ConsultaTratamiento> lista) {
        if (lista.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consultaTratamiento.noEncontradoListado");
        }
    }

}

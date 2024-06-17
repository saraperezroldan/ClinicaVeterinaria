package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConsultaServiceImpl implements IConsultaService{

    @Autowired
    IConsultaRepository consultaRepository;

    @Override
    public ConsultaDTO getConsultaById(int idConsulta) {
        Consulta consultaEncontrada = null;

        consultaEncontrada = consultaRepository.findConsultaById(idConsulta);

        return ConsultaDTO.toDTO(consultaEncontrada, Arrays.asList(MascotaDTO.class, ConsultaTratamientoDTO.class));
    }

    @Override
    public ConsultaDTO crearConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaSaved = ConsultaDTO.toDomain(consultaDTO);
        consultaSaved = consultaRepository.save(consultaSaved);

        return ConsultaDTO.toDTO(consultaSaved);
    }
}

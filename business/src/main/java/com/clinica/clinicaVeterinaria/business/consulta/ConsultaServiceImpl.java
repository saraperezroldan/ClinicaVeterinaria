package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService{
    @Autowired
    IConsultaRepository consultaRepository;

    @Override
    public List<ConsultaDTO> getConsultasByIdMascota(int idMascota){
        List<Consulta> consultas = null;
        List<ConsultaDTO> consultasDTO = new ArrayList<>();
        consultas = consultaRepository.findConsultasByIdMascota(idMascota);

        if (consultas != null && !consultas.isEmpty()) {
            consultas.forEach(consulta -> consultasDTO.add(ConsultaDTO.toDTO(consulta)));
        }
        return consultasDTO;
    }
    @Override
    public ConsultaDTO getConsultaById(int idConsulta) {
        Consulta consultaEncontrada = null;
        consultaEncontrada = consultaRepository.findConsultaById(idConsulta);

        if (consultaEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEncontrado");
        }
        return ConsultaDTO.toDTO(consultaEncontrada);
    }

    @Override
    public ConsultaDTO crearConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaSaved = ConsultaDTO.toDomain(consultaDTO);
        consultaSaved = consultaRepository.save(consultaSaved);

        return ConsultaDTO.toDTO(consultaSaved);
    }

    @Override
    public ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaUpdate = consultaRepository.findConsultaById(consultaDTO.getIdConsulta());

        //existeConsulta(consultaUpdate);
        //validarConsulta(consultaUpdate, 2);
        consultaRepository.save(consultaUpdate);

        return consultaDTO.toDTO(consultaUpdate);
    }

    @Override
    public ConsultaDTO eliminarConsulta(int idConsulta) {
        Consulta consultaBorrar = consultaRepository.findConsultaById(idConsulta);

        if (consultaBorrar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEncontrado");
        }
        consultaRepository.save(consultaBorrar);

        return ConsultaDTO.toDTO(consultaBorrar);
    }
}
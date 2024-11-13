package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService{
    @Autowired
    IConsultaRepository consultaRepository;

    @Override
    public List<ConsultaDTO> getConsultasByIdMascota(int idMascota){
        List<Consulta> consultas = consultaRepository.findConsultasByIdMascota(idMascota);
        List<ConsultaDTO> consultasDTO = new ArrayList<>();

        if (consultas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
        }
        consultas.forEach(consulta -> consultasDTO.add(ConsultaDTO.toDTO(consulta)));

        return consultasDTO;
    }

    @Override
    public List<ConsultaDTO> getCitasByIdMascota(int idMascota) {
        List<Consulta> consultas = consultaRepository.findCitasByIdMascota(idMascota);
        List<ConsultaDTO> consultasDTO = new ArrayList<>();

        if (consultas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
        }
        consultas.forEach(consulta -> consultasDTO.add(ConsultaDTO.toDTO(consulta)));

        return consultasDTO;
    }

    @Override
    public ConsultaDTO getConsultaById(int idConsulta) {
        Consulta consultaEncontrada = consultaRepository.findConsultaById(idConsulta);

        if (consultaEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEncontrado");
        }
        return ConsultaDTO.toDTO(consultaEncontrada);
    }

    @Override
    public PageableResult<ConsultaDTO> getConsultasConFiltro(ConsultaFiltroDTO filtro) {
        List<Consulta> consultas = consultaRepository.findConsultasPorFiltro(filtro);
        int resultMax = consultaRepository.getResultMax(filtro);
        List<ConsultaDTO> consultasDTOs = ConsultaDTO.toDTO(consultas);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,consultasDTOs);
    }

    @Override
    public ConsultaDTO crearConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaNueva = ConsultaDTO.toDomain(consultaDTO);

        Consulta consultaEncontrada = consultaRepository.findConsultaById(consultaDTO.getIdConsulta());
        if (consultaEncontrada != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.yaExisteConsulta");
        }
        validarConsulta(consultaNueva);
        consultaNueva.setFechaAlta(new Date());
        consultaRepository.save(consultaNueva);

        return ConsultaDTO.toDTO(consultaNueva);
    }

    @Override
    public ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaUpdate = ConsultaDTO.toDomain(consultaDTO);
        Consulta consultaEncontrada = consultaRepository.findConsultaById(consultaDTO.getIdConsulta());

        existeConsulta(consultaEncontrada);
        validarConsulta(consultaUpdate);
        consultaRepository.save(consultaUpdate);

        return consultaDTO.toDTO(consultaUpdate);
    }

    @Override
    public ConsultaDTO eliminarConsulta(int idConsulta) {
        Consulta consultaBorrar = consultaRepository.findConsultaById(idConsulta);

        if (consultaBorrar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEncontrado");
        }
        consultaRepository.delete(consultaBorrar);

        return ConsultaDTO.toDTO(consultaBorrar);
    }

    private void existeConsulta (Consulta consulta) {
        if (consulta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrada");
        }
    }
    private void validarConsulta(Consulta consulta) {
        LocalDate fechaCita = consulta.getFechaCita();
        LocalDate fechaActual = LocalDate.now();
        if (fechaCita.isBefore(fechaActual)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.fechaCitaPasada");
        }

    }
}
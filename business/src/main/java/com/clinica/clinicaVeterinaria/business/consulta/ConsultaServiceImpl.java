package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.business.consultaTratamiento.IConsultaTratamientoRepository;
import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ConsultaServiceImpl implements IConsultaService{
    @Autowired
    IConsultaRepository consultaRepository;
    @Autowired
    IConsultaTratamientoRepository ctRepository;
    @Autowired
    ITratamientoRepository tratamientoRepository;

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
        List<Consulta> citas = consultaRepository.findCitasByIdMascota(idMascota);
        List<ConsultaDTO> citasDTO = new ArrayList<>();

        if (citas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
        }
        citas.forEach(cita -> citasDTO.add(ConsultaDTO.toDTO(cita)));

        return citasDTO;
    }

    @Override
    public List<ConsultaDTO> getVacunasByIdMascota(int idMascota) {
        List<Consulta> vacunas = consultaRepository.findCitasByIdMascota(idMascota);
        List<ConsultaDTO> vacunasDTO = new ArrayList<>();

        if (vacunas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
        }
        /*for (Consulta v: vacunas) {
            List<ConsultaTratamiento> vacunasct = ctRepository.findTratamientosByIdConsulta(v.getIdConsulta());
            if (vacunasct == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consultaTratamiento.noEncontrado");
            }
            for (ConsultaTratamiento ct : vacunasct) {
                Tratamiento tratamiento = tratamientoRepository.findTratamientoById(ct.getId().getIdTratamiento());
                if (tratamiento == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
                }
                if (tratamiento.getEsVacuna() == 1) {
                    vacunas.forEach(vacuna -> vacunasDTO.add(ConsultaDTO.toDTO(vacuna)));
                }
            }
        }*/
        return vacunasDTO;
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
    public ConsultaDTO getCitaById(int idCita) {
        Consulta consultaEncontrada = consultaRepository.findCitaById(idCita);

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
        consultaNueva.setEsCita(0);
        consultaNueva.setFechaAlta(new Date());
        consultaRepository.save(consultaNueva);

        return ConsultaDTO.toDTO(consultaNueva);
    }

    @Override
    public ConsultaDTO crearCita(ConsultaDTO citaDTO) {
        Consulta citaNueva = ConsultaDTO.toDomain(citaDTO);

        Consulta citaEncontrada = consultaRepository.findConsultaById(citaDTO.getIdConsulta());
        if (citaEncontrada != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.yaExisteConsulta");
        }

        validarCita(citaNueva);
        citaNueva.setEsCita(1);
        citaNueva.setFechaAlta(new Date());
        consultaRepository.save(citaNueva);

        return ConsultaDTO.toDTO(citaNueva);
    }
    @Override
    public ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaUpdate = ConsultaDTO.toDomain(consultaDTO);
        Consulta consultaEncontrada = consultaRepository.findConsultaById(consultaDTO.getIdConsulta());

        existeConsulta(consultaEncontrada);
        //validarConsulta(consultaUpdate);
        consultaUpdate.setEsCita(0);
        consultaUpdate.setFechaUltima(new Date());
        Set<ConsultaTratamiento> tratamientos = consultaUpdate.getTratamientosConsulta();
        if (tratamientos != null) {
            for (ConsultaTratamiento t : tratamientos) {
                int idConsulta = consultaDTO.getIdConsulta();
                int idTratamiento = t.getId().getIdTratamiento();
                Tratamiento tratamiento = tratamientoRepository.findTratamientoById(idTratamiento);
                Consulta consulta = consultaRepository.findConsultaById(idConsulta);
                if (consulta == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
                }
                if (tratamiento == null){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
                }
                ConsultaTratamientoDTO nuevoTratamiento = new ConsultaTratamientoDTO(idConsulta, idTratamiento );
                ConsultaTratamiento ct = ConsultaTratamientoDTO.toDomain(nuevoTratamiento);
                ctRepository.save(ct);
            }

        }
        //List<Tratamiento> tratamientos =
        consultaRepository.save(consultaUpdate);

        return consultaDTO.toDTO(consultaUpdate);
    }

    @Override
    public ConsultaDTO eliminarConsulta(int idConsulta) {
        Consulta consultaBorrar = consultaRepository.findConsultaById(idConsulta);

        if (consultaBorrar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrado");
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
        if (!StringUtils.hasText(consulta.getDiagnostico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoDiagnostico");
        }
    }
    private void validarCita(Consulta cita) {
        LocalDate fechaCita = cita.getFechaCitaConsulta();
        LocalDate fechaActual = LocalDate.now();
        if (fechaCita.isBefore(fechaActual)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.fechaCitaPasada");
        }
        if (cita.getIdVeterinario() == 0) {

        }
        if (!StringUtils.hasText(cita.getMotivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoMotivo");
        }
    }
}
package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.business.consultaTratamiento.IConsultaTratamientoRepository;
import com.clinica.clinicaVeterinaria.business.mascota.IMascotaRepository;
import com.clinica.clinicaVeterinaria.business.rol.IRolRepository;
import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoRepository;
import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.*;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ConsultaServiceImpl implements IConsultaService {
    @Autowired
    IConsultaRepository consultaRepository;
    @Autowired
    IMascotaRepository mascotaRepository;
    @Autowired
    IConsultaTratamientoRepository ctRepository;
    @Autowired
    ITratamientoRepository tratamientoRepository;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    IRolRepository rolRepository;

    @Override
    public ConsultaDTO getCitaById(int idCita) {
        validaIdConsultaCita(idCita);
        Consulta citaEncontrada = consultaRepository.findCitaById(idCita);
        existeConsultaCita(citaEncontrada);

        return ConsultaDTO.toDTO(citaEncontrada);
    }
    @Override
    public ConsultaDTO getConsultaById(int idConsulta) {
        validaIdConsultaCita(idConsulta);
        Consulta consultaEncontrada = consultaRepository.findConsultaById(idConsulta);
        existeConsultaCita(consultaEncontrada);

        return ConsultaDTO.toDTO(consultaEncontrada);
    }

    @Override
    public List<ConsultaDTO> getCitasByIdMascota(int idMascota) {
        validaIdMascota(idMascota);
        List<Consulta> citas = consultaRepository.findCitasByIdMascota(idMascota);
        List<ConsultaDTO> citasDTO = new ArrayList<>();
        Mascota mascota = mascotaRepository.findMascotaById(idMascota);
        existeMascota(mascota);
        existeConsultasCitasVacunas(citas);

        citas.forEach(cita -> citasDTO.add(ConsultaDTO.toDTO(cita)));

        return citasDTO;
    }

    @Override
    public List<ConsultaDTO> getConsultasByIdMascota(int idMascota){
        validaIdMascota(idMascota);
        List<ConsultaDTO> consultasDTO = new ArrayList<>();
        List<Consulta> consultas = consultaRepository.findConsultasByIdMascota(idMascota);
        Mascota mascota = mascotaRepository.findMascotaById(idMascota);
        existeMascota(mascota);
        existeConsultasCitasVacunas(consultas);

        consultas.forEach(consulta -> consultasDTO.add(ConsultaDTO.toDTO(consulta)));

        return consultasDTO;
    }

    @Override
    public List<ConsultaDTO> getVacunasByIdMascota(int idMascota) {
        validaIdMascota(idMascota);
        List<Consulta> vacunas = consultaRepository.findCitasConVacunasByIdMascota(idMascota);
        List<ConsultaDTO> vacunasDTO = new ArrayList<>();
        Mascota mascota = mascotaRepository.findMascotaById(idMascota);
        existeMascota(mascota);
        existeConsultasCitasVacunas(vacunas);

        vacunas.forEach(vacuna -> vacunasDTO.add(ConsultaDTO.toDTO(vacuna)));

        return vacunasDTO;
    }

    @Override
    public List<ConsultaDTO> getCitasByIdVeterinario(int idVeterinario, LocalDate fechaConcreta) {
        validaCitasVeterinario(idVeterinario, fechaConcreta);
        List<Consulta> citas = consultaRepository.findCitasByIdVeterinario(idVeterinario, fechaConcreta);
        List<ConsultaDTO> citasDTO = new ArrayList<>();

        citas.forEach(cita -> citasDTO.add(ConsultaDTO.toDTO(cita)));

        return citasDTO;
    }

    @Override
    public PageableResult<ConsultaDTO> getConsultasConFiltro(ConsultaFiltroDTO filtro) {
        List<Consulta> consultas = consultaRepository.findConsultasPorFiltro(filtro);
        existeConsultasCitasVacunas(consultas);

        int resultMax = consultaRepository.getResultMax(filtro);
        List<ConsultaDTO> consultasDTOs = ConsultaDTO.toDTO(consultas);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,consultasDTOs);
    }

    @Override
    public PageableResult<ConsultaDTO> getCitasConFiltro(ConsultaFiltroDTO filtro) {
        List<Consulta> citas = consultaRepository.findCitasPorFiltro(filtro);

        existeConsultasCitasVacunas(citas);

        int resultMax = consultaRepository.getResultMaxCitas(filtro);
        List<ConsultaDTO> consultasDTOs = ConsultaDTO.toDTO(citas);

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
        consultaNueva.setFechaCitaConsulta(LocalDate.now());
        consultaNueva.setHoraCita(consultaDTO.getHoraFormateada(LocalTime.now()));
        consultaNueva.setFechaAlta(new Date());
        consultaRepository.save(consultaNueva);

        return ConsultaDTO.toDTO(consultaNueva);
    }

    @Override
    public ConsultaDTO modificarConsulta(ConsultaDTO consultaDTO) {
        Consulta consultaUpdate = ConsultaDTO.toDomain(consultaDTO);
        Consulta consultaEncontrada = consultaRepository.findConsultaById(consultaDTO.getIdConsulta());
        existeConsultaCita(consultaEncontrada);

        consultaUpdate.setFechaUltima(new Date());
        Set<ConsultaTratamiento> constratamientos = new HashSet<>();
        Set<ConsultaTratamiento> tratamientos = consultaUpdate.getTratamientosConsulta();
        if (tratamientos != null) {
            for (ConsultaTratamiento t : tratamientos) {
                int idConsulta = consultaDTO.getIdConsulta();
                int idTratamiento = t.getId().getIdTratamiento();
                ConsultaTratamiento encontrado = ctRepository.existeConsultaTratamiento(idConsulta, idTratamiento);
                if (encontrado != null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consultaTratamiento.yaExisteConsultaTratamiento");
                }
                Tratamiento tratamiento = tratamientoRepository.findTratamientoById(idTratamiento);
                Consulta consulta = consultaRepository.findConsultaById(idConsulta);
                existeConsultaCita(consulta);
                if (tratamiento == null){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
                }
                ConsultaTratamientoID consultaTratamientoID = new ConsultaTratamientoID(idConsulta,idTratamiento);
                ConsultaTratamiento consultaTratamiento = new ConsultaTratamiento();
                consultaTratamiento.setConsulta(consultaUpdate);
                consultaTratamiento.setTratamiento(tratamiento);
                consultaTratamiento.setId(consultaTratamientoID);
                ctRepository.save(consultaTratamiento);
                constratamientos.add(consultaTratamiento);
            }
        }
        consultaUpdate.setTratamientosConsulta(constratamientos);
        consultaRepository.save(consultaUpdate);

        return consultaDTO.toDTO(consultaUpdate);
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
    public ConsultaDTO convertirCitaConsulta(ConsultaDTO citaDTO) {
        Consulta citaUpdate = ConsultaDTO.toDomain(citaDTO);
        int idCitaConsulta = citaDTO.getIdConsulta();
        Consulta citaEncontrada = consultaRepository.findCitaById(idCitaConsulta);
        existeConsultaCita(citaEncontrada);
        validarConsulta(citaUpdate);
        citaUpdate.setEsCita(0);
        citaUpdate.setMotivo(citaEncontrada.getMotivo());
        citaUpdate.setFechaCitaConsulta(LocalDate.now());
        citaUpdate.setHoraCita(LocalTime.now());
        citaUpdate.setFechaUltima(new Date());
        Set<ConsultaTratamiento> constratamientos = new HashSet<>();
        Set<ConsultaTratamiento> tratamientos = citaUpdate.getTratamientosConsulta();
        if (tratamientos != null) {
            for (ConsultaTratamiento t : tratamientos) {
                int idTratamiento = t.getId().getIdTratamiento();
                ConsultaTratamiento encontrado = ctRepository.existeConsultaTratamiento(idCitaConsulta, idTratamiento);
                if (encontrado != null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consultaTratamiento.yaExisteConsultaTratamiento");
                }
                Tratamiento tratamiento = tratamientoRepository.findTratamientoById(idTratamiento);
                if (tratamiento == null){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
                }
                ConsultaTratamientoID consultaTratamientoID = new ConsultaTratamientoID(idCitaConsulta ,idTratamiento);
                ConsultaTratamiento consultaTratamiento = new ConsultaTratamiento();
                consultaTratamiento.setConsulta(citaUpdate);
                consultaTratamiento.setTratamiento(tratamiento);
                consultaTratamiento.setId(consultaTratamientoID);
                ctRepository.save(consultaTratamiento);
                constratamientos.add(consultaTratamiento);
            }
        }
        citaUpdate.setTratamientosConsulta(constratamientos);
        consultaRepository.save(citaUpdate);

        return citaDTO.toDTO(citaUpdate);
    }

    @Override
    public ConsultaDTO modificarCita(ConsultaDTO citaDTO) {
        Consulta citaUpdate = ConsultaDTO.toDomain(citaDTO);
        Consulta citaEncontrada = consultaRepository.findCitaById(citaDTO.getIdConsulta());

        existeConsultaCita(citaEncontrada);
        validarCita(citaUpdate);
        citaUpdate.setEsCita(1);
        citaUpdate.setFechaUltima(new Date());
        consultaRepository.save(citaUpdate);

        return citaDTO.toDTO(citaUpdate);
    }

    @Override
    public ConsultaDTO eliminarCita(int idCita) {
        Consulta citaBorrar = consultaRepository.findCitaById(idCita);
        existeConsultaCita(citaBorrar);

        List<ConsultaTratamiento> consultaTratamientos = ctRepository.findTratamientosByIdConsulta(idCita);
        if (!consultaTratamientos.isEmpty()){
            for (ConsultaTratamiento tratamiento: consultaTratamientos) {
                ctRepository.delete(tratamiento);
            }
        }
        consultaRepository.delete(citaBorrar);

        return ConsultaDTO.toDTO(citaBorrar);
    }
    @Override
    public ConsultaDTO eliminarConsulta(int idConsulta) {
        Consulta consultaBorrar = consultaRepository.findConsultaById(idConsulta);
        existeConsultaCita(consultaBorrar);
        List<ConsultaTratamiento> consultaTratamientos = ctRepository.findTratamientosByIdConsulta(idConsulta);
        if (!consultaTratamientos.isEmpty()){
            for (ConsultaTratamiento tratamiento: consultaTratamientos) {
                ctRepository.delete(tratamiento);
            }
        }
        consultaRepository.delete(consultaBorrar);

        return ConsultaDTO.toDTO(consultaBorrar);
    }

    private void existeConsultaCita (Consulta consulta) {
        if (consulta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontrada");
        }
    }

    private void existeMascota (Mascota mascota) {
        if (mascota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontrada");
        }
    }

    private void existeConsultasCitasVacunas (List<Consulta> lista) {
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "consulta.noEncontradoListado");
        }
    }
    private void validaIdConsultaCita (int idConsulta) {
        if (idConsulta <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noValidoIdConsulta");
        }
    }
    private void validaIdMascota (int idMascota) {
        if (idMascota <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noValidoIdMascota");
        }
    }
    private void validaCitasVeterinario (int idVeterinario, LocalDate fechaConcreta) {
        if (idVeterinario <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noValidoIdVeterinario");
        }
        Usuario veterinario = usuarioRepository.findUsuarioById(idVeterinario);
        if (veterinario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEncontradoVeterinario");
        }
        int rol = veterinario.getRol().getIdRol();
        if (rol == Constantes.ROL_CLIENTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noEsVeterinarioNiAdmin");
        }
        LocalDate fechaActual = LocalDate.now();
        if (fechaConcreta != null && fechaConcreta.isBefore(fechaActual)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.fechaCitaPasada");
        }
    }

    private void validarConsulta(Consulta consulta) {
        if (!StringUtils.hasText(consulta.getDiagnostico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoDiagnostico");
        }
        if (consulta.getIdVeterinario() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoIdVeterinario");
        }
        int idRol = usuarioRepository.findUsuarioById(consulta.getIdVeterinario()).getRol().getIdRol();
        Rol rol = rolRepository.findRolById(idRol);
        if (rol.getIdRol() == Constantes.ROL_CLIENTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.noValidoIdCliente");
        }
    }

    private void validarCita(Consulta cita) {
        LocalDate fechaCita = cita.getFechaCitaConsulta();
        LocalDate fechaActual = LocalDate.now();
        if (fechaCita == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridaFechaCita");
        }
        if (fechaCita.isBefore(fechaActual)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.fechaCitaPasada");
        }
        if (cita.getHoraCita() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoHoraCita");
        }
        if (cita.getIdVeterinario() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoIdVeterinario");
        }
        if (!StringUtils.hasText(cita.getMotivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "consulta.requeridoMotivo");
        }
    }

}
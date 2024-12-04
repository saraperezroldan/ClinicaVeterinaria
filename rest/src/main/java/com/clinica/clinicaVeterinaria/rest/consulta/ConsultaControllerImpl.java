package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.business.consulta.IConsultaService;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ConsultaControllerImpl implements IConsultaController {
    @Autowired
    IConsultaService consultaService;

    @Override
    public ResponseEntity<ConsultaDTO> getCitaById (int idCita) {
        return new ResponseEntity<>(consultaService.getCitaById(idCita), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> getConsultaById (int idConsulta) {
        return new ResponseEntity<>(consultaService.getConsultaById(idConsulta), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<ConsultaDTO>> getCitasByIdMascota(int idMascota) {
        return new ResponseEntity<>(consultaService.getCitasByIdMascota(idMascota), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<List<ConsultaDTO>> getConsultasByIdMascota (int idMascota) {
        return new ResponseEntity<>(consultaService.getConsultasByIdMascota(idMascota), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<ConsultaDTO>>getVacunasByIdMascota(int idMascota) {
        return new ResponseEntity<>(consultaService.getVacunasByIdMascota(idMascota), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<ConsultaDTO>> getCitasByIdVeterinario(int idVeterinario) {
        return new ResponseEntity<>(consultaService.getCitasByIdVeterinario(idVeterinario), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PageableResult<ConsultaDTO>> getCitasConFiltro(ConsultaFiltroDTO filtro) {
        return new ResponseEntity<>(consultaService.getCitasConFiltro(filtro), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PageableResult<ConsultaDTO>> getConsultasConFiltro(ConsultaFiltroDTO filtro) {
        return new ResponseEntity<>(consultaService.getConsultasConFiltro(filtro), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> crearCita(ConsultaDTO citaDTO) {
        return new ResponseEntity<>(consultaService.crearCita(citaDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> crearConsulta(ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.crearConsulta(consultaDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> modificarCita(ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.modificarCita(consultaDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> modificarConsulta(ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.modificarConsulta(consultaDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> eliminarCita(int idCita) {
        return new ResponseEntity<>(consultaService.eliminarCita(idCita), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ConsultaDTO> eliminarConsulta(int idConsulta) {
        return new ResponseEntity<>(consultaService.eliminarConsulta(idConsulta), HttpStatus.OK);
    }
}

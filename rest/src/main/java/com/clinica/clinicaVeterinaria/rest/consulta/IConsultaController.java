package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("consulta")
public interface IConsultaController {

    @GetMapping("getCitaById/{id}")
    ResponseEntity<ConsultaDTO> getCitaById(@PathVariable("id") int idCita);
    @GetMapping("getConsultaById/{id}")
    ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable("id") int idConsulta);
    @GetMapping("getCitasByIdMascota/{id}")
    ResponseEntity<List<ConsultaDTO>>getCitasByIdMascota(@PathVariable("id") int idMascota);
    @GetMapping("getConsultasByIdMascota/{id}")
    ResponseEntity<List<ConsultaDTO>>getConsultasByIdMascota(@PathVariable("id") int idMascota);
    @GetMapping("getVacunasByIdMascota/{id}")
    ResponseEntity<List<ConsultaDTO>> getVacunasByIdMascota(@PathVariable("id") int idMascota);
    @GetMapping("getCitasByIdVeterinario/{id}")
    ResponseEntity<List<ConsultaDTO>> getCitasByIdVeterinario(@PathVariable("id") int idVeterinario);
    @PostMapping("getCitasConFiltro")
    ResponseEntity<PageableResult<ConsultaDTO>> getCitasConFiltro (@RequestBody ConsultaFiltroDTO filtro);
    @PostMapping("getConsultasConFiltro")
    ResponseEntity<PageableResult<ConsultaDTO>> getConsultasConFiltro (@RequestBody ConsultaFiltroDTO filtro);
    @PostMapping("crearCita")
    ResponseEntity<ConsultaDTO> crearCita(@RequestBody ConsultaDTO citaDTO);
    @PostMapping("crearConsulta")
    ResponseEntity<ConsultaDTO> crearConsulta(@RequestBody ConsultaDTO consultaDTO);
    @PostMapping("modificarCita")
    ResponseEntity<ConsultaDTO> modificarCita(@RequestBody ConsultaDTO ConsultaDTO);
    @PostMapping("modificarConsulta")
    ResponseEntity<ConsultaDTO> modificarConsulta(@RequestBody ConsultaDTO ConsultaDTO);
    @DeleteMapping("eliminarCita/{id}")
    ResponseEntity<ConsultaDTO> eliminarCita(@PathVariable("id") int idCita);
    @DeleteMapping("eliminarConsulta/{id}")
    ResponseEntity<ConsultaDTO> eliminarConsulta(@PathVariable("id") int idConsulta);
}
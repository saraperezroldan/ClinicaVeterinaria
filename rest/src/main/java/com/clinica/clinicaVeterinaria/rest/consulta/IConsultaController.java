package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("consulta")
public interface IConsultaController {

    @GetMapping("getConsultasByIdMascota/{id}")
    ResponseEntity<List<ConsultaDTO>>getConsultasByIdMascota(@PathVariable("id") int idMascota);
    @GetMapping("getCitasByIdMascota/{id}")
    ResponseEntity<List<ConsultaDTO>>getCitasByIdMascota(@PathVariable("id") int idMascota);

    @GetMapping("getConsultaById/{id}")
    ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable("id") int idConsulta);

    @PostMapping("crearConsulta")
    ResponseEntity<ConsultaDTO> crearConsulta(@RequestBody ConsultaDTO consultaDTO);

    @PostMapping("modificarConsulta")
    ResponseEntity<ConsultaDTO> modificarConsulta(@RequestBody ConsultaDTO ConsultaDTO);

    @DeleteMapping("eliminarConsulta/{id}")
    ResponseEntity<ConsultaDTO> eliminarConsulta(@PathVariable("id") int idConsulta);
}
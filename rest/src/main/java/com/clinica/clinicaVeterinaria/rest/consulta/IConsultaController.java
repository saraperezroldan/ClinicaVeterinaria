package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("consulta")
public interface IConsultaController {

    @GetMapping("getConsultasByIdMascota/{idMascota}")
    public ResponseEntity<List<ConsultaDTO>>getConsultasByIdMascota(@PathVariable("idMascota") int idMascota);

    @GetMapping("getConsultaById/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable("id") int idConsulta);

    @PostMapping("crearConsulta")
    public ResponseEntity<ConsultaDTO> crearConsulta(@RequestBody ConsultaDTO consultaDTO);

}
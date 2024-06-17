package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("consulta")
public interface IConsultaController {

    //@GetMapping("getConsultas")

    @GetMapping("getConsultaById/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable("id") int idConsulta);

    @PostMapping("crearConsulta")
    public ResponseEntity<ConsultaDTO> crearConsulta(@RequestBody ConsultaDTO consultaDTO);

}

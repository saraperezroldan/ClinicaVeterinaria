package com.clinica.clinicaVeterinaria.rest.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("raza")
public interface IRazaController {
    @GetMapping("getRazas")
    ResponseEntity<List<RazaDTO>> getRazas();
    @GetMapping("getRazaById/{id}")
    ResponseEntity<RazaDTO> getRazaById(@PathVariable("id") int idRaza);
    @PostMapping("crearRaza")
    ResponseEntity<RazaDTO> crearRaza(@RequestBody RazaDTO RazaDTO);
    @PostMapping("modificarRaza")
    ResponseEntity<RazaDTO> modificarRaza(@RequestBody RazaDTO RazaDTO);
    @DeleteMapping("eliminarRaza/{id}")
    ResponseEntity<RazaDTO> eliminarRaza(@PathVariable("id") int idRaza);
}
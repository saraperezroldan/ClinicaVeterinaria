package com.clinica.clinicaVeterinaria.rest.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("especie")
public interface IEspecieController {
    @GetMapping("getEspecies")
    public ResponseEntity<List<EspecieDTO>> getEspecies();
    @GetMapping("getEspecieById/{id}")
    public ResponseEntity<EspecieDTO> getEspecieById(@PathVariable("id") int idEspecie);
    @PostMapping("crearEspecie")
    public ResponseEntity<EspecieDTO> crearEspecie(@RequestBody EspecieDTO EspecieDTO);
    @PostMapping("modificarEspecie")
    public ResponseEntity<EspecieDTO> modificarEspecie(@RequestBody EspecieDTO EspecieDTO);
    @DeleteMapping("eliminarEspecie/{id}")
    public ResponseEntity<EspecieDTO> eliminarEspecie(@PathVariable("id") int idEspecie);
}
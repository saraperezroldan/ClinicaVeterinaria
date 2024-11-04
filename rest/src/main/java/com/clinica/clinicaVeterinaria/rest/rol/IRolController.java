package com.clinica.clinicaVeterinaria.rest.rol;

import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("rol")
public interface IRolController {
    @GetMapping("getRoles")
    ResponseEntity<List<RolDTO>> getRoles();
    @GetMapping("getRolById/{id}")
    ResponseEntity<RolDTO> getRolById(@PathVariable("id") int idRol);
    @PostMapping("crearRol")
    ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO RolDTO);
    @PostMapping("modificarRol")
    ResponseEntity<RolDTO> modificarRol(@RequestBody RolDTO RolDTO);
    @DeleteMapping("eliminarRol/{id}")
    ResponseEntity<RolDTO> eliminarRol(@PathVariable("id") int idRol);
}
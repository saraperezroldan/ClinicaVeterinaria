
package com.clinica.clinicaVeterinaria.rest.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("raza")
public interface IRazaController {
    @GetMapping("getRazas")
    public ResponseEntity<List<RazaDTO>> getRazas();
}

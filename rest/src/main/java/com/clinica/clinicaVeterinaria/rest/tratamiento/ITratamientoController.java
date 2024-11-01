package com.clinica.clinicaVeterinaria.rest.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("tratamiento")
public interface ITratamientoController {
    @GetMapping("getTratamientos")
    public ResponseEntity<List<TratamientoDTO>> getTratamientos();

}

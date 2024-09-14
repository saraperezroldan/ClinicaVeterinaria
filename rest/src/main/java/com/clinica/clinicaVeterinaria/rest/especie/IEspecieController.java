package com.clinica.clinicaVeterinaria.rest.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("especie")
public interface IEspecieController {
    @GetMapping("getEspecies")
    public ResponseEntity<List<EspecieDTO>> getEspecies();
}
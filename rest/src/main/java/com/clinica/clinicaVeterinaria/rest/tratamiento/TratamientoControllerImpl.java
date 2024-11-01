package com.clinica.clinicaVeterinaria.rest.tratamiento;

import com.clinica.clinicaVeterinaria.business.consulta.IConsultaService;
import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoService;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.rest.consulta.IConsultaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TratamientoControllerImpl implements ITratamientoController {
    @Autowired
    ITratamientoService tratamientoService;

    @Override
    public ResponseEntity<List<TratamientoDTO>> getTratamientos() {
        return new ResponseEntity<>(tratamientoService.getTratamientos(), HttpStatus.OK);
    }
}

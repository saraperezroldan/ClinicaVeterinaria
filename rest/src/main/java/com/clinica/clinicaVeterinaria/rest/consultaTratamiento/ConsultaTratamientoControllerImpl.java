package com.clinica.clinicaVeterinaria.rest.consultaTratamiento;

import com.clinica.clinicaVeterinaria.business.consultaTratamiento.IConsultaTratamientoService;
import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoService;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultaTratamientoControllerImpl implements IConsultaTratamientoController{
    @Autowired
    IConsultaTratamientoService ctService;
    @Override
    public ResponseEntity<List<ConsultaTratamientoDTO>> getTratamientosByIdConsulta(int idConsulta) {
        return new ResponseEntity<>(ctService.getTratamientosByIdConsulta(idConsulta), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<ConsultaTratamientoDTO>> getConsultasByIdTratamiento(int idTratamiento) {
        return new ResponseEntity<>(ctService.getConsultasByIdTratamiento(idTratamiento), HttpStatus.OK);
    }
}


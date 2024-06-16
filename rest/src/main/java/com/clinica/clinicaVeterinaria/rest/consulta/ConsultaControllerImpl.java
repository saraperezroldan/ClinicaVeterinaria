package com.clinica.clinicaVeterinaria.rest.consulta;

import com.clinica.clinicaVeterinaria.business.consulta.IConsultaService;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaControllerImpl implements IConsultaController{

    @Autowired
    IConsultaService consultaService;

    @Override
    public ResponseEntity<ConsultaDTO> getConsultaById(int idConsulta) {
        return new ResponseEntity<>(consultaService.getConsultaById(idConsulta), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultaDTO> crearConsulta(ConsultaDTO consultaDTO) {
        return new ResponseEntity<>(consultaService.crearConsulta(consultaDTO), HttpStatus.OK);
    }
}

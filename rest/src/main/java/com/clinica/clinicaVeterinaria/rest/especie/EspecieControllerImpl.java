package com.clinica.clinicaVeterinaria.rest.especie;

import com.clinica.clinicaVeterinaria.business.especie.IEspecieService;
import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EspecieControllerImpl implements IEspecieController{

    @Autowired
    IEspecieService especieService;
    @Override
    public ResponseEntity<List<EspecieDTO>> getEspecies() {
        return new ResponseEntity<>(especieService.getEspecies(), HttpStatus.OK);
    }
}
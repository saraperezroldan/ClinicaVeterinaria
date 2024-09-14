
package com.clinica.clinicaVeterinaria.rest.raza;

import com.clinica.clinicaVeterinaria.business.raza.IRazaService;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RazaControllerImpl implements IRazaController{

    @Autowired
    IRazaService razaService;

    @Override
    public ResponseEntity<List<RazaDTO>> getRazas() {
        return new ResponseEntity<>(razaService.getRazas(), HttpStatus.OK);
    }
}

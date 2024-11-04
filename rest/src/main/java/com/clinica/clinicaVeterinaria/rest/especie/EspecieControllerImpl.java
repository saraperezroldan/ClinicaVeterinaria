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
    @Override
    public ResponseEntity<EspecieDTO> getEspecieById(int idEspecie) {
        return new ResponseEntity<>(especieService.getEspecieById(idEspecie), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<EspecieDTO> crearEspecie(EspecieDTO EspecieDTO) {
        return new ResponseEntity<>(especieService.crearEspecie(EspecieDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<EspecieDTO> modificarEspecie(EspecieDTO EspecieDTO) {
        return new ResponseEntity<>(especieService.modificarEspecie(EspecieDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<EspecieDTO> eliminarEspecie(int idEspecie) {
        return new ResponseEntity<>(especieService.eliminarEspecie(idEspecie), HttpStatus.OK);
    }
}
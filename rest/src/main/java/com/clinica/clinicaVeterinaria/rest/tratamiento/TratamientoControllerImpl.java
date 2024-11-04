package com.clinica.clinicaVeterinaria.rest.tratamiento;

import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoService;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
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
    @Override
    public ResponseEntity<TratamientoDTO> getTratamientoById(int idTratamiento) {
        return new ResponseEntity<>(tratamientoService.getTratamientoById(idTratamiento), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<TratamientoDTO> crearTratamiento(TratamientoDTO tratamientoDTO) {
        return new ResponseEntity<>(tratamientoService.crearTratamiento(tratamientoDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<TratamientoDTO> modificarTratamiento(TratamientoDTO tratamientoDTO) {
        return new ResponseEntity<>(tratamientoService.modificarTratamiento(tratamientoDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<TratamientoDTO> eliminarTratamiento(int idTratamiento) {
        return new ResponseEntity<>(tratamientoService.eliminarTratamiento(idTratamiento), HttpStatus.OK);
    }
}

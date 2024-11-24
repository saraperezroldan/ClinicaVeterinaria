package com.clinica.clinicaVeterinaria.rest.tratamiento;

import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoService;
import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.filtros.TratamientoFiltroDTO;
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
    public ResponseEntity<PageableResult<TratamientoDTO>> getTratamientosConFiltro(TratamientoFiltroDTO filtro) {
        return new ResponseEntity<>(tratamientoService.getTratamientosConFiltro(filtro), HttpStatus.OK);
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

package com.clinica.clinicaVeterinaria.rest.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("tratamiento")
public interface ITratamientoController {
    @GetMapping("getTratamientos")
    public ResponseEntity<List<TratamientoDTO>> getTratamientos();
    @GetMapping("getTratamientoById/{id}")
    public ResponseEntity<TratamientoDTO> getTratamientoById(@PathVariable("id") int idTratamiento);
    @PostMapping("crearTratamiento")
    public ResponseEntity<TratamientoDTO> crearTratamiento(@RequestBody TratamientoDTO TratamientoDTO);
    @PostMapping("modificarTratamiento")
    public ResponseEntity<TratamientoDTO> modificarTratamiento(@RequestBody TratamientoDTO TratamientoDTO);
    @DeleteMapping("eliminarTratamiento/{id}")
    public ResponseEntity<TratamientoDTO> eliminarTratamiento(@PathVariable("id") int idTratamiento);
}

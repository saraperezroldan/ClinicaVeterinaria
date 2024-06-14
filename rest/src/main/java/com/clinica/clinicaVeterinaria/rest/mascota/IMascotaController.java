package com.clinica.clinicaVeterinaria.rest.mascota;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("mascota")
public interface IMascotaController {
    @GetMapping("getMascotas")
    public ResponseEntity<List<MascotaDTO>> getMascotas();

    @GetMapping("getMascotaById/{id}")
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable("id") int idMascota);

    @PostMapping("crearMascota")
    public ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaDTO mascotaDTO);

    @PostMapping("modificarMascota")
    public ResponseEntity<MascotaDTO> modificarMascota(MascotaDTO mascotaDTO);

    @DeleteMapping("eliminarMascota/{id}")
    public ResponseEntity<MascotaDTO> eliminarMascota(@PathVariable("id") int idMascota);
}

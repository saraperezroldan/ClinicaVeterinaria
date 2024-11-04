package com.clinica.clinicaVeterinaria.rest.mascota;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("mascota")
public interface IMascotaController {
    @GetMapping("getMascotas")
    ResponseEntity<List<MascotaDTO>> getMascotas();

    @GetMapping("getMascotaById/{id}")
    ResponseEntity<MascotaDTO> getMascotaById(@PathVariable("id") int idMascota);

    @GetMapping("getMascotasByIdUsuario/{idUsuario}")
    ResponseEntity<List<MascotaDTO>> getMascotasByIdUsuario(@PathVariable("idUsuario") int idUsuario);

    @PostMapping("getMascotaConFiltro")
    ResponseEntity<PageableResult<MascotaDTO>> getMascotaConFiltro (@RequestBody MascotaFiltroDTO filtro);

    @PostMapping("crearMascota")
    ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaDTO mascotaDTO);

    @PostMapping("modificarMascota")
    ResponseEntity<MascotaDTO> modificarMascota(@RequestBody MascotaDTO mascotaDTO);

    @DeleteMapping("eliminarMascota/{id}")
    ResponseEntity<MascotaDTO> eliminarMascota(@PathVariable("id") int idMascota);
}


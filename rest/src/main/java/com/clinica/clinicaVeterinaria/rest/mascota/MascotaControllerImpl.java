package com.clinica.clinicaVeterinaria.rest.mascota;

import com.clinica.clinicaVeterinaria.business.mascota.IMascotaService;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MascotaControllerImpl implements IMascotaController{

    @Autowired
    IMascotaService mascotaService;

    @Override
    public ResponseEntity<List<MascotaDTO>> getMascotas() {
        return new ResponseEntity<>(mascotaService.getAllMascotas(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MascotaDTO> getMascotaById(int idMascota) {
        return new ResponseEntity<>(mascotaService.getMascotaById(idMascota), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MascotaDTO> crearMascota(MascotaDTO mascotaDTO) {
        return new ResponseEntity<>(mascotaService.crearMascota(mascotaDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MascotaDTO> modificarMascota(MascotaDTO mascotaDTO) {
        return new ResponseEntity<>(mascotaService.modificarMascota(mascotaDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MascotaDTO> eliminarMascota(int idMascota) {
        return new ResponseEntity<>(mascotaService.eliminarMascota(idMascota), HttpStatus.OK);
    }

}
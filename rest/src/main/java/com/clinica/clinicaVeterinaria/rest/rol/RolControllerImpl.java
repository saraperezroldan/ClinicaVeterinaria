
package com.clinica.clinicaVeterinaria.rest.rol;


import com.clinica.clinicaVeterinaria.business.rol.IRolService;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RolControllerImpl implements IRolController {
    @Autowired
    IRolService rolService;

    @Override
    public ResponseEntity<List<RolDTO>> getRoles() {
        return new ResponseEntity<>(rolService.getRoles(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RolDTO> getRolById (int idRol) {
        return new ResponseEntity<>(rolService.getRolById(idRol), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RolDTO> crearRol(RolDTO RolDTO) {
        return new ResponseEntity<>(rolService.crearRol(RolDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RolDTO> modificarRol(RolDTO RolDTO) {
        return new ResponseEntity<>(rolService.modificarRol(RolDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RolDTO> eliminarRol(int idRol) {
        return new ResponseEntity<>(rolService.eliminarRol(idRol), HttpStatus.OK);
    }
}

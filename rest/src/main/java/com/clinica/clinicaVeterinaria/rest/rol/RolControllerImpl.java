
package com.clinica.clinicaVeterinaria.rest.rol;

import com.clinica.clinicaVeterinaria.business.rol.IRolService;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RolControllerImpl implements IRolController {
    @Autowired
    IRolService rolService;

    @Override
    public List<RolDTO> getAllRoles() throws URISyntaxException {
        return rolService.getAllRoles();
    }
}

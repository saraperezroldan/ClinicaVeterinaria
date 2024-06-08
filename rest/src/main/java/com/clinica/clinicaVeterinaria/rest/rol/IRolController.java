package com.clinica.clinicaVeterinaria.rest.rol;

import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("rol")
public interface IRolController {
    @GetMapping("getRoles")
    List<RolDTO> getAllRoles() throws URISyntaxException;
}

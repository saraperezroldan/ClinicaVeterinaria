package com.clinica.clinicaVeterinaria.business.rol;

import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<RolDTO> getRoles() {
        List<Rol> roles = null;
        List<RolDTO> rolesDTO = new ArrayList<>();

        roles = rolRepository.findAll();

        if (roles != null && !roles.isEmpty()) {
            roles.forEach(rol -> rolesDTO.add(RolDTO.toDTO(rol)));
        }
        return rolesDTO;
    }

    @Override
    public RolDTO getRolById(int idRol) {
        Rol rolEncontrada = rolRepository.findRolById(idRol);

        if (rolEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rol.noEncontrado");
        }
        return RolDTO.toDTO(rolEncontrada);
    }

    @Override
    public RolDTO crearRol(RolDTO RolDTO) {
        return null;
    }

    @Override
    public RolDTO modificarRol(RolDTO RolDTO) {
        return null;
    }

    @Override
    public RolDTO eliminarRol(int idRol) {
        return null;
    }
}


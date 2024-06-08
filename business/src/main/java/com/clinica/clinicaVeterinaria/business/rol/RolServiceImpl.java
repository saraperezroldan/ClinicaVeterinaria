package com.clinica.clinicaVeterinaria.business.rol;

import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepository;
import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioService;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepositoryImpl;

    @Override
    public List<RolDTO> getAllRoles() {
        List<Rol> roles = null;
        List<RolDTO> rolesDTO = new ArrayList<>();

        roles = rolRepositoryImpl.findAll();

        if (roles != null && !roles.isEmpty()) {
            roles.forEach(rol -> rolesDTO.add(RolDTO.toDTO(rol)));
        }
        return rolesDTO;
    }
}

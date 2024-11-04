package com.clinica.clinicaVeterinaria.business.rol;

import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RolDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<RolDTO> getRoles();
    RolDTO getRolById(int idRol);
    RolDTO crearRol(RolDTO RolDTO);
    RolDTO modificarRol(RolDTO RolDTO);
    RolDTO eliminarRol(int idRol);
}

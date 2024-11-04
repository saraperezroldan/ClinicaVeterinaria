package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import java.util.List;

public interface IRazaService {
    List<RazaDTO> getRazas();
    RazaDTO getRazaById(int idRaza);
    RazaDTO crearRaza(RazaDTO RazaDTO);
    RazaDTO modificarRaza(RazaDTO RazaDTO);
    RazaDTO eliminarRaza(int idRaza);
}
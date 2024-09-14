package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import java.util.List;

public interface IRazaService {
    List<RazaDTO> getRazas();
}
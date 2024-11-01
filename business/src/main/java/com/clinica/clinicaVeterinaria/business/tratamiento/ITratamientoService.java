package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import java.util.List;

public interface ITratamientoService {
    List<TratamientoDTO> getTratamientos();
}

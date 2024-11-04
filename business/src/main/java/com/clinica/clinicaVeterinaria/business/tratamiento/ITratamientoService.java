package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import java.util.List;

public interface ITratamientoService {
    List<TratamientoDTO> getTratamientos();
    TratamientoDTO getTratamientoById (int idTratamiento);
    TratamientoDTO crearTratamiento(TratamientoDTO TratamientoDTO);
    TratamientoDTO modificarTratamiento(TratamientoDTO TratamientoDTO);
    TratamientoDTO eliminarTratamiento(int idTratamiento);
}

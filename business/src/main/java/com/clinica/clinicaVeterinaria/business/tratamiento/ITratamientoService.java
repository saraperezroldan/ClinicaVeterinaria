package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.TratamientoFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;

import java.util.List;

public interface ITratamientoService {
    List<TratamientoDTO> getTratamientos();
    TratamientoDTO getTratamientoById (int idTratamiento);
    PageableResult<TratamientoDTO> getTratamientosConFiltro (TratamientoFiltroDTO filtro);
    TratamientoDTO crearTratamiento(TratamientoDTO TratamientoDTO);
    TratamientoDTO modificarTratamiento(TratamientoDTO TratamientoDTO);
    TratamientoDTO eliminarTratamiento(int idTratamiento);
}

package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TratamientoServiceImpl implements ITratamientoService {
    @Autowired
    private ITratamientoRepository tratamientoRepository;
    @Override
    public List<TratamientoDTO> getTratamientos() {
        List<Tratamiento> tratamientos = null;
        List<TratamientoDTO> tratamientosDTO = new ArrayList<>();

        tratamientos = tratamientoRepository.findAll();

        if (tratamientos != null && !tratamientos.isEmpty()) {
            tratamientos.forEach(tratamiento -> tratamientosDTO.add(TratamientoDTO.toDTO(tratamiento)));
        }
        return tratamientosDTO;
    }

    @Override
    public TratamientoDTO getTratamientoById(int idTratamiento) {
        Tratamiento tratamientoEncontrado = null;
        tratamientoEncontrado = tratamientoRepository.findTratamientoById(idTratamiento);

            if (tratamientoEncontrado == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
            }
            return TratamientoDTO.toDTO(tratamientoEncontrado);
    }

    @Override
    public TratamientoDTO crearTratamiento(TratamientoDTO TratamientoDTO) {
        return null;
    }

    @Override
    public TratamientoDTO modificarTratamiento(TratamientoDTO TratamientoDTO) {
        return null;
    }

    @Override
    public TratamientoDTO eliminarTratamiento(int idTratamiento) {
        return null;
    }
}

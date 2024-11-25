package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.TratamientoFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    public PageableResult<TratamientoDTO> getTratamientosConFiltro(TratamientoFiltroDTO filtro) {
        List<Tratamiento> tratamientos = tratamientoRepository.findTratamientosPorFiltro(filtro);
        int resultMax = tratamientoRepository.getResultMax(filtro);
        List<TratamientoDTO> tratamientosDTOs = TratamientoDTO.toDTO(tratamientos);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,tratamientosDTOs);
    }

    @Override
    public TratamientoDTO crearTratamiento(TratamientoDTO TratamientoDTO) {
        Tratamiento tratamientoNuevo = TratamientoDTO.toDomain(TratamientoDTO);
        validarTratamiento(tratamientoNuevo);
        tratamientoRepository.save(tratamientoNuevo);

        return TratamientoDTO.toDTO(tratamientoNuevo);
    }

    @Override
    public TratamientoDTO modificarTratamiento(TratamientoDTO TratamientoDTO) {
        Tratamiento tratamientoUpdate = TratamientoDTO.toDomain(TratamientoDTO);
        existeTratamiento(tratamientoUpdate);
        validarTratamiento(tratamientoUpdate);
        tratamientoRepository.save(tratamientoUpdate);

        return TratamientoDTO.toDTO(tratamientoUpdate);
    }

    @Override
    public TratamientoDTO eliminarTratamiento(int idTratamiento) {
        Tratamiento tratamientoBorrar = tratamientoRepository.findTratamientoById(idTratamiento);
        existeTratamiento(tratamientoBorrar);
        tratamientoRepository.delete(tratamientoBorrar);

        return TratamientoDTO.toDTO(tratamientoBorrar);
    }
    private void existeTratamiento(Tratamiento tratamiento) {
        if (tratamiento == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tratamiento.noEncontrado");
        }
    }
    private void validarTratamiento(Tratamiento tratamiento){
        if (!StringUtils.hasText(tratamiento.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tratamiento.requeridoNombre");
        }
        if (tratamiento.getPrecio() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tratamiento.requeridoPrecio");
        }
    }
}

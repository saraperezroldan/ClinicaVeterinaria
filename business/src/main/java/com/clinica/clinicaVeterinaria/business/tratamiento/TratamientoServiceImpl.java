package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepository;
import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioService;
import com.clinica.clinicaVeterinaria.domain.dtos.TratamientoDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

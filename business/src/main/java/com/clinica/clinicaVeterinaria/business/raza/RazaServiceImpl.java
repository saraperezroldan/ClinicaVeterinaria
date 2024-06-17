package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaServiceImpl implements IRazaService{

    @Autowired
    private IRazaRepository razaRepository;
    @Override
    public List<RazaDTO> getRazas() {
        List<Raza> razas= razaRepository.findAll();
        return RazaDTO.toDTO(razas);
    }
}

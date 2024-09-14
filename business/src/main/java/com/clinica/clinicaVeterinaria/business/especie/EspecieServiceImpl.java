package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspecieServiceImpl implements IEspecieService{

    @Autowired
    private IEspecieRepository especieRepository;

    @Override
    public List<EspecieDTO> getEspecies() {
        List<Especie> especies = especieRepository.findAll();
        return EspecieDTO.toDTO(especies);
    }
}
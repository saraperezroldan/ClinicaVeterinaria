package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public EspecieDTO getEspecieById(int idEspecie) {
        Especie especieEncontrada = null;
        especieEncontrada = especieRepository.findEspecieById(idEspecie);

        if (especieEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especie.noEncontrado");
        }
        return EspecieDTO.toDTO(especieEncontrada);
    }
}
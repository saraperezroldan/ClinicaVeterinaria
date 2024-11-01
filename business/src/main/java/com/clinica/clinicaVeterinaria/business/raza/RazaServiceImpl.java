package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public RazaDTO getRazaById(int idRaza) {
        Raza razaEncontrada = null;
        razaEncontrada = razaRepository.findRazaById(idRaza);

        if (razaEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "raza.noEncontrado");
        }
        return RazaDTO.toDTO(razaEncontrada);
    }
}
package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;

import java.util.List;

public interface IEspecieService {

    public List<EspecieDTO> getEspecies();
}
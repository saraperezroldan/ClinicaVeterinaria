package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import java.util.List;

public interface IEspecieService {
    List<EspecieDTO> getEspecies();
    EspecieDTO getEspecieById(int idEspecie);
    EspecieDTO crearEspecie(EspecieDTO EspecieDTO);
    EspecieDTO modificarEspecie(EspecieDTO EspecieDTO);
    EspecieDTO eliminarEspecie(int idEspecie);
}
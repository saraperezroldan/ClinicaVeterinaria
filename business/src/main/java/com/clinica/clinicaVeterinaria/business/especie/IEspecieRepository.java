package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEspecieRepository extends JpaRepository<Especie, Integer> {

    @Override
    List<Especie> findAll();
}

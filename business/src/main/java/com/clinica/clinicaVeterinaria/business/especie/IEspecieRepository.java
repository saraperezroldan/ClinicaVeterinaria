package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEspecieRepository extends JpaRepository<Especie, Integer> {

    @Override
    List<Especie> findAll();
    @Query("SELECT e FROM Especie e WHERE e.idEspecie = :idEspecie")
    Especie findEspecieById (int idEspecie);
}

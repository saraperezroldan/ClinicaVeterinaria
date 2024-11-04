package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IRazaRepository extends JpaRepository<Raza, Integer> {
    @Override
    List<Raza> findAll();
    @Query("SELECT r FROM Raza r WHERE r.idRaza = :idRaza")
    Raza findRazaById(int idRaza);
}
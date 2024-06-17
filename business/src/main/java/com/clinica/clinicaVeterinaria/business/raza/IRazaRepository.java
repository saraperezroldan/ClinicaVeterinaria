
package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRazaRepository extends JpaRepository<Raza, Integer> {
    @Override
    List<Raza> findAll();
}

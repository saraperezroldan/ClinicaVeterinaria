package com.clinica.clinicaVeterinaria.business.rol;

import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}
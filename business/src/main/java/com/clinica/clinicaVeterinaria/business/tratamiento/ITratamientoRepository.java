package com.clinica.clinicaVeterinaria.business.tratamiento;

import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITratamientoRepository extends JpaRepository<Tratamiento, Integer>, ITratamientoRepositoryCustom {


}

package com.clinica.clinicaVeterinaria.business.rol;

import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import com.clinica.clinicaVeterinaria.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
    @Override
    List<Rol> findAll();
    @Query("SELECT r FROM Rol r WHERE r.idRol = :idRol")
    Rol findRolById(int idRol);
}
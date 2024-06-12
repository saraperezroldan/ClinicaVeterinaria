package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMascotaRepository extends JpaRepository<Mascota, Integer>, IMascotaRepositoryCustom {

    @Query("SELECT distinct m FROM Mascota m "
            + "LEFT JOIN FETCH m.especie "
            + "WHERE m.idMascota = :idMascota")
    public Mascota findMascotaById(int idMascota);
    @Override
    List<Mascota> findAll();



}

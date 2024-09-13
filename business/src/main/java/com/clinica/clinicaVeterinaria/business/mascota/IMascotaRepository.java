package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IMascotaRepository extends JpaRepository<Mascota, Integer>, IMascotaRepositoryCustom {

    @Query("SELECT distinct m FROM Mascota m WHERE m.idMascota = :idMascota")
    Mascota findMascotaById(int idMascota);

    @Query("SELECT m FROM Mascota m WHERE m.activo = 1")
    List<Mascota> findMascotasActivas();

    @Query("SELECT m FROM Mascota m WHERE m.usuario.idUsuario = :idUsuario")
    List<Mascota> findMascotasByIdUsuario(int idUsuario);
}
package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IConsultaRepository extends JpaRepository<Consulta, Integer>, IConsultaRepositoryCustom{

    @Query("SELECT c FROM Consulta c WHERE c.idConsulta = :idCita AND c.esCita = 1")
    Consulta findCitaById(int idCita);
    @Query("SELECT c FROM Consulta c WHERE c.idConsulta = :idConsulta AND c.esCita = 0")
    Consulta findConsultaById(int idConsulta);
    @Query("SELECT c FROM Consulta c WHERE c.mascota.idMascota = :idMascota AND c.esCita = 1")
    List<Consulta> findCitasByIdMascota(int idMascota);
    @Query("SELECT c FROM Consulta c WHERE c.mascota.idMascota = :idMascota AND c.esCita = 0")
    List<Consulta> findConsultasByIdMascota(int idMascota);
    @Query("SELECT c FROM Consulta c " +
            "JOIN c.tratamientosConsulta tc " +
            "JOIN tc.tratamiento t " +
            "WHERE c.mascota.idMascota = :idMascota AND c.esCita = 1 AND t.esVacuna = 1")
    List<Consulta> findCitasConVacunasByIdMascota (int idMascota);

   @Query("SELECT c FROM Consulta c " +
            "WHERE c.idVeterinario = :idVeterinario AND c.esCita = 1 " +
            "ORDER BY c.fechaCitaConsulta ASC ")
    List<Consulta> findCitasByIdVeterinario (int idVeterinario);
}
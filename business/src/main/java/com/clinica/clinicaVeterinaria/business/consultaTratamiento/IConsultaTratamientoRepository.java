package com.clinica.clinicaVeterinaria.business.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamiento;
import com.clinica.clinicaVeterinaria.domain.entities.ConsultaTratamientoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IConsultaTratamientoRepository extends JpaRepository<ConsultaTratamiento, ConsultaTratamientoID> {

    @Query("SELECT ct FROM ConsultaTratamiento ct WHERE ct.id.idConsulta = :idConsulta")
    List<ConsultaTratamiento> findTratamientosByIdConsulta (int idConsulta);

    @Query("SELECT ct FROM ConsultaTratamiento ct WHERE ct.id.idTratamiento= :idTratamiento")
    List<ConsultaTratamiento> findConsultasByIdTratamiento (int idTratamiento);
    @Query("SELECT ct FROM ConsultaTratamiento ct " +
            "WHERE ct.id.idTratamiento= :idTratamiento AND ct.id.idConsulta = :idConsulta ")
    ConsultaTratamiento existeConsultaTratamiento (int idConsulta, int idTratamiento);
}

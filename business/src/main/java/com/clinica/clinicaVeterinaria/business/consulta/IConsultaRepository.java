package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IConsultaRepository extends JpaRepository<Consulta, Integer>, IConsultaRepositoryCustom{

    @Query("SELECT c FROM Consulta c WHERE c.mascota.idMascota = :idMascota")
    List<Consulta> findConsultasByIdMascota(int idMascota);
    @Query("SELECT c FROM Consulta c WHERE c.idConsulta = :idConsulta")
    public Consulta findConsultaById(int idConsulta);

}
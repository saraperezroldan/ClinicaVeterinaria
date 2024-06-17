package com.clinica.clinicaVeterinaria.business.consulta;

import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaRepository extends JpaRepository<Consulta, String>, IConsultaRepositoryCustom{

    @Query("SELECT distinct c FROM Consulta c "
            + "LEFT JOIN FETCH c.mascota "
            + "WHERE c.idConsulta = :idConsulta")
    public Consulta findConsultaById(int idConsulta);
}

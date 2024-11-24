package com.clinica.clinicaVeterinaria.rest.consultaTratamiento;

import com.clinica.clinicaVeterinaria.domain.dtos.ConsultaTratamientoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("consultaTratamiento")
public interface IConsultaTratamientoController{
    @GetMapping("getTratamientosByIdConsulta/{idConsulta}")
    ResponseEntity<List<ConsultaTratamientoDTO>> getTratamientosByIdConsulta(@PathVariable("idConsulta") int idConsulta);
    @GetMapping("getConsultasByIdTratamiento/{idTratamiento}")
    ResponseEntity<List<ConsultaTratamientoDTO>> getConsultasByIdTratamiento(@PathVariable("idTratamiento") int idTratamiento);
}

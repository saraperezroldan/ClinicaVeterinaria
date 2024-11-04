package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RazaServiceImpl implements IRazaService{
    @Autowired
    private IRazaRepository razaRepository;
    @Override
    public List<RazaDTO> getRazas() {
        List<Raza> razas= razaRepository.findAll();
        return RazaDTO.toDTO(razas);
    }

    @Override
    public RazaDTO getRazaById(int idRaza) {
        Raza razaEncontrada = null;
        razaEncontrada = razaRepository.findRazaById(idRaza);

        if (razaEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "raza.noEncontrado");
        }
        return RazaDTO.toDTO(razaEncontrada);
    }

    @Override
    public RazaDTO crearRaza(RazaDTO razaDTO) {
        Raza razaNuevo = RazaDTO.toDomain(razaDTO);
        validarRaza(razaNuevo);

        razaNuevo.setNombre(razaDTO.getNombre());
        razaNuevo.setEspecie(razaNuevo.getEspecie());
        razaRepository.save(razaNuevo);

        return RazaDTO.toDTO(razaNuevo);
    }

    @Override
    public RazaDTO modificarRaza(RazaDTO razaDTO) {
        Raza razaUpdate = RazaDTO.toDomain(razaDTO);
        if (existeRaza(razaUpdate)) {
            razaUpdate.setNombre(razaDTO.getNombre());
            razaUpdate.setEspecie(razaUpdate.getEspecie());
            razaRepository.save(razaUpdate);
        }

        return RazaDTO.toDTO(razaUpdate);
    }

    @Override
    public RazaDTO eliminarRaza(int idRaza) {
        Raza razaBorrar = razaRepository.findRazaById(idRaza);

        if (existeRaza(razaBorrar)) {
            razaRepository.delete(razaBorrar);
        }
        return RazaDTO.toDTO(razaBorrar);
    }
    private void validarRaza(Raza raza){
        if (existeRaza(raza)){

        }
    }
    private boolean existeRaza(Raza raza) {
        if (raza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontrado");
        }
        return true;
    }
}
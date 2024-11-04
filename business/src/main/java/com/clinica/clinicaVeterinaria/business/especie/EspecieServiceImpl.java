package com.clinica.clinicaVeterinaria.business.especie;

import com.clinica.clinicaVeterinaria.domain.dtos.EspecieDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class EspecieServiceImpl implements IEspecieService{
    @Autowired
    private IEspecieRepository especieRepository;

    @Override
    public List<EspecieDTO> getEspecies() {
        List<Especie> especies = especieRepository.findAll();
        return EspecieDTO.toDTO(especies);
    }

    @Override
    public EspecieDTO getEspecieById(int idEspecie) {
        Especie especieEncontrada = null;
        especieEncontrada = especieRepository.findEspecieById(idEspecie);

        if (especieEncontrada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especie.noEncontrado");
        }
        return EspecieDTO.toDTO(especieEncontrada);
    }

    @Override
    public EspecieDTO crearEspecie(EspecieDTO especieDTO) {
        Especie especieNuevo = EspecieDTO.toDomain(especieDTO);
        validarEspecie(especieNuevo);

        especieNuevo.setNombre(especieDTO.getNombre());
        especieRepository.save(especieNuevo);

        return EspecieDTO.toDTO(especieNuevo);
    }

    @Override
    public EspecieDTO modificarEspecie(EspecieDTO especieDTO) {
        Especie especieUpdate = especieRepository.findEspecieById(especieDTO.getIdEspecie());
        validarEspecie(especieUpdate);

        especieUpdate.setNombre(especieDTO.getNombre());
        especieRepository.save(especieUpdate);

        return EspecieDTO.toDTO(especieUpdate);
    }

    @Override
    public EspecieDTO eliminarEspecie(int idEspecie) {
        Especie especieBorrar = especieRepository.findEspecieById(idEspecie);
        existeEspecie(especieBorrar);

        especieRepository.delete(especieBorrar);

        return EspecieDTO.toDTO(especieBorrar);
    }


    private void validarEspecie(Especie especie){
        if (existeEspecie(especie)){

        }
    }
    private boolean existeEspecie(Especie especie) {
        if (especie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontrado");
        }
        return true;
    }
}
package com.clinica.clinicaVeterinaria.business.raza;

import com.clinica.clinicaVeterinaria.business.especie.IEspecieRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import com.clinica.clinicaVeterinaria.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RazaServiceImpl implements IRazaService{
    @Autowired
    private IRazaRepository razaRepository;
    @Autowired
    private IEspecieRepository especieRepository;
    @Override
    public List<RazaDTO> getRazas() {
        List<Raza> razas= razaRepository.findAll();
        existeRazas(razas);

        return RazaDTO.toDTO(razas);
    }

    @Override
    public RazaDTO getRazaById(int idRaza) {
        Raza razaEncontrada = razaRepository.findRazaById(idRaza);
        existeRaza(razaEncontrada);

        return RazaDTO.toDTO(razaEncontrada);
    }

    @Override
    public RazaDTO crearRaza(RazaDTO razaDTO) {
        Raza razaNuevo = RazaDTO.toDomain(razaDTO);
        existeRaza(razaNuevo);
        validarRaza(razaNuevo);
        razaRepository.save(razaNuevo);

        return RazaDTO.toDTO(razaNuevo);
    }

    @Override
    public RazaDTO modificarRaza(RazaDTO razaDTO) {
        Raza razaUpdate = RazaDTO.toDomain(razaDTO);
        existeRaza(razaUpdate);
        validarRaza(razaUpdate);
        razaRepository.save(razaUpdate);

        return RazaDTO.toDTO(razaUpdate);
    }

    @Override
    public RazaDTO eliminarRaza(int idRaza) {
        Raza razaBorrar = razaRepository.findRazaById(idRaza);
        existeRaza(razaBorrar);
        razaRepository.delete(razaBorrar);

        return RazaDTO.toDTO(razaBorrar);
    }
    private void existeRaza(Raza raza) {
        if (raza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontrada");
        }
    }
    private void existeRazas(List<Raza> lista) {
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontradaLista");
        }
    }
    private void validarRaza(Raza raza){
        if (!StringUtils.hasText(raza.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "raza.requeridoNombre");
        }
        if (raza.getNombre().length() > Constantes.RAZA_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "raza.caracteresMaxNombre");
        }
        if (raza.getEspecie() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "raza.requeridoEspecie");
        }
        Especie especie = especieRepository.findEspecieById(raza.getEspecie().getIdEspecie());
        if (especie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontradaEspecie");
        }
    }
}
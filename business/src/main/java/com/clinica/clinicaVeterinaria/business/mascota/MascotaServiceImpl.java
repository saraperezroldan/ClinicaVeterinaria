package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.business.raza.IRazaRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MascotaServiceImpl implements IMascotaService{

    @Autowired
    private IMascotaRepository mascotaRepository;

    @Autowired
    private IRazaRepository razaRepository;

    @Override
    public List<MascotaDTO> getMascotas() {
        List<Mascota> mascotas = null;
        List<MascotaDTO> mascotasDTO = new ArrayList<>();

        mascotas = mascotaRepository.findAll();

        if (mascotas != null && !mascotas.isEmpty()) {
            mascotas.forEach(mascota -> mascotasDTO.add(MascotaDTO.toDTO(mascota)));
        }
        return mascotasDTO;
    }

    @Override
    public MascotaDTO getMascotaById(int idMascota) {
        Mascota mascotaEncontrada = null;

        if (idMascota > 0) {
            mascotaEncontrada = mascotaRepository.findMascotaById(idMascota);
            if (mascotaEncontrada != null) {
                return MascotaDTO.toDTO(mascotaEncontrada);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.noEncontrado");
            }
        } else  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código de la mascota debe ser mayor que 0.");
        }
    }

    @Override
    public List<MascotaDTO> getMascotasByIdUsuario(int idUsuario) {
        List<Mascota> mascotas = mascotaRepository.findMascotasByIdUsuario(idUsuario);
        List<MascotaDTO> mascotasDTO = new ArrayList<>();


        if (mascotas != null && !mascotas.isEmpty()) {
            mascotas.forEach(mascota -> mascotasDTO.add(MascotaDTO.toDTO(mascota)));
        }
        return mascotasDTO;
    }

    @Override
    public PageableResult<MascotaDTO> getMascotaConFiltro(MascotaFiltroDTO filtro) {
        List<Mascota> mascotas = mascotaRepository.findMascotaPorFiltro(filtro);
        int resultMax = mascotaRepository.getResultMax(filtro);
        List<MascotaDTO> mascotasDTOs = MascotaDTO.toDTO(mascotas);

        return new PageableResult<>(filtro.getPageNumber(),resultMax ,mascotasDTOs);
    }

    @Override
    public MascotaDTO crearMascota(MascotaDTO mascotaDTO) {
        Mascota mascotaNuevo = MascotaDTO.toDomain(mascotaDTO);
        //validarMascota(mascotaNuevo);

        Mascota mascotaOld = mascotaRepository.findMascotaById(mascotaNuevo.getIdMascota());
        if (mascotaOld != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.yaExisteUsuario");
        }
        mascotaNuevo.setActivo(1);
        mascotaNuevo.setFechaAlta(new Date());
        mascotaRepository.save(mascotaNuevo);

        return MascotaDTO.toDTO(mascotaNuevo);
    }

    @Override
    public MascotaDTO modificarMascota(MascotaDTO mascotaDTO) {
        Mascota mascotaSaved = MascotaDTO.toDomain(mascotaDTO);
        mascotaSaved = mascotaRepository.save(mascotaSaved);

        return MascotaDTO.toDTO(mascotaSaved);
    }

    @Override
    public MascotaDTO eliminarMascota(int idMascota) {
        Mascota mascotaEncontrada = null;

        mascotaEncontrada = mascotaRepository.findMascotaById(idMascota);

        mascotaRepository.delete(mascotaEncontrada);

        return MascotaDTO.toDTO(mascotaEncontrada);
    }
    private void existeMascota (Mascota mascota) {
        if (mascota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontrado");
        }
    }

    private void validarMascota(Mascota mascota) {
        existeMascota(mascota);

        //razaRepository.findById(mascota.getRaza().getIdRaza());
    }

}

package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MascotaServiceImpl implements IMascotaService{

    @Autowired
    private IMascotaRepository mascotaRepository;

    @Override
    public List<MascotaDTO> getAllMascotas() {
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

        mascotaEncontrada = mascotaRepository.findMascotaById(idMascota);

        return MascotaDTO.toDTO(mascotaEncontrada);
    }

    @Override
    public MascotaDTO crearMascota(MascotaDTO mascotaDTO) {
        Mascota mascotaSaved = MascotaDTO.toDomain(mascotaDTO);
        mascotaSaved = mascotaRepository.save(mascotaSaved);

        return MascotaDTO.toDTO(mascotaSaved);
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

}

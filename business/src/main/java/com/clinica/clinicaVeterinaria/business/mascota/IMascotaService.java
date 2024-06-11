package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;

import java.util.List;

public interface IMascotaService {

    public List<MascotaDTO> getAllMascotas();
    public MascotaDTO getMascotaById(int idMascota);

    //falta getArticulosFiltrados

    public MascotaDTO crearMascota(MascotaDTO mascotaDTO);
    public MascotaDTO modificarMascota(MascotaDTO mascotaDTO);
    public MascotaDTO eliminarMascota(int idMascota);
}

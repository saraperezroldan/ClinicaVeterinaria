package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMascotaService {

    List<MascotaDTO> getMascotas();
    MascotaDTO getMascotaById(int idMascota);
    List<MascotaDTO> getMascotasByIdUsuario(int idUsuario);
    PageableResult<MascotaDTO> getMascotaConFiltro(MascotaFiltroDTO filtro);
    MascotaDTO crearMascota(MascotaDTO mascotaDTO);
    MascotaDTO modificarMascota(MascotaDTO mascotaDTO);
    MascotaDTO eliminarMascota(int idMascota);
    MascotaDTO cambiarFotoMascota(int idMascota, MultipartFile image);
}

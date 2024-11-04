package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.business.especie.IEspecieRepository;
import com.clinica.clinicaVeterinaria.business.raza.IRazaRepository;
import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.utils.Constantes;
import com.clinica.clinicaVeterinaria.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MascotaServiceImpl implements IMascotaService{

    @Autowired
    private IMascotaRepository mascotaRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRazaRepository razaRepository;
    @Autowired
    private IEspecieRepository especieRepository;

    @Override
    public List<MascotaDTO> getMascotas() {
        List<Mascota> mascotas = null;
        List<MascotaDTO> mascotasDTO = new ArrayList<>();

        mascotas = mascotaRepository.findMascotasActivas();

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

        Mascota mascotaEncontrada = mascotaRepository.findMascotaById(mascotaDTO.getIdMascota());
        if (mascotaEncontrada != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.yaExisteMascota");
        }
        Usuario usuario = usuarioRepository.findUsuarioById(mascotaNuevo.getUsuario().getIdUsuario());
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontradoUsuario");
        }
        validarMascota(mascotaNuevo);
        usuario.setFechaModificacion(new Date());
        usuarioRepository.save(usuario);

        mascotaNuevo.setFechaAlta(new Date());
        mascotaRepository.save(mascotaNuevo);

        return MascotaDTO.toDTO(mascotaNuevo);
    }

    @Override
    public MascotaDTO modificarMascota(MascotaDTO mascotaDTO) {
        Mascota mascotaUpdate = MascotaDTO.toDomain(mascotaDTO);
        Mascota mascotaEncontrada = mascotaRepository.findMascotaById(mascotaDTO.getIdMascota());

        existeMascota(mascotaEncontrada);
        validarMascota(mascotaUpdate);

        String edad = Utils.calcularEdadEnAniosYMeses(Utils.convertirDateALocalDate(mascotaDTO.getFechaNacimiento()));
        System.out.println(edad);
        mascotaUpdate.setFechaModificacion(new Date());
        mascotaRepository.save(mascotaUpdate);

        return MascotaDTO.toDTO(mascotaUpdate);
    }

    @Override
    public MascotaDTO eliminarMascota(int idMascota) {
        Mascota mascotaBorrar = mascotaRepository.findMascotaById(idMascota);

        existeMascota(mascotaBorrar);
        if (mascotaBorrar.getActivo() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.yaEliminada");
        }
        mascotaBorrar.setActivo(0);
        mascotaBorrar.setFechaBaja(new Date());
        mascotaRepository.save(mascotaBorrar);

        return MascotaDTO.toDTO(mascotaBorrar);
    }
    private void existeMascota (Mascota mascota) {
        if (mascota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontrada");
        }
    }
    private void validarMascota(Mascota mascota) {
        if (!StringUtils.hasText(mascota.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.requeridoNombre");
        }
        if (mascota.getNombre().length() > Constantes.MASCOTA_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.caracteresMaxNombre");
        }
        if (!StringUtils.hasText(mascota.getGenero())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.requeridoGenero");
        }
        if (mascota.getNombre().length() > Constantes.MASCOTA_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.caracteresMaxNombre");
        }
        Usuario usuario = usuarioRepository.findUsuarioById(mascota.getUsuario().getIdUsuario());
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontradoUsuario");
        }
        Raza raza = razaRepository.findRazaById(mascota.getRaza().getIdRaza());
        if (raza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontradaRaza");
        }
        Especie especie = especieRepository.findEspecieById(mascota.getRaza().getEspecie().getIdEspecie());
        if (especie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mascota.noEncontradaEspecie");
        }
    }
}
package com.clinica.clinicaVeterinaria.business.mascota;

import com.clinica.clinicaVeterinaria.business.especie.IEspecieRepository;
import com.clinica.clinicaVeterinaria.business.raza.IRazaRepository;
import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepository;
import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.RazaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.entities.Especie;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.entities.Raza;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import com.clinica.clinicaVeterinaria.domain.utils.Utils;
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

        Mascota mascotaOld = mascotaRepository.findMascotaById(mascotaNuevo.getIdMascota());
        if (mascotaOld != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.yaExisteMascota");
        }
        Usuario usuario = usuarioRepository.findUsuarioById(mascotaNuevo.getUsuario().getIdUsuario());
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario.noEncontrada");
        }
        Raza raza = razaRepository.findRazaById(mascotaNuevo.getRaza().getIdRaza());
        if (raza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontrada");
        }
        Especie especie = especieRepository.findEspecieById(mascotaNuevo.getRaza().getEspecie().getIdEspecie());
        if (especie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "especie.noEncontrada");
        }
        usuario.setFechaModificacion(new Date());
        usuarioRepository.save(usuario);
        //mascotaNuevo.getRaza().getEspecie().setIdEspecie(especie.getIdEspecie());
        mascotaNuevo.setRaza(raza);
        mascotaNuevo.setFechaAlta(new Date());
        mascotaRepository.save(mascotaNuevo);

        return MascotaDTO.toDTO(mascotaNuevo);
    }

    @Override
    public MascotaDTO modificarMascota(MascotaDTO mascotaDTO) {
        Mascota mascotaUpdate = mascotaRepository.findMascotaById(mascotaDTO.getIdMascota());

        existeMascota(mascotaUpdate);
        validarMascota(MascotaDTO.toDomain(mascotaDTO));

        Raza raza = razaRepository.findRazaById(mascotaDTO.getRaza().getIdRaza());
        if (raza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "raza.noEncontrada");
        }
        Especie especie = especieRepository.findEspecieById(mascotaDTO.getRaza().getEspecie().getIdEspecie());
        if (especie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "especie.noEncontrada");
        }

        mascotaUpdate.setNombre(mascotaDTO.getNombre());
        mascotaUpdate.setGenero(mascotaDTO.getGenero());
        mascotaUpdate.setComplexion(mascotaDTO.getComplexion());
        mascotaUpdate.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        mascotaUpdate.setPeso(mascotaDTO.getPeso());
        mascotaUpdate.setRaza(RazaDTO.toDomain(mascotaDTO.getRaza()));
        mascotaUpdate.setFechaModificacion(new Date());

        String edad = Utils.calcularEdadEnAniosYMeses(Utils.convertirDateALocalDate(mascotaDTO.getFechaNacimiento()));
        System.out.println(edad);
        mascotaRepository.save(mascotaUpdate);

        return MascotaDTO.toDTO(mascotaUpdate);
    }

    @Override
    public MascotaDTO eliminarMascota(int idMascota) {
        Mascota mascotaBorrar = mascotaRepository.findMascotaById(idMascota);

        if (mascotaBorrar == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mascota.noEncontrado");
        }
        mascotaBorrar.setActivo(0);
        mascotaBorrar.setFechaBaja(new Date());
        mascotaRepository.save(mascotaBorrar);

        return MascotaDTO.toDTO(mascotaBorrar);
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
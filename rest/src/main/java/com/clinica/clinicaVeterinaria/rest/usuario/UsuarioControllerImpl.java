
package com.clinica.clinicaVeterinaria.rest.usuario;

import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioService;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UsuarioControllerImpl implements IUsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> getUsuarioById(int idUsuario) {
        return new ResponseEntity<>(usuarioService.getUsuarioById(idUsuario), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> getUsuariosByIdRol(int idRol) {
        return new ResponseEntity<>(usuarioService.getUsuariosByIdRol(idRol), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(String email) {
        return new ResponseEntity<>(usuarioService.getUsuarioByEmail(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> getUsuarioByDni(String dni) {
        return new ResponseEntity<>(usuarioService.getUsuarioByDni(dni), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageableResult<UsuarioDTO>> getUsuarioConFiltro(UsuarioFiltroDTO filtro) {
        return new ResponseEntity<>(usuarioService.getUsuarioConFiltro(filtro), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> modificarUsuario(UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.modificarUsuario(usuarioDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> eliminarUsuario(int idUsuario) {
        return new ResponseEntity<>(usuarioService.eliminarUsuario(idUsuario), HttpStatus.OK);
    }
}

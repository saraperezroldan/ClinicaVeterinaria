package com.clinica.clinicaVeterinaria.rest.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.MascotaDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("usuario")
public interface IUsuarioController {

    @GetMapping("getUsuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios();

    @GetMapping("getUsuarioById/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") int idUsuario);

    @GetMapping("getUsuariosByIdRol/{idRol}")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosByIdRol(@PathVariable("idRol") int idRol);

    @PostMapping("getUsuarioConFiltro")
    public ResponseEntity<PageableResult<UsuarioDTO>> getUsuarioConFiltro (@RequestBody UsuarioFiltroDTO filtro);

    @PostMapping("crearUsuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("modificarUsuario")
    public ResponseEntity<UsuarioDTO> modificarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable("id") int idUsuario);
}

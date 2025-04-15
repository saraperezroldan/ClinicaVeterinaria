package com.clinica.clinicaVeterinaria.rest.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import com.clinica.clinicaVeterinaria.domain.dtos.pageable.PageableResult;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("usuario")
public interface IUsuarioController {

    @GetMapping("getUsuarios")
    ResponseEntity<List<UsuarioDTO>> getUsuarios();
    @GetMapping("getUsuarioById/{id}")
    ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") int idUsuario);
    @GetMapping("getUsuariosByIdRol/{idRol}")
    ResponseEntity<List<UsuarioDTO>> getUsuariosByIdRol(@PathVariable("idRol") int idRol);
    @GetMapping("getUsuarioByEmail/{email}")
    ResponseEntity<UsuarioDTO> getUsuarioByEmail(@PathVariable("email") String email);
    @GetMapping("getUsuarioByDni/{dni}")
    ResponseEntity<UsuarioDTO> getUsuarioByDni(@PathVariable("dni") String dni);
    @PostMapping("getUsuarioConFiltro")
    ResponseEntity<PageableResult<UsuarioDTO>> getUsuarioConFiltro (@RequestBody UsuarioFiltroDTO filtro);
    @PostMapping("crearUsuario")
    ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO);
    @PostMapping("modificarUsuario")
    ResponseEntity<UsuarioDTO> modificarUsuario(@RequestBody UsuarioDTO usuarioDTO);
    @DeleteMapping("eliminarUsuario/{id}")
    ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable("id") int idUsuario);
    @PostMapping("/cambiarFoto/{id}")
    ResponseEntity<UsuarioDTO> cambiarFoto(
            @PathVariable("id") int idUsuario, @RequestParam("image") MultipartFile image);
    @PostMapping("/cambiarPassword/{id}")
    ResponseEntity<UsuarioDTO> cambiarPassword(@PathVariable("id") int idUsuario, @RequestBody UsuarioDTO usuarioDTO);
}


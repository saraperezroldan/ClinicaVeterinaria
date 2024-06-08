package com.clinica.clinicaVeterinaria.rest.usuario;

import com.clinica.clinicaVeterinaria.domain.dtos.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("usuario")
public interface IUsuarioController {

    @GetMapping("getUsuarios")
    List<UsuarioDTO> getAllUsuarios() throws URISyntaxException;

    @GetMapping("getUsuarioPorId/{id}")
    UsuarioDTO getUsuarioPorId(@PathVariable("id") int idUsuario) throws URISyntaxException;

    @PostMapping("anadirUsuario")
    UsuarioDTO anadirUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("modificarUsuario")
    UsuarioDTO modificarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("eliminarUsuario/{id}")
    UsuarioDTO eliminarUsuario(@PathVariable("id") int idUsuario);

}


package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>, IUsuarioRepositoryCustom {
    @Query("SELECT distinct u FROM Usuario u "
            + " WHERE u.idUsuario = :idUsuario")
    Usuario findUsuarioById (int idUsuario);

    @Query("SELECT u FROM Usuario u " + " WHERE u.rol.idRol = :idRol")
    List<Usuario> findUsuariosByIdRol(int idRol);

    @Override
    List<Usuario> findAll();
}


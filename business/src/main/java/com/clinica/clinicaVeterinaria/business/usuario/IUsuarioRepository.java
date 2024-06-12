package com.clinica.clinicaVeterinaria.business.usuario;

import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>, IUsuarioRepositoryCustom {
    @Query("SELECT distinct u FROM Usuario u "
            + "LEFT JOIN FETCH u.rol "
            + "WHERE u.idUsuario = :idUsuario")
    Usuario findUsuarioById (int idUsuario);

    @Override
    List<Usuario> findAll();
}

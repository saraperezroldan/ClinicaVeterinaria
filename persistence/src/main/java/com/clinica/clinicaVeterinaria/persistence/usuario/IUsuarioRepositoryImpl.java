package com.clinica.clinicaVeterinaria.persistence.usuario;

import com.clinica.clinicaVeterinaria.business.usuario.IUsuarioRepositoryCustom;
import com.clinica.clinicaVeterinaria.domain.entities.Usuario;
import com.clinica.clinicaVeterinaria.domain.filtros.UsuarioFiltroDTO;
import com.clinica.clinicaVeterinaria.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IUsuarioRepositoryImpl extends IBaseRepositoryImpl implements IUsuarioRepositoryCustom {
    @Override
    public List<Usuario> findUsuarioPorFiltro(UsuarioFiltroDTO filtro) {
        String query = "SELECT u FROM Usuario u WHERE u.activo = 1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Usuario> typedQuery = em.createQuery(query + queryConditions + orderQuery, Usuario.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        if (filtro.getPageable() && filtro.getPageNumber() >= 0 && filtro.getPageElements() > 0) {
            typedQuery.setFirstResult(filtro.getPageNumber() * filtro.getPageElements());
            typedQuery.setMaxResults(filtro.getPageElements());
        }
        return typedQuery.getResultList();
    }

    @Override
    public int getResultMax(UsuarioFiltroDTO filtro) {
        String query =  "SELECT count(u) FROM Usuario u WHERE u.activo = 1  ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }

    private Map<String,Object> getParameters(UsuarioFiltroDTO filtro) {
        Map<String,Object> parameters = new HashMap<>();

        if (StringUtils.hasText(filtro.getTexto())) {
            parameters.put("texto", "%" + filtro.getTexto().trim() + "%");
        }
        if (StringUtils.hasText(filtro.getDni())) {
            parameters.put("dni", "%" + collateLiteral(filtro.getDni().trim()) + "%");
        }
        if (StringUtils.hasText(filtro.getEmail())) {
            parameters.put("email", "%" + collateLiteral(filtro.getEmail().trim()) + "%");
        }
        if (StringUtils.hasText(filtro.getTelefono())) {
            parameters.put("telefono", "%" + collateLiteral(filtro.getTelefono().trim()) + "%");
        }
        if (filtro.getIdRol() > 0) {
            parameters.put("idRol", + filtro.getIdRol());
        }
        if (StringUtils.hasText(filtro.getNombre())) {
            parameters.put("nombre", "%" + collateLiteral(filtro.getNombre().trim()) + "%");
        }
        return parameters;
    }


    private String getConditions(UsuarioFiltroDTO filtro) {
        StringBuilder queryConditions = new StringBuilder();

        if (StringUtils.hasText(filtro.getEmail())) {
            queryConditions.append(" AND (u.email LIKE :email)");
        }
        if (StringUtils.hasText(filtro.getTelefono())) {
            queryConditions.append(" AND (u.telefono LIKE :telefono)");
        }
        if (StringUtils.hasText(filtro.getDni())) {
            queryConditions.append(" AND (u.dni LIKE :dni)");
        }
        if (filtro.getIdRol() > 0) {
            queryConditions.append(" AND (u.rol.idRol IN :idRol)");
        }
        if (StringUtils.hasText(filtro.getNombre())) {
            queryConditions.append(" AND (u.nombre LIKE :nombre)");
        }
        return queryConditions.toString();
    }

    private String getOrder(UsuarioFiltroDTO filtro) {
        String orderQuery = " ORDER BY u.idUsuario ";
        return orderQuery;
    }
}

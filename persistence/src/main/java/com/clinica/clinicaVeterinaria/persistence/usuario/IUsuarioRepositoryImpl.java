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
        String query = "SELECT u "
                + "FROM Usuario u "
                + "WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Usuario> typedQuery = em.createQuery(query + queryConditions + orderQuery, Usuario.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        if (filtro.getPageable()){
            typedQuery.setFirstResult(filtro.getPageNumber() * filtro.getPageElements());
            typedQuery.setMaxResults(filtro.getPageElements());
        }

        return typedQuery.getResultList();
    }

    @Override
    public int getResultMax(UsuarioFiltroDTO filtro) {
        String query =  "SELECT count(u) "
                + "FROM Usuario u "
                + "WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }


    private String getConditions(UsuarioFiltroDTO filtro) {
        String queryConditions = "";

        if (StringUtils.hasText(filtro.getDni())) {
            queryConditions += " AND (u.dni LIKE :dni) ";
        }

        return queryConditions;
    }

    private Map<String,Object> getParameters(UsuarioFiltroDTO filtro) {
        Map<String,Object> parameters = new HashMap<>();

        if (StringUtils.hasText(filtro.getDni())) {
            parameters.put("dni", "%" + filtro.getDni() + "%");
        }

        return parameters;
    }

    private String getOrder(UsuarioFiltroDTO filtro) {
        String orderQuery = " ORDER BY u.idUsuario ";
    //Posible futura ordenacion
        return orderQuery;
    }
}

package com.clinica.clinicaVeterinaria.persistence.tratamiento;

import com.clinica.clinicaVeterinaria.business.tratamiento.ITratamientoRepositoryCustom;
import com.clinica.clinicaVeterinaria.domain.entities.Tratamiento;
import com.clinica.clinicaVeterinaria.domain.filtros.TratamientoFiltroDTO;
import com.clinica.clinicaVeterinaria.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ITratamientoRepositoryImpl extends IBaseRepositoryImpl implements ITratamientoRepositoryCustom {
    @Override
    public List<Tratamiento> findTratamientosPorFiltro(TratamientoFiltroDTO filtro) {
        String query = "SELECT t FROM Tratamiento t WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Tratamiento> typedQuery = em.createQuery(query + queryConditions + orderQuery, Tratamiento.class);
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
    public int getResultMax(TratamientoFiltroDTO filtro) {
        String query = "SELECT count(t) FROM Tratamiento t WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }
    private Map<String,Object> getParameters(TratamientoFiltroDTO filtro) {
        Map<String,Object> parameters = new HashMap<>();
        if (StringUtils.hasText(filtro.getNombre())) {
            parameters.put("nombre", "%" + filtro.getNombre().trim() + "%");
        }
        if (filtro.getEsVacuna() > 0) {
            parameters.put("esVacuna", + filtro.getEsVacuna());
        }
        if (filtro.getStock() > 0) {
            parameters.put("stock", + filtro.getStock());
        }
        if (StringUtils.hasText(filtro.getTexto())) {
            parameters.put("texto", "%" + filtro.getTexto().trim() + "%");
        }

        return parameters;
    }
    private String getConditions(TratamientoFiltroDTO filtro) {
        StringBuilder queryConditions = new StringBuilder();

        if (StringUtils.hasText(filtro.getNombre())) {
            queryConditions.append(" AND (t.nombre LIKE :nombre)");
        }
        if (filtro.getEsVacuna() > 0) {
            queryConditions.append(" AND (t.esVacuna IN :esVacuna)");
        }
        if (filtro.getStock() > 0){
            queryConditions.append(" AND (t.stock <= :stock)");
        }
        return queryConditions.toString();
    }
    private String getOrder(TratamientoFiltroDTO filtro) {
        String orderQuery = " ORDER BY t.idTratamiento ";
        List<String> orderParameters = Arrays.asList("");

        return orderQuery;
    }
}

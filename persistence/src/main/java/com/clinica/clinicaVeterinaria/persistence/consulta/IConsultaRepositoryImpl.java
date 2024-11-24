package com.clinica.clinicaVeterinaria.persistence.consulta;

import com.clinica.clinicaVeterinaria.business.consulta.IConsultaRepositoryCustom;
import com.clinica.clinicaVeterinaria.domain.entities.Consulta;
import com.clinica.clinicaVeterinaria.domain.filtros.ConsultaFiltroDTO;
import com.clinica.clinicaVeterinaria.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IConsultaRepositoryImpl extends IBaseRepositoryImpl implements IConsultaRepositoryCustom {
    @Override
    public List<Consulta> findConsultasPorFiltro(ConsultaFiltroDTO filtro) {
        String query = "SELECT c FROM Consulta c WHERE c.esCita = 0 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Consulta> typedQuery = em.createQuery(query + queryConditions + orderQuery, Consulta.class);
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
    public int getResultMax(ConsultaFiltroDTO filtro) {
        String query =  "SELECT count(c) FROM Consulta c WHERE c.esCita = 0 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }

    @Override
    public List<Consulta> findCitasPorFiltro(ConsultaFiltroDTO filtro) {
        String query = "SELECT c FROM Consulta c WHERE c.esCita = 1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Consulta> typedQuery = em.createQuery(query + queryConditions + orderQuery, Consulta.class);
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
    public int getResultMaxCitas(ConsultaFiltroDTO filtro) {
        String query =  "SELECT count(c) FROM Consulta c WHERE c.esCita = 1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }

    private Map<String,Object> getParameters(ConsultaFiltroDTO filtro) {
        Map<String,Object> parameters = new HashMap<>();
        if (filtro.getIdConsulta() > 0) {
            parameters.put("idConsulta", + filtro.getIdConsulta());
        }
        if (filtro.getIdVeterinario() > 0) {
            parameters.put("idVeterinario", + filtro.getIdVeterinario());
        }
        if (filtro.getFechaCitaConsulta() != null) {
            parameters.put("fechaCitaConsulta", filtro.getFechaCitaConsulta());
        }
        if (filtro.getHoraCita() != null) {
            parameters.put("horaCita", filtro.getHoraCita());
        }
        if (StringUtils.hasText(filtro.getTexto())) {
            parameters.put("texto", "%" + filtro.getTexto().trim() + "%");
        }

        return parameters;
    }
    private String getConditions(ConsultaFiltroDTO filtro) {
        StringBuilder queryConditions = new StringBuilder();

        if (filtro.getIdConsulta() > 0) {
            queryConditions.append(" AND (c.idConsulta IN :idConsulta)");
        }
        if (filtro.getIdVeterinario() > 0) {
            queryConditions.append(" AND (c.idVeterinario IN :idVeterinario)");
        }
        if (StringUtils.hasText(filtro.getTexto())) {
            queryConditions.append(" AND (c.motivo LIKE :texto)");
        }
        if (filtro.getFechaCitaConsulta() != null) {
            queryConditions.append(" AND (c.fechaCitaConsulta = :fechaCitaConsulta)");
        }
        if (filtro.getHoraCita() != null) {
            queryConditions.append(" AND (c.horaCita = :horaCita)");
        }
        return queryConditions.toString();
    }

    private String getOrder(ConsultaFiltroDTO filtro) {
        String orderQuery = " ORDER BY c.fechaAlta ";
        List<String> orderParameters = Arrays.asList("");

        return orderQuery;
    }
}

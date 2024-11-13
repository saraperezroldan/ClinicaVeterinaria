package com.clinica.clinicaVeterinaria.persistence.mascota;

import com.clinica.clinicaVeterinaria.business.mascota.IMascotaRepositoryCustom;
import com.clinica.clinicaVeterinaria.domain.entities.Mascota;
import com.clinica.clinicaVeterinaria.domain.filtros.MascotaFiltroDTO;
import com.clinica.clinicaVeterinaria.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IMascotaRepositoryImpl extends IBaseRepositoryImpl implements IMascotaRepositoryCustom {

    @Override
    public List<Mascota> findMascotaPorFiltro (MascotaFiltroDTO filtro) {
        String query = "SELECT m FROM Mascota m WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);
        String orderQuery = getOrder(filtro);

        TypedQuery<Mascota> typedQuery = em.createQuery(query + queryConditions + orderQuery, Mascota.class);
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
    public int getResultMax(MascotaFiltroDTO filtro) {
        String query =  "SELECT count(m) FROM Mascota m WHERE 1=1 ";

        Map<String,Object> parameters = getParameters(filtro);
        String queryConditions = getConditions(filtro);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }

    private Map<String,Object> getParameters(MascotaFiltroDTO filtro) {
        Map<String,Object> parameters = new HashMap<>();

        if (StringUtils.hasText(filtro.getTexto())) {
            parameters.put("texto", "%" + filtro.getTexto().trim() + "%");
        }

        return parameters;
    }
    private String getConditions(MascotaFiltroDTO filtro) {
        String queryConditions = "";

        if (StringUtils.hasText(filtro.getTexto())) {
            queryConditions += " AND (m.nombre LIKE :texto )";
        }

        return queryConditions;
    }

    private String getOrder(MascotaFiltroDTO filtro) {
        String orderQuery = " ORDER BY m.idMascota ";
        List<String> orderParameters = Arrays.asList(" ");
        if (StringUtils.hasText(filtro.getOrderBy()) && filtro.getOrderBy().equalsIgnoreCase("cc.nombreCanalComercial")) {
            //orderQuery = "ORDER BY TRIM(UPPER(cc.nombreCanalComercial)) " + (filtro.getOrderDesc() ? " DESC" : " ASC");
        } else if (StringUtils.hasText(filtro.getOrderBy()) && orderParameters.contains(filtro.getOrderBy())) {
            orderQuery = "ORDER BY " + filtro.getOrderBy() + (filtro.getOrderDesc() ? " DESC" : " ASC");
        }

        return orderQuery;
    }
}

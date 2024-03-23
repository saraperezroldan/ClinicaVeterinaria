package com.clinica.clinicaVeterinaria.persistence;


import org.springframework.stereotype.Repository;
import com.clinica.clinicaVeterinaria.business.IBaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IBaseRepositoryImpl implements IBaseRepository {

	@PersistenceContext
	public EntityManager em;
	
	public String translate(String nombreCampo, String wildCardCampo) {
		String clausulaTranslate = "replace(replace(replace(replace(replace(lower(%s), 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u') like  "
				+ "replace(replace(replace(replace(replace(lower(%s), 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u')"; 
		return String.format(clausulaTranslate, nombreCampo, wildCardCampo);
	}

	@Override
	public void refresh(Object entity) {
		em.refresh(entity);
	}
}

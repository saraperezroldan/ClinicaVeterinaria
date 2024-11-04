package com.clinica.clinicaVeterinaria.business.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.clinica.clinicaVeterinaria.domain.types.LanguageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

@Service
public class LocaleServiceImpl implements ILocaleService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    /**
     * Obtiene un mensaje a partir de un código definido en los ficheros messages_*.properties
     * según el Locale definido al iniciar la sesión del usuario.
     * @param codeMessage Identificador del mensaje
     * @return Mensaje localizado
     */
    @Override
    public String getMessage(String codeMessage) {
        return this.messageSource.getMessage(codeMessage, null, LocaleContextHolder.getLocale());
    }

    /**
     * Obtiene un mensaje a partir de un código definido en los ficheros messages_*.properties
     * según el Locale definido al iniciar la sesión del usuario aplicando el reemplazo de los parámetros params.
     * @param codeMessage Identificador del mensaje
     * @param params Colección de objetos para ser reemplazados en el mensaje localizado según la posición de los parámetros.
     * @return Mensaje localizado con reemplazo de parámetros.
     */
    @Override
    public String getMessage(String codeMessage, Object[] params) {
        return this.messageSource.getMessage(codeMessage, params, LocaleContextHolder.getLocale());
    }

    /**
     * Obtiene un mensaje a partir de un código definido en los ficheros messages_*.properties
     * según el Locale que está asociado al código de Idioma.
     * @param codeMessage Identificador del mensaje
     * @param params Colección de objetos para ser reemplazados en el mensaje localizado según la posición de los parámetros.
     * @param codeLanguage Código del idioma para identificar Locale específico a aplicar.
     * @return Mensaje localizado con reemplazo de parámetros.
     */
    @Override
    public String getMessage(String codeMessage, Object[] params, String codeLanguage) {
        LanguageType languageType = LanguageType.getLanguageType(codeLanguage);
        return this.messageSource.getMessage(codeMessage, params, languageType.getLocale());
    }

    @Override
    /**
     * Cambiar el locale asociado a la sesión HTTP a un idioma concreto.
     * @param request: Petición HTTP asociada a la sesión.
     * @param response: Respuesta HTTP de la petición.
     * @param codeLanguage: Código de idioma al que se desea cambiar el locale de la sesión.
     */
    public void changeLocale(HttpServletRequest request, HttpServletResponse response, String codeLanguage) {
        LanguageType languageType = LanguageType.getLanguageType(codeLanguage);
        this.localeResolver.setLocale(request, response, languageType.getLocale());
    }
}

package com.clinica.clinicaVeterinaria.business.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILocaleService {
    public String getMessage(String codeMessage);
    public String getMessage(String codeMessage, Object[] params);
    public String getMessage(String codeMessage, Object[] params, String codeLanguage);
    public void changeLocale(HttpServletRequest request, HttpServletResponse response, String codeLanguage);
}

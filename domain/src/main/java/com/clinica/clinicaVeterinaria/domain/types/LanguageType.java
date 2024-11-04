package com.clinica.clinicaVeterinaria.domain.types;

import java.util.Locale;

public enum LanguageType {

    ES(new Locale("es", "ES")),
    CAT(new Locale("cat", "ES"));

    LanguageType(Locale locale){
        this.locale = locale;
    }

    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public static LanguageType getLanguageType(String codeLanguage) {
        return LanguageType.valueOf(codeLanguage);
    }
}


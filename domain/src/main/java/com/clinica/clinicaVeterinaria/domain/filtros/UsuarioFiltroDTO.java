package com.clinica.clinicaVeterinaria.domain.filtros;

public class UsuarioFiltroDTO extends BaseFiltroDTO {
    private String dni;
    private String email;
    private String telefono;

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
}
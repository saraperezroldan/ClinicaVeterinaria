package com.clinica.clinicaVeterinaria.domain.filtros;

public class TratamientoFiltroDTO extends BaseFiltroDTO {
    private String nombre;
    private int esVacuna;
    private int stock;

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getEsVacuna() {return esVacuna;}
    public void setEsVacuna(int esVacuna) {this.esVacuna = esVacuna;}
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
}


package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "especie")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecie;
    private String nombre;

    public Especie() {
    }

    public Especie(int idEspecie, String descripcion) {
        this.idEspecie = idEspecie;
        this.nombre = descripcion;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

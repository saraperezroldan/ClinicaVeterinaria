
package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "raza")
public class Raza {
    @Id
    @GeneratedValue
    private int idRaza;
    private String nombre;

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_especie"
    )
    private Especie especie;

    public Raza() {
    }

    public Raza(int idRaza, String nombre, Especie especie) {
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.especie = especie;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}

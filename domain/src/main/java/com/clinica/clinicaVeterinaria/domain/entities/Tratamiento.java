package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idTratamiento;
    private String nombre;
    private String descripcion;
    private int esVacuna;
    private float precio;

    @OneToMany(mappedBy = "tratamiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ConsultaTratamiento> tratamientosConsulta;

    public Tratamiento() {
    }

    public Tratamiento(int idTratamiento, String nombre, String descripcion, float precio) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getIdTratamiento() {return idTratamiento;}
    public void setIdTratamiento(int idTratamiento) {this.idTratamiento = idTratamiento;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public int getEsVacuna() {return esVacuna;}
    public void setEsVacuna(int esVacuna) {this.esVacuna = esVacuna;}
    public float getPrecio() {return precio;}
    public void setPrecio(float precio) {this.precio = precio;}
    public Set<ConsultaTratamiento> getTratamientosConsulta() {return tratamientosConsulta;}
    public void setTratamientosConsulta(Set<ConsultaTratamiento> tratamientosConsulta) {this.tratamientosConsulta = tratamientosConsulta;}
}
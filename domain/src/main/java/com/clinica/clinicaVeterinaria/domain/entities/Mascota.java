package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMascota;
    private String nombre;
    private int edad;
    private float peso;
    private String genero;
    private String imagen;
    private String complexion;
    private int activo;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;

    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /*@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_raza")
    private Raza raza;*/

    public Mascota(){}

    public int getIdMascota() {return idMascota;}

    public void setIdMascota(int idMascota) {this.idMascota = idMascota;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public float getPeso() {return peso;}

    public void setPeso(float peso) {this.peso = peso;}

    public String getGenero() {return genero;}

    public void setGenero(String genero) {this.genero = genero;}

    public String getComplexion() {return complexion;}

    public void setComplexion(String complexion) {this.complexion = complexion;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public Date getFechaNacimiento() {return fechaNacimiento;}

    public void setFechaNacimiento(Date fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public int getActivo() {return activo;}

    public void setActivo(int activo) {this.activo = activo;}

    public Date getFechaAlta() {return fechaAlta;}

    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}

    public Date getFechaModificacion() {return fechaModificacion;}

    public void setFechaModificacion(Date fechaModificacion) {this.fechaModificacion = fechaModificacion;}

    public Date getFechaBaja() {return fechaBaja;}

    public void setFechaBaja(Date fechaBaja) {this.fechaBaja = fechaBaja;}

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    //public Raza getRaza() {return raza;}

    //public void setRaza(Raza raza) {this.raza = raza;}
}
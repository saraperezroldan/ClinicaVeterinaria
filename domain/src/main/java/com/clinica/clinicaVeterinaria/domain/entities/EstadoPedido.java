package com.clinica.clinicaVeterinaria.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado_pedido")
public class EstadoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstadoPedido;
	private String descripcion;
	
	public void setIdEstadoPedido(int codigoTipoPedido) {
		this.idEstadoPedido = codigoTipoPedido;
	}
	
	public int getIdEstadoPedido() {
		return this.idEstadoPedido;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}

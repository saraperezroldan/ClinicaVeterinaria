package com.clinica.clinicaVeterinaria.domain.dtos;

import com.clinica.clinicaVeterinaria.domain.entities.EstadoPedido;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

public class EstadoPedidoDTO {
	private int idEstadoPedido;
	private String descripcion;
	
	public static EstadoPedidoDTO toDTO(EstadoPedido tipoPedido) {
		EstadoPedidoDTO estadoPedidoDTO = new EstadoPedidoDTO();
		
		if (tipoPedido == null) {
			return estadoPedidoDTO;
		}
			
		estadoPedidoDTO.setIdEstadoPedido(Math.max(tipoPedido.getIdEstadoPedido(), 0));
		estadoPedidoDTO.setDescripcion(StringUtils.hasText(tipoPedido.getDescripcion()) ? tipoPedido.getDescripcion().trim() : "");
		
		return estadoPedidoDTO;
	}
	
	public static List<EstadoPedidoDTO> toDTO(List<EstadoPedido> tiposPedido) {
		if (tiposPedido == null) {
			return Collections.emptyList();
		}
		
		return tiposPedido.stream()
				.map(EstadoPedidoDTO::toDTO)
				.collect(Collectors.toList());
	}
	
	public static EstadoPedido toDomain(EstadoPedidoDTO estadoPedidoDTO) {
		EstadoPedido tipoPedido = new EstadoPedido();
		
		if (estadoPedidoDTO == null) {
			return null;
		}
		
		tipoPedido.setIdEstadoPedido(Math.max(estadoPedidoDTO.getIdEstadoPedido(), 0));
		tipoPedido.setDescripcion(StringUtils.hasText(estadoPedidoDTO.getDescripcion()) ? estadoPedidoDTO.getDescripcion().trim() : "");

		return tipoPedido;
	}
	
	public static List<EstadoPedido> toDomain(List<EstadoPedidoDTO> tiposPedidoDTO) {
		if (tiposPedidoDTO == null) {			
			return Collections.emptyList();
		}
		
		return tiposPedidoDTO.stream()
				.map(EstadoPedidoDTO::toDomain)
				.collect(Collectors.toList());
	}

	public int getIdEstadoPedido() {
		return idEstadoPedido;
	}

	public void setIdEstadoPedido(int idEstadoPedido) {
		this.idEstadoPedido = idEstadoPedido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

package com.compras.demo.beans.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraDTORequest {
	@NotNull
	private String codProducto;
	@NotNull
	private Double cantidad;
	@NotNull
	private Double precio;
	@NotNull
	private Double total;
	@NotNull
	private Long usuarioId;
	
}

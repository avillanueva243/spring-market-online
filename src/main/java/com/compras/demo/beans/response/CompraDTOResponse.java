package com.compras.demo.beans.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraDTOResponse {
	private Long compraId;
	private String codProducto;
	private String nombreProducto;
	private Double cantidad;
	private Double precio;
	private Double total;
	private Long usuarioId;
	
}

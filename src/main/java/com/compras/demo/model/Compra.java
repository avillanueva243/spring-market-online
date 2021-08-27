package com.compras.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compra {
	private Long compraId;
	private Producto producto;
	private Double cantidad;
	private Double precio;
	private Double total;
	private Long usuarioId;
	
}

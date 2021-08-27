package com.compras.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
	private String codigo;
	private String descripcion;
	private String nombre;
	private Double precio;
	private Double stock;
	private Boolean activo;
}

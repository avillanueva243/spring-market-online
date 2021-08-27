package com.compras.demo.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Respuesta<T> {
	private boolean success;
	private T data;
	private ResponseDetail response;

	public Respuesta(boolean success, T data, ResponseDetail response) {
		this.success=success;
		this.data=data;
		this.response=response;
	}
	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	public static class ResponseDetail{
		private String codigo;
		private String titulo;
		private String mensaje;
		public ResponseDetail(String codigo,String titulo,String mensaje) {
			this.codigo=codigo;
			this.titulo=titulo;
			this.mensaje=mensaje;
		}
	}
}

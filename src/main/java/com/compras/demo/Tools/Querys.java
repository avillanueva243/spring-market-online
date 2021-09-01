package com.compras.demo.Tools;

public class Querys {
	public static final String GET_COMPRAS_BY_USER_ID="select c.*, p.NOMBRE from AUTORIZACION.COMPRAS c "
														+ "inner join AUTORIZACION.PRODUCTOS p on p.CODIGO=c.CODPRODUCTO "
														+ "where c.USUARIOID=?";
	public static final String INSERT_COMPRA="insert into AUTORIZACION.COMPRAS(CODPRODUCTO,CANTIDAD,PRECIO,TOTAL,USUARIOID) values(?,?,?,?,?)";
	
	public static final String GET_USER_BY_NOMBRE = "select u.* from AUTORIZACION.USUARIOS u where u.NOMBRE=?";
}

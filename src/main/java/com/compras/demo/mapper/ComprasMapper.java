package com.compras.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.compras.demo.model.Compra;
import com.compras.demo.model.Producto;


public class ComprasMapper implements RowMapper<Compra> {

	@Override
	public Compra mapRow(ResultSet rs, int rowNum) throws SQLException {
		Compra compra=new Compra();
    	compra.setCompraId(rs.getLong("COMPRAID"));
    	Producto producto=new Producto();
    	producto.setCodigo(rs.getString("CODPRODUCTO"));
    	producto.setNombre(rs.getString("NOMBRE"));
    	compra.setProducto(producto);
    	compra.setCantidad(rs.getDouble("CANTIDAD"));
    	compra.setPrecio(rs.getDouble("PRECIO"));
    	compra.setTotal(rs.getDouble("TOTAL"));
    	compra.setUsuarioId(rs.getLong("USUARIOID"));
		return compra;
	}

}

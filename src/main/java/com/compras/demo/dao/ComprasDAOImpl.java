package com.compras.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.compras.demo.Tools.Querys;
import com.compras.demo.mapper.ComprasMapper;
import com.compras.demo.model.Compra;

@Repository
public class ComprasDAOImpl implements ComprasDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	ComprasMapper mapper=new ComprasMapper();
	
	
	@Override
	public List<Compra> getAll(Long usuarioId) {
		return jdbcTemplate.query(Querys.GET_COMPRAS_BY_USER_ID, mapper,usuarioId);
	}

	@Override
	public boolean insert(Compra compra) {
		jdbcTemplate.update(Querys.INSERT_COMPRA, compra.getProducto().getCodigo(),compra.getCantidad(),compra.getPrecio(),compra.getTotal(), compra.getUsuarioId());
		return true;
	}

}

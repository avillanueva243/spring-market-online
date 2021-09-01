package com.compras.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.compras.demo.Tools.Querys;
import com.compras.demo.mapper.UsuarioMapper;
import com.compras.demo.model.Usuario;

@Repository
public class UsuariosDAOImpl implements UsuariosDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	UsuarioMapper mapper=new UsuarioMapper();

	@Override
	public Usuario getByNombre(String nombre) {
		List<Usuario> list= jdbcTemplate.query(Querys.GET_USER_BY_NOMBRE, mapper,nombre);
		return list==null || list.isEmpty()? null: list.get(0);
	}
	
	

}

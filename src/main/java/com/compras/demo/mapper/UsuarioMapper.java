package com.compras.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.compras.demo.model.Usuario;


public class UsuarioMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario=new Usuario();
    	usuario.setUsuarioId(rs.getLong("USUARIOID"));
    	usuario.setNombre(rs.getString("NOMBRE"));
    	usuario.setClave(rs.getString("CLAVE"));
    	usuario.setPerfil(rs.getString("PERFIL"));
		return usuario;
	}

}

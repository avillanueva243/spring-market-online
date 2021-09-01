package com.compras.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compras.demo.dao.UsuariosDAO;
import com.compras.demo.model.Usuario;
@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private UsuariosDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user= usuarioDAO.getByNombre(username);
		List<GrantedAuthority> auth=new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(user.getPerfil()));
		return new User(user.getNombre(),user.getClave(),auth);
	}
	

}

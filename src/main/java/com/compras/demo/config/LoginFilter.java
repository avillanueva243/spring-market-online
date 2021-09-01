package com.compras.demo.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.compras.demo.Tools.ResponseCodes;
import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.Respuesta.ResponseDetail;
import com.compras.demo.beans.request.UsuarioDTORequest;
import com.compras.demo.beans.response.TokenDTOResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginFilter extends AbstractAuthenticationProcessingFilter{

    private final ObjectMapper mapper;
    
	protected LoginFilter(String url, AuthenticationManager authenticationManager, ObjectMapper mapper) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		this.mapper=mapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		UsuarioDTORequest usuario = mapper.readValue(request.getInputStream(), UsuarioDTORequest.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsuario(), usuario.getContrasena(), Collections.emptyList()));
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		User user= (User) auth.getPrincipal();
		String permisos=auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
		
		String token = Jwts.builder().setSubject(user.getUsername()).setExpiration(new Date(System.currentTimeMillis() + 3600000))
						.signWith(SignatureAlgorithm.HS512, "secreto@")
						.claim("permisos", permisos)
						.compact();
		
		
		res.setCharacterEncoding("utf-8");
		
		TokenDTOResponse tokenResponse=new TokenDTOResponse();
		tokenResponse.setToken("Bearer "+token);
        Respuesta<TokenDTOResponse> respuesta= new Respuesta<>(true,tokenResponse, new ResponseDetail(ResponseCodes.RESPUESTA_CORRECTA,"usuario correcto","usuario correcto"));

        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(res.getWriter(), respuesta);
	}
}

package com.compras.demo.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.compras.demo.Tools.ResponseCodes;
import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.Respuesta.ResponseDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenFilter extends OncePerRequestFilter {

	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		try {
			if(!existeJWTToken(request)) {
		        throw new UnsupportedJwtException("No existe token");
			}
			
			String user=validateToken(request);
			if(user==null) {
		        throw new UnsupportedJwtException("No existe usuario");
			}
			
			Authentication auth=getAuthentication(request); 
			SecurityContextHolder.getContext().setAuthentication(auth);
		
        	filterChain.doFilter(request,response);
        	
		} catch (ExpiredJwtException e)  {
	        Respuesta<Object> respuesta= new Respuesta<>(false,null, new ResponseDetail(ResponseCodes.TOKEN_EXPIRADO,"token expirado","token expirado"));
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        mapper.writeValue(response.getWriter(), respuesta);
		} catch(Exception e) {
	        Respuesta<Object> respuesta= new Respuesta<>(false,null, new ResponseDetail(ResponseCodes.ERROR_DESCONOCIDO,"ocurrio un error","ocurrio un error"));
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        mapper.writeValue(response.getWriter(), respuesta);
		}
	}
	

	private String validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		return Jwts.parser().setSigningKey("secreto@").parseClaimsJws(jwtToken).getBody().getSubject();
	}

	private boolean existeJWTToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader("Authorization");
		return authenticationHeader != null && authenticationHeader.startsWith("Bearer ");
	}
	
	
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			final Claims claims =Jwts.parser().setSigningKey("secreto@").parseClaimsJws(token.replace("Bearer", "")).getBody();
			String user =  claims.getSubject();
			
			final Collection<SimpleGrantedAuthority> authorities =
					Arrays.stream(claims.get("permisos").toString().split(","))
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
			
			return user != null ? new UsernamePasswordAuthenticationToken(user, token, authorities) : null;
		}
		return null;
	}
	
	

}

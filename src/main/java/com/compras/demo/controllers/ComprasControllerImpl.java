package com.compras.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.beans.response.CompraDTOResponse;
import com.compras.demo.services.ComprasService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ComprasControllerImpl implements ComprasController {

	@Autowired
	private ComprasService comprasService;
	
	@Override
	public ResponseEntity<Respuesta<List<CompraDTOResponse>>> getAll(Long usuarioId) {
		
		Respuesta<List<CompraDTOResponse>> responses=comprasService.getAll(usuarioId);
		if(responses.isSuccess())
			return new ResponseEntity<>(responses, HttpStatus.OK);
		else
			return new ResponseEntity<>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Respuesta<Boolean>> insert(@Valid CompraDTORequest compra) {
		Respuesta<Boolean> responses=comprasService.insert(compra);
		if(responses.isSuccess())
			return new ResponseEntity<>(responses, HttpStatus.OK);
		else
			return new ResponseEntity<>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

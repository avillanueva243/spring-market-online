package com.compras.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.beans.response.CompraDTOResponse;


public interface ComprasController {

    @RequestMapping(value = "/compra/list/{userId}", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<Respuesta<List<CompraDTOResponse>>> getAll(@PathVariable(value = "userId") Long usuarioId);
    
    @RequestMapping(value = "/compra/insert", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<Respuesta<Boolean>> insert(@Valid @RequestBody CompraDTORequest compra);
}

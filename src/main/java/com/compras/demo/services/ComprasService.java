package com.compras.demo.services;

import java.util.List;

import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.beans.response.CompraDTOResponse;

public interface ComprasService {

    public Respuesta<List<CompraDTOResponse>> getAll(Long usuarioId);

	public Respuesta<Boolean> insert(CompraDTORequest compra) ;
}

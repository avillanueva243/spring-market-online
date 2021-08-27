package com.compras.demo.dao;

import java.util.List;

import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.model.Compra;

public interface ComprasDAO {

    public List<Compra> getAll(Long usuarioId);
	public boolean insert(Compra compra);
}

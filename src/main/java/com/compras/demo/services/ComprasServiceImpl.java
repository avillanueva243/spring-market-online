package com.compras.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compras.demo.Tools.ResponseCodes;
import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.Respuesta.ResponseDetail;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.beans.response.CompraDTOResponse;
import com.compras.demo.dao.ComprasDAO;
import com.compras.demo.model.Compra;
import com.compras.demo.model.Producto;

@Service
public class ComprasServiceImpl implements ComprasService{

	@Autowired
	private ComprasDAO comprasDAO;
	
	@Override
	public Respuesta<List<CompraDTOResponse>> getAll(Long usuarioId) {
		try {
			List<Compra> compras= comprasDAO.getAll(usuarioId);
			List<CompraDTOResponse> listaDTO=new ArrayList<>();
			compras.forEach(x-> listaDTO.add(convertToDTO(x)));
			return new Respuesta<>(true,listaDTO,new ResponseDetail(ResponseCodes.RESPUESTA_CORRECTA,"Respuesta correcta","Se obtuvo los datos correctamente"));
		}catch(Exception ex) {
			return new Respuesta<>(false,null,new ResponseDetail(ResponseCodes.ERROR_BD,"Respuesta incorrecta","hubo un error al consultar los datos"));
		}
	}


	@Override
	@Transactional
	public Respuesta<Boolean> insert(CompraDTORequest compra) {
		try {
			boolean respuesta=comprasDAO.insert(convertToModel(compra));
			return new Respuesta<>(true,respuesta,new ResponseDetail(ResponseCodes.RESPUESTA_CORRECTA,"Respuesta correcta","Se obtuvo los datos correctamente"));
		}catch(Exception ex) {
			return new Respuesta<>(false,false,new ResponseDetail(ResponseCodes.ERROR_BD,"Respuesta incorrecta","hubo un error al insertar los datos"));
		}
	}
	
	public CompraDTOResponse convertToDTO(Compra compra){
		CompraDTOResponse dto=new CompraDTOResponse();
		dto.setCompraId(compra.getCompraId());
		dto.setCantidad(compra.getCantidad());
		dto.setCodProducto(compra.getProducto().getCodigo());
		dto.setNombreProducto(compra.getProducto().getNombre());
		dto.setPrecio(compra.getPrecio());
		dto.setTotal(compra.getTotal());
		dto.setUsuarioId(compra.getUsuarioId());
		return dto;
	}

	public Compra convertToModel(CompraDTORequest compra){
		Compra dto=new Compra();
		dto.setCantidad(compra.getCantidad());
		Producto prod=new Producto();
		prod.setCodigo(compra.getCodProducto());
		dto.setProducto(prod);
		dto.setPrecio(compra.getPrecio());
		dto.setTotal(compra.getTotal());
		dto.setUsuarioId(compra.getUsuarioId());
		return dto;
	}
	
}

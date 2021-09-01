package com.compras.demo.dao;

import java.util.List;

import com.compras.demo.beans.Respuesta;
import com.compras.demo.beans.request.CompraDTORequest;
import com.compras.demo.model.Compra;
import com.compras.demo.model.Usuario;

public interface UsuariosDAO {

    public Usuario getByNombre(String nombre);
}

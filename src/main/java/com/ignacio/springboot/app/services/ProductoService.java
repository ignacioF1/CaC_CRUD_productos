package com.ignacio.springboot.app.services;

import com.ignacio.springboot.app.models.Producto;

public interface ProductoService {

	// Metodo interface para poder utilizar tanto en Service implementacion
	// como tambien en el controller
	public abstract Producto IdProducto(String idProducto);
}
	// Devuelve producto con el id del producto
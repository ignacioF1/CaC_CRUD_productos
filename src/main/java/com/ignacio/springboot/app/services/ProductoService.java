package com.ignacio.springboot.app.services;

import java.util.List;

import com.ignacio.springboot.app.models.Producto;

public interface ProductoService {

	// Metodo interface para poder utilizar tanto en Service implementacion
	// como tambien en el controller
	
	
	// Devuelve producto con el id del producto
	public abstract Producto IdProducto(String idProducto);
	
	// Guarda un producto
	public abstract Producto save(Producto entity);

	// Obtiene Toda la Lista de productos existente en la base de datos
	public abstract List<Producto> findAll();

	// Modifica los datos de 1 producto
	public abstract Producto update(Producto entity);
	
	//Elilmino 1 producto 
	public abstract Producto deleteById(String idProducto);
}
	
package com.ignacio.springboot.app.services;

import java.util.List;
import com.ignacio.springboot.app.models.Login;

public interface LoginService {

	// Metodo interface para poder utilizar tanto en Service implementacion
	// como tambien en el controller
	
	
	// Devuelve producto con el id del producto
	public abstract Login IdLogin(String idLogin);
	
	// Guarda un producto
	public abstract Login save(Login entity);

	// Obtiene Toda la Lista de productos existente en la base de datos
	public abstract List<Login> findAll();

	// Modifica los datos de 1 usuario
	public abstract Login update(Login entity);
	
	//Elilmino 1 Producto 
	public abstract Login deleteById(String idLogin);
}
	
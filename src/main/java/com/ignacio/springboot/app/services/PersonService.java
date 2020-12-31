package com.ignacio.springboot.app.services;

import java.util.List;
import com.ignacio.springboot.app.models.Person;

public interface PersonService {

	// Metodo interface para poder utilizar tanto en Service implementacion
	// como tambien en el controller
	
	
	// Devuelve producto con el id del producto
	public abstract Person IdPerson(String idPerson);
	
	// Guarda un producto
	public abstract Person save(Person entity);

	// Obtiene Toda la Lista de productos existente en la base de datos
	public abstract List<Person> findAll();

	// Modifica los datos de 1 usuario
	public abstract Person update(Person entity);
	
	//Elilmino 1 Producto 
	public abstract Person deleteById(String idLogin);
}
	
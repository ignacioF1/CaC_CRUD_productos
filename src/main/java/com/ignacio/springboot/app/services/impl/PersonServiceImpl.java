package com.ignacio.springboot.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ignacio.springboot.app.models.Person;
import com.ignacio.springboot.app.repository.PersonRepository;
import com.ignacio.springboot.app.services.PersonService;


@Service("personService")
public class PersonServiceImpl implements PersonService {

	// Inyecto el Repository
	// para usar sus funciones e implementar
	// en la lógica de los servicios
	@Autowired
	private PersonRepository personRepository;
	// Con este contrato o método de la capa actual
	// creo la lógica para poder hacer la consulta con mi base de datos Mongo
	@Override
	public Person IdPerson(String idPerson) {
		// Instancio un nuevo objeto producto
		Person person = null;

		// Envolvemos en una validación
		// para que en caso de que falle por algún motivo esa consulta
		// me imprima en la consola en que parte falló mi app
		
		try { // validación, en caso de romper avisa

			person = personRepository.findById(idPerson);

		} catch (Exception e) {
			
			e.printStackTrace();

		}
		// En caso de éxito devuelvo todos los datos que contiene Producto
		return person;
	}

	
	
	@Override
	public Person save(Person entity) {
		// Instancio un nuevo objeto producto
		Person person = null;
		
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app
		try {

			person = personRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// En caso de exito devuelvo todos los datos que contiene Producto
		return person;
	}

	@Override
	public List<Person> findAll() {
		// Instancio una Lista de Productos 
		List<Person> lstPersons = new ArrayList<Person>();
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app

		try {
			lstPersons = personRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// En caso de exito devuelvo Lista de todos Los Productos 
		return lstPersons;
	}

	@Override
	public Person update(Person entity) {
		// Instancio un nuevo objeto producto
		Person person = null;
		
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app

		try {

			person = personRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// En caso de exito devuelvo todos los datos que contiene Producto
		return person;
	}

	@Override
	public Person deleteById(String idPerson) {
		// Instancio un nuevo objeto producto
		Person person = null;

		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app
		try {

			person = personRepository.deleteById(idPerson);

		} catch (Exception e) {

			e.printStackTrace();
		}

		// En caso de exito devuelvo todos los datos que contiene Producto
		return person;
	}

}


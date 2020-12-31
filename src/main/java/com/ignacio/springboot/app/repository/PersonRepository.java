package com.ignacio.springboot.app.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ignacio.springboot.app.models.Person;


@Repository("personRepository")
public interface PersonRepository extends MongoRepository<Person, Serializable> {

	// Dentro de este bloque de código voy a hacer las consultas
	// que necesite contra MongoDB
	
	// Internamente actua como una querry de consulta
	
	// Busca en base de datos un producto por medio del ID
	Person findById(String id);
	
	Person findByEmail(@Param("name") String name);
	
	// Elimina en base de datos un producto por medio del ID
		Person deleteById(String id);
}

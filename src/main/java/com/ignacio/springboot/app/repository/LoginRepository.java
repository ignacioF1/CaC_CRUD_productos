package com.ignacio.springboot.app.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ignacio.springboot.app.models.Login;


@Repository("loginRepository")
public interface LoginRepository extends MongoRepository<Login, Serializable> {

	// Dentro de este bloque de código voy a hacer las consultas
	// que necesite contra MongoDB
	
	// Internamente actua como una querry de consulta
	
	// Busca en base de datos un producto por medio del ID
	Login findById(String id);
	
	
	// Elimina en base de datos un producto por medio del ID
		Login deleteById(String id);
}

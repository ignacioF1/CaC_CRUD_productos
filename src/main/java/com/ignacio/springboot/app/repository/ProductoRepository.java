package com.ignacio.springboot.app.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ignacio.springboot.app.models.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends MongoRepository<Producto, Serializable> {

	// Dentro de este bloque de código voy a hacer las consultas
	// que necesite contra MongoDB
	
	// Internamente actua como una querry de consulta
	// Busca en base de datos un producto por medio del ID
	
	Producto findById(String id);
}

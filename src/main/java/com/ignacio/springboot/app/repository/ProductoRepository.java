package com.ignacio.springboot.app.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ignacio.springboot.app.models.Producto;

@Repository("ProductoRepository")
public interface ProductoRepository extends MongoRepository<Producto, Serializable> {

	Producto findById(String id);
}

package com.ignacio.springboot.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignacio.springboot.app.models.Producto;
import com.ignacio.springboot.app.repository.ProductoRepository;
import com.ignacio.springboot.app.services.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	// Inyecto el Repository
	// para usar sus funciones e implementar
	// en la lógica de los servicios
	@Autowired
	private ProductoRepository productoRepository;
	// Con este contrato o método de la capa actual
	// creo la lógica para poder hacer la consulta con mi base de datos Mongo
	@Override
	public Producto IdProducto(String idProducto) {
		// Instancio un nuevo objeto producto
		Producto producto = null;

		// Envolvemos en una validación
		// para que en caso de que falle por algún motivo esa consulta
		// me imprima en la consola en que parte falló mi app
		
		try { // validación, en caso de romper avisa

			producto = productoRepository.findById(idProducto);

		} catch (Exception e) {
			
			e.printStackTrace();

		}
		// En caso de éxito devuelvo todos los datos que contiene Producto
		return producto;
	}

}

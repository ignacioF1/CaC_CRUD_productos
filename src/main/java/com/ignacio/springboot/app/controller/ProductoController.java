package com.ignacio.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ignacio.springboot.app.models.Producto;
import com.ignacio.springboot.app.services.ProductoService;

@RestController
@CrossOrigin
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// Método GET para obtener los datos de 1 producto por su ID
	@GetMapping(value = "/producto/{idProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> findByProducto(@PathVariable String idProducto) {

		// Instancio un nuevo objeto producto
		Producto producto = new Producto();
		
		// Llamo al servicio creado y le paso por parámetro el idProducto
		producto = productoService.IdProducto(idProducto);
		
		// Si el servicio me devolvio un resultado exitoso (200)
		// devuelvo al FrontEnd todos los datos del producto solicitado
		return ResponseEntity.ok(producto);
		// return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
}

package com.ignacio.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@GetMapping(value = "/producto/{idProducto}")
	public ResponseEntity<Producto> findByProducto(@PathVariable String idProducto) {

		Producto producto = new Producto();
		producto = productoService.IdProducto(idProducto);
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
}

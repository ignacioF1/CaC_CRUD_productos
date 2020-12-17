package com.ignacio.springboot.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignacio.springboot.app.models.Producto;
import com.ignacio.springboot.app.repository.ProductoRepository;
import com.ignacio.springboot.app.services.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto IdProducto(String idProducto) {

		Producto producto = null;

		try { // validación, en caso de romper avisa

			producto = productoRepository.findById(idProducto);

		} catch (Exception e) {
			// handle exception

		}

		return null;
	}

}

package com.ignacio.springboot.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ignacio.springboot.app.models.Login;
import com.ignacio.springboot.app.services.LoginService;

@RestController
//@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	
	// Metodo POST para crear un nuevo Producto
	// Persisto o Guardo en la BBDD
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> save(@RequestBody Login login) {

		Login loginData = loginService.save(login);

		// Si el servicio me devolvio un resultado exitoso o 200
		// devuelvo al FrontEnd todos los datos del usuario Creado
		return ResponseEntity.ok(loginData);

	}
	
	
	
	
	// Metodo GET para obtener toda la lista de productos
	// existentes en nuestra base de datos
	@GetMapping(value = "/logins", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() {
		// Instancio un nueva lista de Productos
		List<Login> logins = new ArrayList<Login>();

		// llamo al servicio creado y obtengo la lista
		logins = loginService.findAll();

		// Si el servicio me devolvio un resultado exitoso o 200
		// devuelvo al FrontEnd toda la lista de productos
		return ResponseEntity.ok(logins);
	}
	

	
	
	// Método GET para obtener los datos de 1 producto por su ID
	@GetMapping(value = "/login/{idLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> findByLogin(@PathVariable String idLogin) {

		// Instancio un nuevo objeto producto
		Login login = new Login();
		
		// Llamo al servicio creado y le paso por parámetro el idProducto
		login = loginService.IdLogin(idLogin);
		
		// Si el servicio me devolvio un resultado exitoso (200)
		// devuelvo al FrontEnd todos los datos del producto solicitado
		return ResponseEntity.ok(login);
		// return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	
	
	
	// Metodo PUT para modificar un producto existente
	// Persisto y actualizo en la BBDD
	@PutMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> updateLogin(@RequestBody Login login) {

		// llamo al servicio creado y le paso los nuevos datos
		// NO TENGO QUE MODIFICAR EL ID, tiene que ser el mismo
		Login loginData = loginService.save(login);

		// Si el servicio me devolvio un resultado exitoso o 200
		// devuelvo al FrontEnd todos los datos del Producto actualizado
		return ResponseEntity.ok(loginData);

	}

	// Metodo DELETE para eliminar los datos de 1 producto por su ID
	@DeleteMapping(value = "/login/{idLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> deleteFindByIdLogin(@PathVariable String idLogin) {

		// Instancio un nuevo objeto producto
		Login login = new Login();

		// llamor al servicio creado y le paso por parametro el idProducto
		login = loginService.deleteById(idLogin);

		// Si el servicio me devolvio un resultado exitoso o 200
		// Elimino todos los datos del Producto solicitado
		return ResponseEntity.ok(login);
	}
}

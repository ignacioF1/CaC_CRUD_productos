package com.ignacio.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ignacio.springboot.app.models.Person;
import com.ignacio.springboot.app.repository.PersonRepository;
import com.ignacio.springboot.app.repository.Util;
import com.ignacio.springboot.app.services.PersonService;

@RestController
@CrossOrigin
public class PersonController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PersonService personService;

	@Autowired
	PersonRepository personRepository;

	/*// LOGIN
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> login(

			@RequestParam String email, @RequestParam String password) {

		if (email.isEmpty() || password.isEmpty()) {
			return new ResponseEntity<>(Util.makeMap("error","Missing data"), HttpStatus.FORBIDDEN);
		}

		if (personRepository.findByEmail(email) != null) {
			// Check if password is ok
			
			if (personRepository.findByEmail(email).getPassword() == passwordEncoder.encode(password)) {

				return new ResponseEntity<>(Util.makeMap("OK","Logged in"), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(Util.makeMap("error","Wrong password"), HttpStatus.FORBIDDEN);

	} */
	
	

	// REGISTER
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> register(

			@RequestParam String email, @RequestParam String password) {

		if (email.isEmpty() || password.isEmpty()) {
			return new ResponseEntity<>("Datos faltantes", HttpStatus.FORBIDDEN);
		}

		if (personRepository.findByEmail(email) != null) {
			return new ResponseEntity<>("Usuario no disponible", HttpStatus.FORBIDDEN);
		}
		if(Util.isValid(email) == false) {
			return new ResponseEntity<>("Correo inválido", HttpStatus.FORBIDDEN);
		}
		if(password.length() < 6) {
			return new ResponseEntity<>("La contraseña debe contener al menos 6 caracteres", HttpStatus.FORBIDDEN);
		}
		Person newPerson = new Person(null, email, passwordEncoder.encode(password));
		personRepository.save(newPerson);
		return new ResponseEntity<>(newPerson, HttpStatus.CREATED); // Devuelve la nueva Person creada
	}

	// LOGOUT
	@PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> logout(Authentication authentication) {

	if (Util.isGuest(authentication)) {   // Check if guest
	//return new ResponseEntity<>("Error, sesión no iniciada", HttpStatus.OK);
		return new ResponseEntity<>(Util.makeMap("error","¡Sesión no iniciada!"), HttpStatus.BAD_REQUEST);
	}
		//return new ResponseEntity<>("¡Hasta luego!", HttpStatus.OK);
		return new ResponseEntity<>(Util.makeMap("ok","¡Hasta luego!"), HttpStatus.OK);
	}

	
	// OBTAIN LOGGED IN USER
	@GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll(Authentication authentication) {
		Person guest = new Person();
		guest.setEmail("Invitado");
	if (Util.isGuest(authentication)) {   // Check if guest
			return ResponseEntity.ok(guest);
		}
		return ResponseEntity.ok(personRepository.findByEmail(authentication.getName()));

		  }
	
	
	
	@GetMapping(value = "/person/{idPerson}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> findByPerson(@PathVariable String idPerson) {

		// Instancio un nuevo objeto producto
		Person person = new Person();

		// Llamo al servicio creado y le paso por parámetro el idProducto
		person = personService.IdPerson(idPerson);

		// Si el servicio me devolvio un resultado exitoso (200)
		// devuelvo al FrontEnd todos los datos del producto solicitado
		return ResponseEntity.ok(person);
		// return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}


	@PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {

		// llamo al servicio creado y le paso los nuevos datos
		// NO TENGO QUE MODIFICAR EL ID, tiene que ser el mismo
		Person personData = personService.save(person);

		// Si el servicio me devolvio un resultado exitoso o 200
		// devuelvo al FrontEnd todos los datos del Producto actualizado
		return ResponseEntity.ok(personData);

	}

	// Metodo DELETE para eliminar los datos de 1 persona por su ID
	@DeleteMapping(value = "/person/{idPerson}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> deleteFindByIdPerson(@PathVariable String idPerson) {

		// Instancio un nuevo objeto Person
		Person person = new Person();

		// llamor al servicio creado y le paso por parametro el idPerson
		person = personService.deleteById(idPerson);

		// Si el servicio me devolvio un resultado exitoso o 200
		// Elimino todos los datos de la Persona solicitada
		return ResponseEntity.ok(person);
	}
}

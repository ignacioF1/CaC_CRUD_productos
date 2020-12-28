package com.ignacio.springboot.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ignacio.springboot.app.models.Login;
import com.ignacio.springboot.app.repository.LoginRepository;
import com.ignacio.springboot.app.services.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

	// Inyecto el Repository
	// para usar sus funciones e implementar
	// en la lógica de los servicios
	@Autowired
	private LoginRepository loginRepository;
	// Con este contrato o método de la capa actual
	// creo la lógica para poder hacer la consulta con mi base de datos Mongo
	@Override
	public Login IdLogin(String idLogin) {
		// Instancio un nuevo objeto producto
		Login login = null;

		// Envolvemos en una validación
		// para que en caso de que falle por algún motivo esa consulta
		// me imprima en la consola en que parte falló mi app
		
		try { // validación, en caso de romper avisa

			login = loginRepository.findById(idLogin);

		} catch (Exception e) {
			
			e.printStackTrace();

		}
		// En caso de éxito devuelvo todos los datos que contiene Producto
		return login;
	}

	
	
	@Override
	public Login save(Login entity) {
		// Instancio un nuevo objeto producto
		Login login = null;
		
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app
		try {

			login = loginRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// En caso de exito devuelvo todos los datos que contiene Producto
		return login;
	}

	@Override
	public List<Login> findAll() {
		// Instancio una Lista de Productos 
		List<Login> lstLogins = new ArrayList<Login>();
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app

		try {
			lstLogins = loginRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// En caso de exito devuelvo Lista de todos Los Productos 
		return lstLogins;
	}

	@Override
	public Login update(Login entity) {
		// Instancio un nuevo objeto producto
		Login login = null;
		
		
		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app

		try {

			login = loginRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// En caso de exito devuelvo todos los datos que contiene Producto
		return login;
	}

	@Override
	public Login deleteById(String idLogin) {
		// Instancio un nuevo objeto producto
		Login login = null;

		// Envolvemos en una validacion
		// para que en caso falle por algun motivo esa consulta
		// me imprima en la consola en que parte fallo mi app
		try {

			login = loginRepository.deleteById(idLogin);

		} catch (Exception e) {

			e.printStackTrace();
		}

		// En caso de exito devuelvo todos los datos que contiene Producto
		return login;
	}

}


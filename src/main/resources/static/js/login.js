// Login form

// Mensajes

const loginOk = "¡Bienvenido!";
const loginBad = "Usuario o contraseña erróneos";
const logoutOk = "¡Hasta luego!";
const logoutBad = "Error al salir";
const registOk = "¡Registrado correctamente!";
const registBad = "Error al registrarse";

var btnLogin = document.getElementById('login');
var btnLogout = document.getElementById('logout');
var btnRegister = document.getElementById('registrarse');

// LOGIN

btnLogin.addEventListener('click', function(evt) {
	evt.preventDefault();
	var form = evt.target.form;

	var form = btnLogin.form;
	
	if ($(form).hasClass("is-valid")){
	$.post("/api/login",
		{
			email: form["usrname"].value,
			password: form["psw"].value
		})
		.done(function() {
			console.log("login ok");
			$("#response").html(loginOk).show();
		})
		.fail(function() {
			console.log("login bad");
			$("#response").html(loginBad).show();
		})
	$("#loginModal").modal("hide");
	setTimeout(function() {
		$("#response").hide();
		location.reload();
	}, 2000);
} 


});



// LOGOUT

btnLogout.addEventListener('click', function(evt) {
	evt.preventDefault();
	$.post("/api/logout")
		.done(function() {
			console.log("logout ok");
			$("#response").html(logoutOk).show();
		})
		.fail(function() {
			console.log("logout bad");
			$("#response").html(logoutBad).show();
		})
	$("#loginModal").modal("hide");
	setTimeout(function() {
		$("#response").hide();
		location.reload();
	}, 2000);
});

// REGISTER (Registración de un usuario nuevo)

btnRegister.addEventListener('click', function(evt) {
	evt.preventDefault();
	var form = btnLogin.form; // Datos de la form del botón Login
	$.post("/register",
		{
			email: form["usrname"].value,
			password: form["psw"].value
		})
		.done(function() {
			console.log("registration ok");
			$("#response").html(registOk).show();
			
			$.post("/api/login", // If registration ok, log in automatically
		{
			email: form["usrname"].value,
			password: form["psw"].value
		})
		
		})
		.fail(function() {
			console.log("registration bad");
			$("#response").html(registBad).show();
		})
	$("#loginModal").modal("hide");
	setTimeout(function() {
		$("#response").hide();
		location.reload();
	}, 2000);
});


// Obtiene el usuario actual

document.addEventListener('DOMContentLoaded', obtenerUser);
	async function obtenerUser() {
    try {
        const resultado = await fetch("/person");
        const usuario = await resultado.json();
        const email = usuario.email;
        $("#currentUser").html("Usuario: " + email);
        if(email == "Invitado"){
        $("#nuevoProducto").hide();
        }
        return usuario;
    } catch (error) {
        console.log(error)
    }
}


// Muestra contraseña al clickear check

function passVisible() {
  var x = document.getElementById("psw");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

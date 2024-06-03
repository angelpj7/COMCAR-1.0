window.addEventListener("DOMContentLoaded", function() {


	function llamada(id, op) {
		fetch('AdministracionServlet?id=' + id + "&op=" + op)
			.then(response => response.json())
			.then(data => pintarFormulario(data))

	}
	/**
* Funci�n para otener el valor de un parametro en el GET 
*/
	function getParameterByName(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
			results = regex.exec(location.search);
		return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}



	function pintarFormulario(datos) {
		document.getElementById("id").value = datos.id;
		document.getElementById("nombre").value = datos.nombre;
		document.getElementById("apellidos").value = datos.apellidos;
		document.getElementById("email").value = datos.email;
		document.getElementById("tel").value = datos.telefono;
		document.getElementById("dni").value = datos.dni;
		document.getElementById("direccion").value = datos.direccion;
		document.getElementById("username").value = datos.usuario;
		document.getElementById("pass").value = "pordefecto"
		document.getElementById("pass").disabled = true;
		document.getElementById("pass").type = "password";


		//admin control 
		document.getElementById("labelAdmin").style.display = "true";
		var mySelect = document.getElementById('admin');
		for (var i, j = 0; i = mySelect.options[j]; j++) {
			if (i.value == datos.admin) {
				mySelect.selectedIndex = j;
				break;
			}
		}

	}


	window.onload = function() {
		let id = getParameterByName("id");
		let op = getParameterByName("op");
		if (op == 1) {
			llamada(id, op);
		}


	}

	let boton = document.getElementById("boton");
	boton.addEventListener("click", function() {
		validarFormulario();
	});


})


function validarFormulario() {
	let nombre = document.getElementById('nombre').value;
	let apellidos = document.getElementById('apellidos').value;
	let dni = document.getElementById('dni').value;
	let email = document.getElementById('email').value;
	let direccion = document.getElementById('direccion').value;
	let tel = document.getElementById('tel').value;
	let username = document.getElementById('username').value;
	let pass = document.getElementById('pass').value;

	let ok = true;

	if (nombre === "") {
		ok = false;
		document.getElementById('nombre').style.background = "red";
	}

	if (apellidos === "") {
		ok = false;
		document.getElementById('apellidos').style.background = "red";
	}

	if (dni === "") {
		ok = false;
		document.getElementById('dni').style.background = "red";
	}

	if (direccion === "") {
		ok = false;
		document.getElementById('direccion').style.background = "red";
	}

	if (email === "") {
		ok = false;
		document.getElementById('email').style.background = "red";
	}

	if (tel === "") {
		ok = false;
		document.getElementById('tel').style.background = "red";
	}

	if (username === "") {
		ok = false;
		document.getElementById('username').style.background = "red";
	}

	if (pass === "") {
		ok = false;
		document.getElementById('pass').style.background = "red";
	}

	if (ok === true) {
		document.getElementById("altas").submit();
	}
}

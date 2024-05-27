window.addEventListener("DOMContentLoaded", function () {
	
	function mostrarAnuncios(){
			fetch('HomeServlet?ad=1')
			.then(response => response.json())
			.then(data => pintarAnunciosTabla(data))
			
		}
		
	    function consultarLogin(){
			fetch('HomeServlet?session=1')
			.then(response => response.json())
			.then(data => mostrarBotones(data))
		}
	    
		function pintarAnunciosTabla(datos){
			
			let html = "<table class='Tabla'>";
				
			html +="<tr><th>Titulo</th>";
			html +="<th>Descripcion</th>";
			html += "<th>Precio</th>";
			html += "<th>Matrícula</th>";	
			html += "<th>Marca</th>";
			html += "<th>Modelo</th>";
			html += "<th>Año</th>";
			html += "<th>Kilómetros</th>";
			html += "<th>Combustible</th>";
			html += "<th>Color</th>";
			html += "<th>Propietario</th>";
			html +="</tr>";
			
			for(let i=0;i<datos.length;i++){	
							
					html +="<tr><td>"+datos[i].titulo+"</td>";
					html +="<td>"+datos[i].descripcion+"</td>";
					html += "<td>"+datos[i].precio+"</td>";
					html += "<td>"+datos[i].matricula+"</td>";
					html += "<td>"+datos[i].marca+"</td>";
					html += "<td>"+datos[i].modelo+"</td>";
					html += "<td>"+datos[i].anio+"</td>";
					html += "<td>"+datos[i].kilometros+"</td>";
					html += "<td>"+datos[i].combustible+"</td>";
					html += "<td>"+datos[i].color+"</td>";
					html += "<td>"+datos[i].propietario+"</td>";
					html +="</tr>";
			}
			
			html +="</table>";
			
		
			document.getElementById("listado").innerHTML = html;
			
		}
		
		
		// Función para verificar si el usuario está logueado utilizando variables de sesión
		function estaLogeado(login) {
		    // Verificar si el valor de la variable de sesión "usuario" está presente
		    if (login > 0) {
		        // El usuario está logueado, devuelve true
		        return true;
		    } else {
		        // El usuario no está logueado, devuelve false
		        return false;
		    }
		}
		
		// Función para mostrar u ocultar los botones de registrarse e iniciar sesión
		function mostrarBotones(session) {
			
			var btnProfile = document.getElementById('btnProfile');
		    var btnRegistrarse = document.getElementById('btnSignUp');
		    var btnIniciarSesion = document.getElementById('btnLogin');
		    var btnLogout = document.getElementById('btnLogout');
		    var btnAdmin = document.getElementById('btnAdmin');
			var username = session.username;
			
		    // Verificar si el usuario está logeado
		    if (estaLogeado(session.permiso)) {
                btnProfile.querySelector("button").textContent = session.username
		    	btnRegistrarse.style.display = "none"
		        btnIniciarSesion.style.display = "none"
		       	btnProfile.style.display = "inline"
				btnLogout.style.display = "inline"
				
				if (session.permiso == 2) {
			       	btnAdmin.style.display = "inline"
				}
				else{
					btnAdmin.style.display = "none"
				}
		    } else {
		        // Usuario no logeado, mostrar los botones
		        btnProfile.style.display = "none"
		    	btnRegistrarse.style.display = "inline"
			    btnIniciarSesion.style.display = "inline"
			   	btnLogout.style.display = "none"
			   	btnAdmin.style.display = "none"
		    }
		}

		
	    window.onload = function() {
	    	consultarLogin();
	    	mostrarAnuncios();
	    }
	    
	    document.getElementById("btnLogout").addEventListener("click", function() {
	        cerrarSesion();
	    });

	    function cerrarSesion() {
	    	
	    	
	        var xhr = new XMLHttpRequest();
	        xhr.open("POST", "Logout", true); 
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === XMLHttpRequest.DONE) {
	                if (xhr.status === 200) {
	                    // La sesión se cerró correctamente
	                    window.location.href = "Home.html"; // Redirige a la página de inicio de sesión
	                } else {
	                    // Ocurrió un error al cerrar la sesión
	                    console.error("Error al cerrar la sesión:", xhr.responseText);
	                }
	            }
	        };
	        xhr.send();
	    }
	    
	

	
})
 
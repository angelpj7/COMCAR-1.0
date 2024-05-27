document.addEventListener('DOMContentLoaded', function() {
    consultarLogin();
    mostrarUsuarios();
    document.getElementById("btnLogout").addEventListener("click", cerrarSesion);
});

function mostrarAnuncios() {
    fetch('HomeServlet?ad=1')
        .then(response => response.json())
        .then(data => pintarAnunciosTabla(data));
}

function consultarLogin() {
    fetch('HomeServlet?session=1')
        .then(response => response.json())
        .then(data => mostrarBotones(data));
}

function mostrarUsuarios() {
    fetch('AdministracionServlet?type=1')
        .then(response => response.json())
        .then(data => pintarUsuariosTabla(data));
}

function pintarUsuariosTabla(datos) {
    console.log(datos);
    let html = "<table class='Tabla'>";
    html += "<tr><th>ID</th>";
    html += "<th>Nombre</th>";
    html += "<th>Apellidos</th>";
    html += "<th>DNI</th>";
    html += "<th>Email</th>";
    html += "<th>Telefono</th>";
    html += "<th>Direccion</th>";
    html += "<th>Usuario</th>";
    html += "<th>Admin</th>";
    html += "</tr>";

    for (let i = 0; i < datos.length; i++) {
        if (datos[i].usuario !== "admin") {
            html += "<tr><td>" + datos[i].id + "</td>";
            html += "<td>" + datos[i].nombre + "</td>";
            html += "<td>" + datos[i].apellidos + "</td>";
            html += "<td>" + datos[i].dni + "</td>";
            html += "<td>" + datos[i].email + "</td>";
            html += "<td>" + datos[i].telefono + "</td>";
            html += "<td>" + datos[i].direccion + "</td>";
            html += "<td>" + datos[i].usuario + "</td>";
            html += "<td>" + datos[i].admin + "</td>";
            html += "<td><a href='AltaUsuario.html?id=" + datos[i].id + "&op=1' class='edit-link'>Editar</a></td>";
            html += "<td><a href='#' data-id='" + datos[i].id + "' class='borrar-link'>Borrar</a></td>";
            html += "</tr>";
        }
    }

    html += "</table>";
    document.getElementById("listado").innerHTML = html;

    document.querySelectorAll('.borrar-link').forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const id = this.getAttribute('data-id');
            borrar(id);
        });
    });
}

function borrar(id) {
    if (confirm("¿Seguro que quieres borrar el usuario y sus anuncios asociados?")) {
        fetch('AdministracionServlet?id=' + id + '&op=2')
            .then(response => response.json())
            .then(data => pintarUsuariosTabla(data));
    }
}

function estaLogeado(login) {
    return login > 0;
}

function mostrarBotones(session) {
    var btnRegistrarse = document.getElementById('btnSignUp');
    var btnIniciarSesion = document.getElementById('btnLogin');
    var btnProfile = document.getElementById('btnProfile');
    var btnLogout = document.getElementById('btnLogout');
    var btnAdmin = document.getElementById('btnAdmin');
    var slogan = document.getElementById('slogan');
    

    if (estaLogeado(session.permiso)) {
        btnRegistrarse.style.display = "none";
        btnIniciarSesion.style.display = "none";
        btnProfile.querySelector("button").textContent = session.username;
        btnProfile.style.display = "inline";
        btnLogout.style.display = "inline";

        if (session.permiso == 2) {
            btnAdmin.style.display = "inline";
            slogan.style.display = "none";
            
        } else {
            btnAdmin.style.display = "none";
        }
    } else {
        btnRegistrarse.style.display = "inline";
        btnIniciarSesion.style.display = "inline";
        btnProfile.style.display = "none";
        btnLogout.style.display = "none";
        btnAdmin.style.display = "none";
    }
}

function cerrarSesion() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "Logout", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                window.location.href = "Home.html";
            } else {
                console.error("Error al cerrar la sesión:", xhr.responseText);
            }
        }
    };
    xhr.send();
}
 
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AltaUsuarioServlet
 */
public class AltaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String nombre =  request.getParameter("nombre");
		String apellidos =  request.getParameter("apellidos");
		String dni =  request.getParameter("dni");
		String email =  request.getParameter("email");
		int telefono =  Integer.parseInt(request.getParameter("tel"));
		String direccion =  request.getParameter("direccion");
		String username =  request.getParameter("usuario");
		String password =  request.getParameter("password");
		String id = request.getParameter("id");
		Boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
				
		if (id == null || id == "") {
			Usuario newUsuario = new Usuario(nombre,apellidos,dni,email,telefono,direccion,username);
			
			
			try {
				newUsuario.insertar(password);
				response.sendRedirect("Home.html");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				response.sendRedirect("AltaUsuario.html");
			}
		}
		else {
			int idUsuario = Integer.parseInt(id);
			try {
				Usuario usuario = new Usuario(idUsuario,nombre,apellidos,dni,email,telefono,direccion,username,admin);
				usuario.actualizarUsuario();
				response.sendRedirect("PortalAdministracion.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("AltaUsuario.html?id="+idUsuario+"&op=1");
			}
		}
	}

}
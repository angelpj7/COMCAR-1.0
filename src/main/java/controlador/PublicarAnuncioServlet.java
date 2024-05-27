package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Anuncio;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class PublicarAnuncioServlet
 */
public class PublicarAnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicarAnuncioServlet() {
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
				String titulo =  request.getParameter("titulo");
				String descripcion =  request.getParameter("descripcion");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String matricula =  request.getParameter("matricula");
				String marca =  request.getParameter("marca");
				String modelo =  request.getParameter("modelo");
				int anio =  Integer.parseInt(request.getParameter("anio"));
				int km =  Integer.parseInt(request.getParameter("km"));
				String combustible =  request.getParameter("combustible");
				String color =  request.getParameter("color");

				HttpSession sesion = request.getSession(); 
				
				if (sesion.getAttribute("permiso") != null) {
					int permiso = (int) sesion.getAttribute("permiso");
					
					if ( permiso > 0) {
						try {
							
							int idUsuario = (int) sesion.getAttribute("idUsuario");
							Usuario currentUser = new Usuario(idUsuario);
							currentUser.crearAnuncio(titulo, descripcion,precio, matricula, marca, modelo, anio, km, combustible, color);
							// response.sendRedirect("AnuncioPublicado.html");
							response.sendRedirect("Home.html");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
							// html mostrar registro erróneo
							// o
							response.sendRedirect("PublicarAnuncio.html");
						}
					}
					else {
						response.sendRedirect("Login.html");
					}
				}
				else {
					response.sendRedirect("Login.html");
				}

			}
}

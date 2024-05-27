package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private int compruebaLogeo(String nombreInsertado, String passInsertado) {
    	int idUsuario = -1;
    	
    	/*
    	 * Simulamos con variables el nombre y la contraseña.
    	 * En un caso real esto sería un método de la clase DAO
    	 * donde consultariamos us el usuario existe y esta en la base de datos.
    	 * */
    	
    	
    	Usuario usuarioLogin = new Usuario(nombreInsertado);
    	
		try {
			idUsuario = usuarioLogin.comprobarUsuario(passInsertado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	   	
    	return idUsuario;
    }
    
    private int comprobarPermiso(int idUsuario) {
    	int permiso = -1; 
    	
    	Usuario usuarioLogin = new Usuario(idUsuario);
    	
		try {
			if (usuarioLogin.comprobarAdmin()) {
				permiso = 2; // permiso administrador
			}
			else {
				permiso = 1; // permiso normal
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	   	
    	return permiso;
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
		

	    	PrintWriter respuesta = response.getWriter(); // para poder enviar la respuesta por http
	        HttpSession sesion = request.getSession();  // abro la sesión para poderla utilizar.
	
	        String nombreInsertado = request.getParameter("usuario");
			String passInsertado = request.getParameter("password");
			
			int idUsuario = this.compruebaLogeo(nombreInsertado, passInsertado);
			
			if(idUsuario >= 0) {
		        sesion.setAttribute("username", nombreInsertado);
		        sesion.setAttribute("idUsuario", idUsuario);
		        
		        int permiso = this.comprobarPermiso(idUsuario);
		        sesion.setAttribute("permiso", permiso);
			    respuesta.print("Usuario logeado: " + sesion.getAttribute("nombre")+ " con user id: "+ sesion.getAttribute("nombre"));
			    response.sendRedirect("Home.html");
			}else {
			    respuesta.print("Usuario no logeado: " + sesion.getAttribute("nombre"));
			    response.sendRedirect("Login.html");
	
			}
		
	}

}
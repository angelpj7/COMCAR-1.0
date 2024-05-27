package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import modelo.Anuncio;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoAnuncio;


/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
	
		if (request.getParameter("ad") != null) {
			
			DaoAnuncio anuncios;
			try {
				anuncios = new DaoAnuncio();
				out.print(anuncios.listarJson());
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("session") != null) {

			HttpSession session = request.getSession();
			int permiso = 0;
			String username = "";
			int idUsuario = 0;
			
			if (session.getAttribute("permiso") != null){
				permiso = (int) session.getAttribute("permiso");
				username = (String) session.getAttribute("username");
				idUsuario = (int) session.getAttribute("idUsuario");
			}
			out.write("{\"permiso\": \"" + permiso + "\","
					+ "\"username\": \"" + username + "\","
					+ "\"userId\":  \"" + idUsuario + "\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
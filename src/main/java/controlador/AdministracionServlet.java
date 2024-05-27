package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Anuncio;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoAnuncio;
import dao.DaoUsuario;
import dao.DaoCoche;
/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministracionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
				
			
		if (request.getParameter("type") != null) {
					
		
			if ( Integer.parseInt(request.getParameter("type")) == 1 ) {
				DaoUsuario usuarios;
				try {
					usuarios = new DaoUsuario();
					out.print(usuarios.listarJson());
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (request.getParameter("op") != null) {
			
			if ( Integer.parseInt(request.getParameter("op")) == 2) {
							
				try {
					DaoUsuario daoUsuarios =  new DaoUsuario();
					DaoAnuncio daoAnuncios = new DaoAnuncio();
					
					int idUsuario = Integer.parseInt(request.getParameter("id"));
					
					
					ArrayList<Anuncio> listaAnuncios = daoAnuncios.getAnunciosByIdUsuario(idUsuario);

					for (int i = 0; i < listaAnuncios.size() ; i++) {
						listaAnuncios.get(i).eliminarAnuncio();
					}	
					
					daoUsuarios.eliminar(idUsuario);

					out.print(daoUsuarios.listarJson());
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(Integer.parseInt(request.getParameter("op")) == 1) {
				//proceso logica edicion
				
				try {
					int idUsuario = Integer.parseInt(request.getParameter("id"));
					Usuario usuario = new Usuario(idUsuario);	
					out.print(usuario.getUsuarioJson());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
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

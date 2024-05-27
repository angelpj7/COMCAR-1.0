package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.google.gson.Gson;

import modelo.Anuncio;
import modelo.Usuario;

/**
 * DaoAnuncio se encarga de manejar las operaciones CRUD relacionadas con
 * objetos Anuncio en la base de datos.
 */
public class DaoAnuncio {

	public static Connection con = null;

	/**
	 * Constructor para DaoAnuncio. Inicializa la conexión a la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public DaoAnuncio() throws SQLException {
		try {
			// Intenta cargar el controlador JDBC de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DBConexion.getConexion();
			// System.out.println("¡El controlador JDBC de MySQL se ha cargado
			// correctamente!");
		} catch (ClassNotFoundException e) {
			// Captura y muestra cualquier excepción que ocurra al cargar el controlador
			System.out.println("Error al cargar el controlador JDBC de MySQL: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Inserta un nuevo objeto Anuncio en la base de datos.
	 * 
	 * @param a el objeto Anuncio que se va a insertar.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void insertar(Anuncio a) throws SQLException {

		String sql = "INSERT INTO anuncios (titulo,descripcion,precio,idCoche,idUsuario) VALUES (?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getTitulo());
		ps.setString(2, a.getDescripcion());
		ps.setInt(3, a.getPrecio());
		ps.setInt(4, a.getIdCoche());
		ps.setInt(5, a.getIdUsuario());
		ps.executeUpdate();
		ps.close();

	}

	/**
	 * Recupera una lista de objetos Anuncio como una cadena JSON. El resultado
	 * incluye información combinada de las tablas 'coches' y 'usuarios'.
	 * 
	 * @return una cadena JSON que representa una lista de objetos Anuncio.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public String listarJson() throws SQLException {

		String sql = """
				select
					a.titulo,
				    a.descripcion,
				    a.fecha,
				    a.precio,
				    b.matricula,
				    b.marca,
				    b.modelo,
				    b.anio,
				    b.kilometros,
				    b.combustible,
				    b.color,
				    c.nombre,
				    c.apellidos,
				    c.telefono
				from
					comcar.anuncios a
				left join comcar.coches b
				on
					a.idCoche = b.idCoche
				left join comcar.usuarios c
				on
					a.idUsuario = c.idUsuario;

				""";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		String json = "";

		// Creo el json a mano porque como tengo dos clases (anuncio y coche) y no las
		// muestro directamente si no combinadas, para poder mostrar todos los datos que
		// he solicitado en la query ya que al no pertenecer a un objeto completo
		// (anuncio) no puedo usar gson para serializarlo
		while (rs.next()) {
			if (json != "") {
				json += ",";
			}
			String array = "{\"titulo\" : \"" + rs.getString(1) + "\", \"descripcion\" : \"" + rs.getString(2)
					+ "\", \"precio\" : \"" + rs.getString(4) + "\", \"matricula\" : \"" + rs.getString(5)
					+ "\", \"marca\" : \"" + rs.getString(6) + "\",  \"modelo\" : \"" + rs.getString(7)
					+ "\",  \"anio\" : \"" + rs.getString(8) + "\",  \"kilometros\" : \"" + rs.getString(9)
					+ "\",  \"combustible\" : \"" + rs.getString(10) + "\",  \"color\" : \"" + rs.getString(11)
					+ "\", \"propietario\" : \"" + rs.getString(12) + " " + rs.getString(13) + "\"}";
			json += array;
		}

		if (json != "") {
			json = "[" + json + "]";
		}

		return json;

	}

	/**
	 * Recupera una lista de objetos Anuncio por el ID del usuario dado.
	 * 
	 * @param idUsuario el ID del usuario.
	 * @return un ArrayList de objetos Anuncio.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public ArrayList<Anuncio> getAnunciosByIdUsuario(int idUsuario) throws SQLException {
		String sql = "select idAnuncio,titulo,descripcion,precio,idUsuario,idCoche from Anuncios where idUsuario = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);

		ResultSet rs = ps.executeQuery();

		ArrayList<Anuncio> ls = null;

		while (rs.next()) {
			if (ls == null) {
				ls = new ArrayList<Anuncio>();
			}

			ls.add(new Anuncio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getInt(6)));
		}
		return ls;

	}

	/**
	 * Elimina un objeto Anuncio de la base de datos por el ID del Anuncio dado.
	 * 
	 * @param idAnuncio el ID del Anuncio.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void eliminar(int idAnuncio) throws SQLException {
		String sql = "Delete from anuncios where idAnuncio = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idAnuncio);
		ps.executeUpdate();
		ps.close();
	}

}
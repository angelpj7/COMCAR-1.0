package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Coche;

/**
 * DaoCoche se encarga de manejar las operaciones CRUD relacionadas con objetos
 * Coche en la base de datos.
 */
public class DaoCoche {

	public static Connection con = null;

	/**
	 * Constructor para DaoCoche. Inicializa la conexión a la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public DaoCoche() throws SQLException {

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
	 * Inserta un nuevo objeto Coche en la base de datos.
	 * 
	 * @param c el objeto Coche que se va a insertar.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void insertar(Coche c) throws SQLException {

		String sql = "INSERT INTO coches (matricula,marca,modelo,anio,kilometros,combustible,color) VALUES (?,?,?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getMatricula());
		ps.setString(2, c.getMarca());
		ps.setString(3, c.getModelo());
		ps.setInt(4, c.getAnio());
		ps.setInt(5, c.getKilometros());
		ps.setString(6, c.getCombustible());
		ps.setString(7, c.getColor());
		ps.executeUpdate();
		ps.close();

	}

	/**
	 * Recupera el ID de un coche basado en su matrícula.
	 * 
	 * @param matricula la matrícula del coche.
	 * @return el ID del coche, o -1 si no se encuentra.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public int getId(String matricula) throws SQLException {
		int idCoche = -1;
		String sql = "Select idCoche from coches where matricula = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, matricula);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			idCoche = rs.getInt("idCoche");
		}
		ps.close();
		rs.close();
		return idCoche;
	}

	/**
	 * Elimina un objeto Coche de la base de datos por el ID del coche dado.
	 * 
	 * @param idCoche el ID del coche.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void eliminar(int idCoche) throws SQLException {
		String sql = "Delete from Coches where idCoche = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idCoche);
		ps.executeUpdate();
		ps.close();
	}

}
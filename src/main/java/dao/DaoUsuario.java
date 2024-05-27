package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Usuario;

/**
 * DaoUsuario se encarga de manejar las operaciones CRUD relacionadas con
 * objetos Usuario en la base de datos.
 */
public class DaoUsuario {

	public static Connection con = null;

	/**
	 * Constructor para DaoUsuario. Inicializa la conexión a la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public DaoUsuario() throws SQLException {

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
	 * Convierte una cadena en su hash MD5, en este caso se utiliza para la password
	 * 
	 * @param input la cadena que se va a convertir.
	 * @return la cadena convertida en MD5.
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Inserta un nuevo objeto Usuario en la base de datos.
	 * 
	 * @param u        el objeto Usuario que se va a insertar.
	 * @param password la contraseña del usuario que se va a insertar.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void insertar(Usuario u, String password) throws SQLException {

		String sql = "INSERT INTO usuarios (nombre,apellidos,dni,telefono,direccion,usuario,password,email) VALUES (?,?,?,?,?,?,?,?) "; 
																																		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getApellidos());
		ps.setString(3, u.getDni());
		ps.setInt(4, u.getTelefono());
		ps.setString(5, u.getDireccion());
		ps.setString(6, u.getUsuario());
		ps.setString(7, getMD5(password));
		ps.setString(8, u.getEmail());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Comprueba las credenciales de un usuario.
	 * 
	 * @param usuario  el nombre de usuario.
	 * @param password la contraseña del usuario.
	 * @return el ID del usuario si las credenciales son correctas, -1 si no lo son.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public int comprobarUsuario(String usuario, String password) throws SQLException {
		int idUsuario = -1;
		String sql = "Select idUsuario from usuarios where usuario = ? and password = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usuario);
		ps.setString(2, getMD5(password));

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			idUsuario = rs.getInt("idUsuario");
		}
		ps.close();
		rs.close();

		return idUsuario;
	}

	/**
	 * Comprueba si un usuario es administrador.
	 * 
	 * @param idUsuario el ID del usuario.
	 * @return true si el usuario es administrador, false si no lo es.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public boolean comprobarAdmin(int idUsuario) throws SQLException {
		boolean admin = false;
		String sql = "Select admin from usuarios where idUsuario = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			admin = rs.getBoolean("admin");
		}
		ps.close();
		rs.close();

		return admin;
	}

	/**
	 * Recupera una lista de todos los usuarios en la base de datos.
	 * 
	 * @return un ArrayList de objetos Usuario.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public ArrayList<Usuario> listar() throws SQLException {

		String sql = "SELECT idUsuario,nombre,apellidos,dni,email,telefono,direccion,usuario,admin FROM usuarios";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<Usuario> ls = null;

		while (rs.next()) {
			if (ls == null) {
				ls = new ArrayList<Usuario>();
			}

			ls.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
		}
		return ls;

	}

	/**
	 * Recupera una lista de todos los usuarios en la base de datos como una cadena
	 * JSON.
	 * 
	 * @return una cadena JSON que representa una lista de objetos Usuario.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public String listarJson() throws SQLException {

		String json = "";
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}

	/**
	 * Elimina un objeto Usuario de la base de datos por el ID del usuario dado.
	 * 
	 * @param idUsuario el ID del usuario.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void eliminar(int idUsuario) throws SQLException {
		String sql = "Delete from usuarios where idUsuario = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Recupera un objeto Usuario por su ID y lo devuelve como una cadena JSON.
	 * 
	 * @param idUsuario el ID del usuario.
	 * @return una cadena JSON que representa el objeto Usuario.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public String getUsuarioJsonById(int idUsuario) throws SQLException {
		String sql = "SELECT idUsuario,nombre,apellidos,dni,email,telefono,direccion,usuario,admin FROM usuarios where idUsuario = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();

		String json = "";
		Gson gson = new Gson();

		while (rs.next()) {
			Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
			json = gson.toJson(usuario);
		}
		ps.close();
		rs.close();
		return json;
	}

	/**
	 * Actualiza los datos de un usuario en la base de datos.
	 * 
	 * @param u el objeto Usuario con los datos actualizados.
	 * @throws SQLException si ocurre un error al acceder a la base de datos.
	 */
	public void actualizar(Usuario u) throws SQLException {
		String sql = "UPDATE usuarios SET nombre=?,apellidos=?,dni=?,direccion=?,email=?,telefono=?,admin=?,usuario=? WHERE idUsuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getApellidos());
		ps.setString(3, u.getDni());
		ps.setString(4, u.getDireccion());
		ps.setString(5, u.getEmail());
		ps.setInt(6, u.getTelefono());
		ps.setBoolean(7, u.getAdmin());
		ps.setString(8, u.getUsuario());
		ps.setInt(9, u.getId());
		ps.executeUpdate();
		ps.close();

	}
}
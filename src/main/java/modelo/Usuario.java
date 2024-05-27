
package modelo;

import java.util.Objects;

import dao.DaoUsuario;

import java.util.ArrayList;
import java.sql.SQLException;

/**
 * Clase que representa un usuario con sus atributos
 */
public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private int telefono;
	private String direccion;
	private String usuario;
	private String password;
	private boolean admin;

	/**
	 * Constructor por defecto.
	 */
	public Usuario() {
	}

	/**
	 * Constructor que inicializa un usuario con los valores especificados.
	 *
	 * @param nombre    Nombre del usuario.
	 * @param apellidos Apellidos del usuario.
	 * @param dni       DNI del usuario.
	 * @param email     Email del usuario.
	 * @param telefono  Teléfono del usuario.
	 * @param direccion Dirección del usuario.
	 * @param usuario   Nombre de usuario.
	 * @param admin     establece que el usario no es administrador por defecto.
	 * @param id        inicializa el id
	 */
	public Usuario(String nombre, String apellidos, String dni, String email, int telefono, String direccion,
			String usuario) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.admin = false;
		this.id = -1;

	}

	/**
	 * Constructor que inicializa un usuario con los valores especificados y un
	 * identificador.
	 *
	 * @param userId    Identificador único del usuario.
	 * @param nombre    Nombre del usuario.
	 * @param apellidos Apellidos del usuario.
	 * @param dni       DNI del usuario.
	 * @param email     Email del usuario.
	 * @param telefono  Teléfono del usuario.
	 * @param direccion Dirección del usuario.
	 * @param usuario   Nombre de usuario.
	 * @param admin     Indica si el usuario es administrador.
	 */
	public Usuario(int userId, String nombre, String apellidos, String dni, String email, int telefono,
			String direccion, String usuario, boolean admin) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.admin = admin;
		this.id = userId;

	}

	/**
	 * Constructor que inicializa un usuario con un nombre de usuario.
	 *
	 * @param username Nombre de usuario.
	 */
	public Usuario(String username) {
		this.usuario = username;
	}

	/**
	 * Constructor que inicializa un usuario con un identificador.
	 *
	 * @param idUsuario Identificador único del usuario.
	 */
	public Usuario(int idUsuario) {
		this.id = idUsuario;
	}

	/**
	 * Obtiene el identificador del usuario.
	 *
	 * @return El identificador del usuario.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del usuario.
	 *
	 * @param id El nuevo identificador del usuario.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del usuario.
	 *
	 * @return El nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param nombre El nuevo nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene los apellidos del usuario.
	 *
	 * @return Los apellidos del usuario.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del usuario.
	 *
	 * @param apellidos Los nuevos apellidos del usuario.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene el DNI del usuario.
	 *
	 * @return El DNI del usuario.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del usuario.
	 *
	 * @param dni El nuevo DNI del usuario.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el email del usuario.
	 *
	 * @return El email del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email del usuario.
	 *
	 * @param email El nuevo email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene el teléfono del usuario.
	 *
	 * @return El teléfono del usuario.
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono del usuario.
	 *
	 * @param telefono El nuevo teléfono del usuario.
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtiene la dirección del usuario.
	 *
	 * @return La dirección del usuario.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección del usuario.
	 *
	 * @param direccion La nueva dirección del usuario.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene el nombre de usuario.
	 *
	 * @return El nombre de usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre de usuario.
	 *
	 * @param usuario El nuevo nombre de usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Establece si el usuario es administrador.
	 *
	 * @param admin El valor que indica si el usuario es administrador.
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Obtiene si el usuario es administrador.
	 *
	 * @return true si el usuario es administrador, false en caso contrario.
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Inserta el usuario en la base de datos.
	 *
	 * @param password La contraseña del usuario.
	 * @throws SQLException Si ocurre un error al insertar el usuario en la base de
	 *                      datos.
	 */
	public void insertar(String password) throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this, password);
	}

	/**
	 * Crea un anuncio asociado a este usuario y lo inserta en la base de datos.
	 *
	 * @param titulo      Título del anuncio.
	 * @param descripcion Descripción del anuncio.
	 * @param precio      Precio del anuncio.
	 * @param matricula   Matrícula del coche asociado al anuncio.
	 * @param marca       Marca del coche asociado al anuncio.
	 * @param modelo      Modelo del coche asociado al anuncio.
	 * @param anio        Año del coche asociado al anuncio.
	 * @param kilometros  Kilómetros del coche asociado al anuncio.
	 * @param combustible Combustible del coche asociado al anuncio.
	 * @param color       Color del coche asociado al anuncio.
	 * @throws SQLException Si ocurre un error al insertar el anuncio en la base de
	 *                      datos.
	 */
	public void crearAnuncio(String titulo, String descripcion, int precio, String matricula, String marca,
			String modelo, int anio, int kilometros, String combustible, String color) throws SQLException {

		Anuncio newAnuncio = new Anuncio(titulo, descripcion, this.id, precio);
		newAnuncio.crearCoche(matricula, marca, modelo, anio, kilometros, combustible, color);
		newAnuncio.insertar();

	}

	/**
	 * Comprueba las credenciales del usuario y devuelve su identificador si son
	 * correctas.
	 *
	 * @param password La contraseña del usuario.
	 * @return El identificador del usuario si las credenciales son correctas.
	 * @throws SQLException Si ocurre un error al comprobar las credenciales.
	 */
	public int comprobarUsuario(String password) throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		int idUsuario = dao.comprobarUsuario(this.usuario, password);
		return idUsuario;

	}

	/**
	 * Comprueba si el usuario es administrador.
	 *
	 * @return true si el usuario es administrador, false en caso contrario.
	 * @throws SQLException Si ocurre un error al comprobar el estado de
	 *                      administrador.
	 */
	public boolean comprobarAdmin() throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		boolean admin = dao.comprobarAdmin(this.id);
		return admin;

	}

	/**
	 * Devuelve una cadena que representa al usuario.
	 *
	 * @return Una cadena con los datos del usuario.
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", email="
				+ email + ", telefono=" + telefono + ", direccion=" + direccion + ", usuario=" + usuario + ", password="
				+ password + ", admin=" + admin + "]";
	}

	/**
	 * Devuelve una representación JSON del usuario.
	 *
	 * @return Una cadena JSON que representa al usuario.
	 * @throws SQLException Si ocurre un error al obtener los datos del usuario.
	 */
	public String getUsuarioJson() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		return dao.getUsuarioJsonById(this.id);
	}

	/**
	 * Actualiza la información del usuario en la base de datos.
	 *
	 * @throws SQLException Si ocurre un error al actualizar los datos del usuario.
	 */
	public void actualizarUsuario() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.actualizar(this);
	}
}
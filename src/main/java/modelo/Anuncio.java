/**
 * 
 */
package modelo;

import java.sql.SQLException;

import dao.DaoAnuncio;
import dao.DaoUsuario;

/**
 * La clase Anuncio representa un anuncio de venta de coches.
 */
public class Anuncio {

	private int idUsuario;
	private int idCoche;
	private String titulo;
	private String descripcion;
	private String fecha;
	private int precio;
	private int id;

	/**
	 * Constructor vacío de la clase Anuncio.
	 */
	public Anuncio() {

	}

	/**
	 * Constructor que inicializa un anuncio con los parámetros dados.
	 * 
	 * @param titulo       el título del anuncio
	 * @param descripcion: la descripción del anuncio
	 * @param idUsuario:   el ID del usuario que crea el anuncio
	 * @param precio:      el precio del coche en el anuncio
	 */
	public Anuncio(String titulo, String descripcion, int idUsuario, int precio) {

		this.idUsuario = idUsuario;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.idCoche = -1;
		this.fecha = "";
		this.id = -1;

	}

	/**
	 * Constructor que inicializa un anuncio con todos los parámetros dados.
	 * 
	 * @param idAnuncio   el ID del anuncio
	 * @param titulo      el título del anuncio
	 * @param descripcion la descripción del anuncio
	 * @param precio      el precio del coche en el anuncio
	 * @param idUsuario   el ID del usuario que crea el anuncio
	 * @param idCoche     el ID del coche en el anuncio
	 */
	public Anuncio(int idAnuncio, String titulo, String descripcion, int precio, int idUsuario, int idCoche) {

		this.id = idAnuncio;
		this.idUsuario = idUsuario;
		this.idCoche = idCoche;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fecha = "";

	}

	/**
	 * Obtiene el ID del usuario.
	 * 
	 * @return el ID del usuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Establece el ID del usuario.
	 * 
	 * @param idUsuario el ID del usuario.
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Obtiene el titulo del anuncio.
	 * 
	 * @return el titulo del anuncio.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Obtiene el título del anuncio.
	 * 
	 * @return el título del anuncio
	 */
	public void setTitulo(String titulo) {
		titulo = titulo;
	}

	/**
	 * Obtiene la descripción del anuncio.
	 * 
	 * @return la descripción del anuncio
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Obtiene la descripcion del anuncio.
	 * 
	 * @return la descripcion del anuncio
	 */
	public void setDescripcion(String descripcion) {
		descripcion = descripcion;
	}

	/**
	 * Obtiene el ID del coche.
	 * 
	 * @return el ID del coche
	 */
	public int getIdCoche() {
		return idCoche;
	}

	/**
	 * Establece el ID del coche.
	 * 
	 * @param idCoche el ID del coche
	 */
	public void setIdCoche(int idCoche) {
		idCoche = idCoche;
	}

	/**
	 * Obtiene el ID del anuncio.
	 * 
	 * @return el ID del anuncio
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el ID del anuncio.
	 * 
	 * @param id el ID del anuncio
	 */
	public void setId(int id) {
		id = id;
	}

	/**
	 * Establece el precio del anuncio.
	 * 
	 * @param precio el precio del anuncio
	 */
	public void setPrecio(int precio) {
		precio = precio;
	}

	/**
	 * Obtiene el precio del anuncio.
	 * 
	 * @return el precio del anuncio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * Devuelve una representación en cadena del anuncio.
	 * 
	 * @return una cadena que representa el anuncio
	 */
	@Override
	public String toString() {
		return "Anuncio [idUsuario=" + idUsuario + ", idCoche=" + idCoche + ", titulo=" + titulo + ", descripcion="
				+ descripcion + ", fecha=" + fecha + ", precio=" + precio + ", id=" + id + "]";
	}

	/**
	 * Crea un coche con los parámetros dados y establece el ID del coche en el
	 * anuncio.
	 * 
	 * @param matricula   la matrícula del coche
	 * @param marca       la marca del coche
	 * @param modelo      el modelo del coche
	 * @param anio        el año del coche
	 * @param kilometros  los kilómetros recorridos por el coche
	 * @param combustible el tipo de combustible del coche
	 * @param color       el color del coche
	 * @throws SQLException si ocurre un error al insertar el coche en la base de
	 *                      datos
	 */
	public void crearCoche(String matricula, String marca, String modelo, int anio, int kilometros, String combustible,
			String color) throws SQLException {

		Coche newCoche = new Coche(matricula, marca, modelo, anio, kilometros, combustible, color);
		this.idCoche = newCoche.insertar();

	}

	/**
	 * Inserta el anuncio en la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al insertar el anuncio en la base de
	 *                      datos
	 */
	public void insertar() throws SQLException {

		DaoAnuncio dao = new DaoAnuncio();
		dao.insertar(this);
	}

	/**
	 * Elimina el anuncio de la base de datos y el coche asociado al anuncio.
	 * 
	 * @throws SQLException si ocurre un error al eliminar el anuncio o el coche en
	 *                      la base de datos
	 */
	public void eliminarAnuncio() throws SQLException {
		DaoAnuncio dao = new DaoAnuncio();
		dao.eliminar(this.id);

		Coche coche = new Coche(this.id);
		coche.eliminarCoche();

	}

}
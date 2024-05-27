
package modelo;

import java.sql.SQLException;

import dao.DaoCoche;
import dao.DaoUsuario;

/**
 * Clase que representa un coche con sus atributos
 */
public class Coche {

	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private int anio;
	private int kilometros;
	private String combustible;
	private String color;

	/**
	 * Constructor por defecto.
	 */
	public Coche() {

	}

	/**
	 * Constructor que inicializa un coche con los valores especificados.
	 * 
	 * @param matricula   Matrícula del coche.
	 * @param marca       Marca del coche.
	 * @param modelo      Modelo del coche.
	 * @param anio        Año del coche.
	 * @param kilometros  Kilómetros recorridos por el coche.
	 * @param combustible Tipo de combustible del coche.
	 * @param color       Color del coche.
	 */
	public Coche(String matricula, String marca, String modelo, int anio, int kilometros, String combustible,
			String color) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.kilometros = kilometros;
		this.combustible = combustible;
		this.color = color;
		this.id = -1;
	}

	/**
	 * Constructor que inicializa un coche con un identificador.
	 * 
	 * @param idCoche Identificador único del coche.
	 */
	public Coche(int idCoche) {
		this.id = idCoche;
	}

	/**
	 * Obtiene la matrícula del coche.
	 * 
	 * @return La matrícula del coche.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Establece la matrícula del coche.
	 * 
	 * @param matricula La nueva matrícula del coche.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Obtiene la marca del coche.
	 * 
	 * @return La marca del coche.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Establece la marca del coche.
	 * 
	 * @param marca La nueva marca del coche.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Obtiene el modelo del coche.
	 * 
	 * @return El modelo del coche.
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo del coche.
	 * 
	 * @param modelo El nuevo modelo del coche.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Obtiene el año del coche.
	 * 
	 * @return El año del coche.
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Establece el año del coche.
	 * 
	 * @param anio El nuevo año del coche.
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Obtiene los kilómetros recorridos por el coche.
	 * 
	 * @return Los kilómetros recorridos por el coche.
	 */
	public int getKilometros() {
		return kilometros;
	}

	/**
	 * Establece los kilómetros recorridos por el coche.
	 * 
	 * @param kilometros Los nuevos kilómetros recorridos por el coche.
	 */
	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	/**
	 * Obtiene el tipo de combustible del coche.
	 * 
	 * @return El tipo de combustible del coche.
	 */
	public String getCombustible() {
		return combustible;
	}

	/**
	 * Establece el tipo de combustible del coche.
	 * 
	 * @param combustible El nuevo tipo de combustible del coche.
	 */
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	/**
	 * Obtiene el color del coche.
	 * 
	 * @return El color del coche.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Establece el color del coche.
	 * 
	 * @param color El nuevo color del coche.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Inserta el coche en la base de datos y devuelve el identificador asignado.
	 * 
	 * @return El identificador del coche después de ser insertado en la base de
	 *         datos.
	 * @throws SQLException Si ocurre un error al insertar el coche en la base de
	 *                      datos.
	 */
	public int insertar() throws SQLException {

		DaoCoche dao = new DaoCoche();
		dao.insertar(this);

		int idCoche = dao.getId(this.matricula);

		return idCoche;
	}

	/**
	 * Elimina el coche de la base de datos.
	 * 
	 * @throws SQLException Si ocurre un error al eliminar el coche de la base de
	 *                      datos.
	 */
	public void eliminarCoche() throws SQLException {
		DaoCoche dao = new DaoCoche();
		dao.eliminar(this.id);
	}

}
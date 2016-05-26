package me4l;

import java.util.ArrayList;

/**
 * Clase de tipo Value Object que representa una entidad simple en la BBDD,
 * en este caso, la entidad representada en la tabla "receta" de la BBDD.
 */
public class RecetaVO {

	private String id;
	private String nombre;
	private String descripcion;
	private String plato;
	private String numPersonas;
	private String validada;
	private ArrayList<String> ingredientes;
	private ArrayList<String> pesoIngredientes;
	private ArrayList<String> unidad;

	/**
	 * Constructor por defecto.
	 */
	public RecetaVO () {
		
	}

	/**
	 * Constructor a partir de los atributos que se permiten almacenar en la
	 * tabla que representa la entidad receta en la BBDD.
	 */
	public RecetaVO (String nombre, String descripcion, String plato, String numPersonas,
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes, ArrayList<String> unidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plato = plato;
		this.numPersonas = numPersonas;
		this.ingredientes = ingredientes;
		this.pesoIngredientes = pesoIngredientes;
		this.unidad = unidad;
	}

	/**
	 * Constructor a partir de los atributos que se permiten almacenar en la
	 * tabla que representa la entidad comentario en la BBDD, incluyendo el id
	 * de la receta.
	 */
	public RecetaVO (String id, String nombre, String descripcion, String plato, String numPersonas,
			String validada, ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes, ArrayList<String> unidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plato = plato;
		this.numPersonas = numPersonas;
		this.validada = validada;
		this.ingredientes = ingredientes;
		this.pesoIngredientes = pesoIngredientes;
		this.unidad = unidad;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo id del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa el id del objeto RecetaVO.
	 */
	public String getId () {
		return this.id;
	}

	/**
	 * Modifica la cadena de carácteres del atributo id del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo id del objeto RecetaVO.
	 */
	public void setId (String nuevo) {
		this.id = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo nombre del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa el nombre del objeto RecetaVO.
	 */
	public String getNombre () {
		return this.nombre;
	}

	/**
	 * Modifica la cadena de carácteres del atributo nombre del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo nombre del objeto RecetaVO.
	 */
	public void setNombre (String nuevo) {
		this.nombre = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo descripcion del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa la descripcion del objeto RecetaVO.
	 */
	public String getDescripcion () {
		return this.descripcion;
	}

	/**
	 * Modifica la cadena de carácteres del atributo descripcion del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa la nueva descripcion del objeto RecetaVO.
	 */
	public void setDescripcion (String nuevo) {
		this.descripcion = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo plato del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa el plato del objeto RecetaVO.
	 */
	public String getPlato () {
		return this.plato;
	}

	/**
	 * Modifica la cadena de carácteres del atributo plato del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo plato del objeto RecetaVO.
	 */
	public void setPlato (String nuevo) {
		this.plato = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo numPersonas del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa el numPersonas del objeto RecetaVO.
	 */
	public String getNumPersonas () {
		return this.numPersonas;
	}

	/**
	 * Modifica la cadena de carácteres del atributo numPersonas del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo numPersonas del objeto RecetaVO.
	 */
	public void setNumPersonas (String nuevo) {
		this.numPersonas = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo validada del objeto RecetaVO.
	 *
	 * @return Cadena de carácteres que representa la validacion del objeto RecetaVO.
	 */
	public String getValidada () {
		return this.validada;
	}

	/**
	 * Modifica la cadena de carácteres del atributo validada del objeto RecetaVO.
	 *
	 * @param nuevo Cadena de carácteres que representa la nueva validacion del objeto RecetaVO.
	 */
	public void setValidada (String nuevo) {
		this.validada = nuevo;
	}

	/**
	 * Devuelve una lista con los ingredientes almacenados en el objeto RecetaVO.
	 *
	 * @return Lista de cadenas de caracteres con los ingredientes que se relacionan
	 * 		   con este objeto ingrediente.
	 */
	public ArrayList<String> getIngredientes() {
		return this.ingredientes;
	}

	/**
	 * Modifica la lista de ingredientes almacenados en este objeto RecetaVO.
	 *
	 * @param nuevo Lista de cadenas de carácteres con ingredientes.
	 */
	public void setIngredientes (ArrayList<String> nuevo) {
		this.ingredientes = nuevo;
	}

	/**
	 * Devuelve una lista con los pesos de los ingredientes almacenados en el objeto RecetaVO.
	 *
	 * @return Lista de cadenas de caracteres con los pesos de los ingredientes que se relacionan
	 * 		   con este objeto ingrediente.
	 */
	public ArrayList<String> getPesoIngredientes() {
		return this.pesoIngredientes;
	}

	/**
	 * Modifica la lista de los pesos de los ingredientes almacenados en este objeto RecetaVO.
	 *
	 * @param nuevo Lista de cadenas de carácteres con los pesos de los ingredientes.
	 */
	public void setPesoIngredientes(ArrayList<String> nuevo) {
		this.pesoIngredientes = nuevo;
	}

	/**
	 * Devuelve una lista con las unidades de peso de los ingredientes almacenados en el
	 * objeto RecetaVO.
	 *
	 * @return Lista de cadenas de caracteres con las unidades de peso de los ingredientes
	 * 		   que se relacionan con este objeto ingrediente.
	 */
	public ArrayList<String> getUnidad() {
		return this.unidad;
	}

	/**
	 * Modifica la lista de las unidades de los pesos de los ingredientes almacenados en este
	 * objeto RecetaVO.
	 *
	 * @param nuevo Lista de cadenas de carácteres con las unidades de los pesos de los
	 *              ingredientes.
	 */
	public void setUnidad(ArrayList<String> nuevo) {
		this.unidad = nuevo;
	}
}

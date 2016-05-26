package me4l;

/**
 * Clase de tipo Value Object que representa una entidad simple en la BBDD,
 * en este caso, la entidad representada en la tabla "ingrediente" de la BBDD.
 */
public class IngredienteVO {
	
	private String nombre;
	private String tipo;

	/**
	 * Constructor por defecto.
	 */
	public IngredienteVO() {
		
	}

	/**
	 * Constructor a partir de los atributos que se permiten almacenar en la
	 * tabla que representa la entidad ingrediente en la BBDD.
	 */
	public IngredienteVO(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo nombre del objeto IngredienteVO.
	 *
	 * @return Cadena de carácteres que representa el nombre del objeto IngredienteVO.
	 */
	public String getNombre () {
		return this.nombre;
	}

	/**
	 * Modifica la cadena de carácteres del atributo nombre del objeto IngredienteVO.
	 *
	 * @param nuevoNombre Cadena de carácteres que representa el nuevo nombre del objeto
	 * 					  IngredienteVO.
	 */
	public void setNombre (String nuevoNombre) {
		this.nombre = nuevoNombre;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo tipo del objeto IngredienteVO.
	 *
	 * @return Cadena de carácteres que representa el tipo del objeto IngredienteVO.
	 */
	public String getTipo () {
		return this.tipo;
	}

	/**
	 * Modifica la cadena de carácteres del atributo tipo del objeto IngredienteVO.
	 *
	 * @param nuevoTipo Cadena de carácteres que representa el nuevo tipo del objeto
	 * 					IngredienteVO.
	 */
	public void setTipo (String nuevoTipo) {
		this.tipo = nuevoTipo;
	}
}

package me4l;

/**
 * Clase de tipo Value Object que representa una entidad simple en la BBDD,
 * en este caso, la entidad representada en la tabla "comentarios" de la BBDD.
 */
public class ComentarioVO {

	private String idReceta;
	private String idComentario;
	private String contenido;

	/**
	 * Constructor por defecto.
	 */
	public ComentarioVO () {
		
	}

	/**
	 * Constructor a partir de los atributos que se permiten almacenar en la
	 * tabla que representa la entidad comentario en la BBDD.
	 */
	public ComentarioVO (String idReceta, String comentario) {
		this.idReceta = idReceta;
		this.contenido = comentario;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo idReceta del objeto ComentarioVO.
	 *
	 * @return Cadena de carácteres que representa el idReceta del objeto ComentarioVO.
	 */
	public String getIdReceta () {
		return this.idReceta;
	}

	/**
	 * Modifica la cadena de carácteres del atributo idReceta del objeto ComentarioVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo idReceta del objeto
	 * 				ComentarioVO.
	 */
	public void setIdReceta (String nuevo) {
		this.idReceta = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo idComentario del objeto ComentarioVO.
	 *
	 * @return Cadena de carácteres que representa el idComentario del objeto ComentarioVO.
	 */
	public String getIdComentario () {
		return this.idComentario;
	}

	/**
	 * Modifica la cadena de carácteres del atributo idComentario del objeto ComentarioVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo idComentario del objeto
	 * 				ComentarioVO.
	 */
	public void setIdComentario (String nuevo) {
		this.idComentario = nuevo;
	}

	/**
	 * Devuleve una cadena de carácteres con el atributo contenido del objeto ComentarioVO.
	 *
	 * @return Cadena de carácteres que representa el contenido del objeto ComentarioVO.
	 */
	public String getContenido () {
		return this.contenido;
	}

	/**
	 * Modifica la cadena de carácteres del atributo contenido del objeto ComentarioVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo contenido del objeto
	 * 				ComentarioVO.
	 */
	public void setContenido (String nuevo) {
		this.contenido = nuevo;
	}
}

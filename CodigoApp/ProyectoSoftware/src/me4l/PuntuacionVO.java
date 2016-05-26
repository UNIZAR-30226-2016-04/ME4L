package me4l;

/**
 * Clase de tipo Value Object que representa una entidad simple en la BBDD,
 * en este caso, la entidad representada en la tabla "puntuacion" de la BBDD.
 */
public class PuntuacionVO {
	
	private String idReceta;
	private String ip;
	private String puntos;

	/**
	 * Constructor por defecto.
	 */
	public PuntuacionVO () {
		
	}

	/**
	 * Constructor a partir de los atributos que se permiten almacenar en la
	 * tabla que representa la entidad puntuacion en la BBDD.
	 */
	public PuntuacionVO (String idReceta, String ip, String puntos) {
		this.idReceta = idReceta;
		this.ip = ip;
		this.puntos = puntos;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo idReceta del objeto PuntuacionVO.
	 *
	 * @return Cadena de carácteres que representa el idReceta del objeto PuntuacionVO.
	 */
	public String getIdReceta () {
		return this.idReceta;
	}

	/**
	 * Modifica la cadena de carácteres del atributo idReceta del objeto PuntuacionVO.
	 *
	 * @param nuevo Cadena de carácteres que representa el nuevo idReceta del objeto
	 * 				PuntuacionVO.
	 */
	public void setIdReceta (String nuevo) {
		this.idReceta = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo ip del objeto PuntuacionVO.
	 *
	 * @return Cadena de carácteres que representa la ip del objeto PuntuacionVO.
	 */
	public String getIp () {
		return this.ip;
	}

	/**
	 * Modifica la cadena de carácteres del atributo ip del objeto PuntuacionVO.
	 *
	 * @param nuevo Cadena de carácteres que representa la nueva ip del objeto
	 * 				PuntuacionVO.
	 */
	public void setIp (String nuevo) {
		this.ip = nuevo;
	}

	/**
	 * Devuelve una cadena de carácteres con el atributo puntos del objeto PuntuacionVO.
	 *
	 * @return Cadena de carácteres que representa los puntos del objeto PuntuacionVO.
	 */
	public String getPuntos () {
		return this.puntos;
	}

	/**
	 * Modifica la cadena de carácteres del atributo puntos del objeto PuntuacionVO.
	 *
	 * @param nuevo Cadena de carácteres que representa los nuevos puntos del objeto
	 * 				PuntuacionVO.
	 */
	public void setPuntos (String nuevo) {
		this.puntos = nuevo;
	}
}

package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que implementa un patrón de acceso a BBDD de tipo Table Data Gateway,
 * en este caso, para la tabla de la BBDD que almacena los datos de una puntuación.
 */
public class PuntuacionDAO {
	
	private Connection conexion;

	/**
	 * Constructor de la clase PuntuacionDAO.
	 *
	 * @param conexion Objeto de tipo Connection que contiene la información necesaria
	 *                 para realizar la conexión con la BBDD.
	 */
	public PuntuacionDAO (Connection conexion) {

		this.conexion = conexion;
	}

	/**
	 * Función que se encarga de insertar los datos de una puntuación en la BBDD.
	 *
	 * @param puntuacion Objeto de tipo PuntuacionVO que contiene la información de una puntuación
	 *                   que se ha de almacenar en la BBDD.
	 */
	public void addPuntuacion (PuntuacionVO puntuacion) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"INSERT INTO puntuacion VALUES ('" + puntuacion.getIdReceta() + "', '"
					+ puntuacion.getIp() + "', '" + puntuacion.getPuntos() + "');");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de modificar los datos de una puntuación existente en la BBDD.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere modificar la puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 * @param puntos Cadena de caracteres que representa el nuevo valor de la puntuación identificada
	 *               mediante los dos parámetros anteriores.
	 */
	public void modificarPuntuacion (String idReceta, String ip, String puntos) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"UPDATE puntuacion SET puntos='" + puntos + "' WHERE ip='" + ip + "' AND idReceta ='" +
							idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de eliminar una puntuación existente en la BBDD.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere eliminar su puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 */
	public void eliminarPuntuacion (String idReceta, String ip) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"DELETE FROM puntuacion WHERE ip='" + ip + "' AND idReceta ='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de buscar en la BBDD los datos de una puntuación existente.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere devolver su puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 * @return Objeto de tipo PuntuacionVO con toda la información almacenada sobre la puntuación
	 *         almacenada en la BBDD con el id e ip especificados.
	 */
	public PuntuacionVO devolverPuntuacion (String idReceta, String ip) {
		
		try {
			PuntuacionVO puntuacion = new PuntuacionVO();
			puntuacion.setIdReceta(idReceta);
			puntuacion.setIp(ip);
			
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM puntuacion WHERE ip='" + ip + "' AND idReceta='" +
					idReceta + "';");
			
			rs.next();
			puntuacion.setPuntos(rs.getString("puntos"));
			
			return puntuacion;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de comprobar si existe una puntuación en la BBDD, con el id
	 * e ip especificados como parámetros.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere buscar.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 * @return Variable booleana con valor true si los identificadores especificados como
	 * 		   parámetros, se corresponden con una puntuación de la BBDD; y valor false en caso contrario.
	 */
	public boolean existePuntuacion (String idReceta, String ip) {
		
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM puntuacion WHERE ip='" + ip + "' AND idReceta='" +
					idReceta + "';");
			boolean existe;
			if (rs.next()) {
				existe = true;
			}
			else {
				existe = false;
			}
			rs.close();
			return existe;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Función que se encarga de calcular la media de todas las puntuaciones de la receta
	 * representada por el identificador pasado como parámetro.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere buscar.
	 * @return Cadena de caracteres que representa la media de todas las puntuaciones realizadas
	 * 		   sobre la receta. Si no existe ninguna puntuación para esa receta se devuelve -1.
	 */
	public String mediaPuntuacion (String idReceta) {

		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM puntuacion WHERE idReceta='" +	idReceta + "';");

			double total = 0.0;
			double contador = 0.0;
			while (rs.next()) {
				total += Double.parseDouble(rs.getString("puntos"));
				contador++;
			}
			s.close();

			if (contador == 0.0) {
				return "-1";
			} else {
				return Double.toString(total/contador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que implementa un patrón de acceso a BBDD de tipo Table Data Gateway,
 * en este caso, para la tabla de la BBDD que almacena los datos de un ingrediente.
 */
public class IngredienteDAO {
	
	private Connection conexion;

	/**
	 * Constructor de la clase IngredienteDAO.
	 *
	 * @param conexion Objeto de tipo Connection que contiene la información necesaria
	 *                 para realizar la conexión con la BBDD.
	 */
	public IngredienteDAO (Connection conexion) {
		this.conexion = conexion;
	}

	/**
	 * Función que se encarga de insertar los datos de un ingrediente en la BBDD.
	 *
	 * @param ingrediente Objeto de tipo IngredienteVO que contiene la información de un ingrediente
	 *                    que se ha de almacenar en la BBDD.
	 */
	public void addIngrediente (IngredienteVO ingrediente) {
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"INSERT INTO `ingrediente` VALUES ('" + ingrediente.getNombre() + "','" + ingrediente.getTipo() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todos los ingrediente existentes en
	 * la BBDD.
	 *
	 * @return Lista de cadena de caracteres con los nombres de los ingredientes almacenados
	 * 		   en la BBDD.
	 */
	public ArrayList<String> obtenerIngredientes() {
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM ingrediente");
			ArrayList<String> ingredientes = new ArrayList<String>();
			while (rs.next()) {
				ingredientes.add(rs.getString("nombre"));				
			}
			rs.close();
			return ingredientes; 
		} catch (SQLException ex) {
			return null;
		} 
	}
}

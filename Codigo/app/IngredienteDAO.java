package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class IngredienteDAO {
	
	private Connection conexion;
	
	public IngredienteDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
	public void addIngrediente (IngredienteVO ingrediente) {
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"INSERT INTO `ingrediente` (`nombre`,`tipo`) VALUES ('" + ingrediente.getNombre() + "','" + ingrediente.getTipo() + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

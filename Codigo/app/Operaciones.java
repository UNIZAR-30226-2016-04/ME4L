package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Operaciones {
	private IngredienteDAO ingredienteDAO;
	
	public Operaciones () {
		try{
			Conexion c;
			c = new Conexion();
			Connection conexion = c.getConnection();			
			this.ingredienteDAO = new IngredienteDAO(conexion);
			
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}
}

package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IngredienteDAO {
	
	private Connection conexion;
	
	public IngredienteDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
	public void addIngrediente (IngredienteVO ingrediente) {
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"INSERT INTO `ingrediente` VALUES ('" + ingrediente.getNombre() + "','" + ingrediente.getTipo() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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

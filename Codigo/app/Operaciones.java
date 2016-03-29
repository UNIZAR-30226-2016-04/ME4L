package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operaciones {
	private IngredienteDAO ingredienteDAO;
	private RecetaDAO recetaDAO;
	
	public Operaciones () {
		try{
			Conexion c;
			c = new Conexion();
			Connection conexion = c.getConnection();
			this.ingredienteDAO = new IngredienteDAO(conexion);
			this.recetaDAO = new RecetaDAO(conexion);
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}
	
	public void addIngrediente (String nombre, String tipo) {
		IngredienteVO ingrediente = new IngredienteVO(nombre, tipo);
		ingredienteDAO.addIngrediente(ingrediente);
	}
	
	public void addReceta (String nombre, String descripcion, String plato, String personas, 
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes) {
		
		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes);
		recetaDAO.addReceta(receta);
	}
	
	public ArrayList<String> obtenerIngredientes() {
		return ingredienteDAO.obtenerIngredientes();
	}
}

package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operaciones {
	private IngredienteDAO ingredienteDAO;
	private RecetaDAO recetaDAO;
	private PuntuacionDAO puntuacionDAO;
	
	public Operaciones () {
		
		try{
			Conexion c;
			c = new Conexion();
			Connection conexion = c.getConnection();
			
			this.ingredienteDAO = new IngredienteDAO(conexion);
			this.recetaDAO = new RecetaDAO(conexion);
			this.puntuacionDAO = new PuntuacionDAO(conexion);
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}
	
	public void addIngrediente (String nombre, String tipo) {
		
		IngredienteVO ingrediente = new IngredienteVO(nombre, tipo);
		ingredienteDAO.addIngrediente(ingrediente);
		System.out.println("Ingrediente añadido correctamente.");
	}
	
	public void addReceta (String nombre, String descripcion, String plato, String personas, 
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes) {
		
		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes);
		recetaDAO.addReceta(receta);
		System.out.println("Receta añadida correctamente.");
	}

	public void addRecetaV (String nombre, String descripcion, String plato, String personas,
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes) {

		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes);
		recetaDAO.addRecetaV(receta);
		System.out.println("Receta validada añadida correctamente.");
	}
	
	public void modificarReceta (String nombre, String descripcion, String plato, String personas, 
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes, String idReceta) {
		
		if (recetaDAO.existeReceta(idReceta)) {
			RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes);
			recetaDAO.modificarReceta(receta, idReceta);
			System.out.println("Receta modificada correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}

	public void validarReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.validarReceta(idReceta);
			System.out.println("Receta validada correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}
	
	public void eliminarReceta (String idReceta) {
		
		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.eliminarReceta(idReceta);
			System.out.println("Receta eliminada correctamente.");
		} else {
			System.err.println("Error: No existe receta");
		}
		
	}
	
	public RecetaVO devolverReceta (String idReceta) {
		
		RecetaVO receta = new RecetaVO();
		if (recetaDAO.existeReceta(idReceta)) {
			receta = recetaDAO.devolverReceta(idReceta);
			System.out.println("Receta devuelta con �xito.");
		} else {
			System.err.println("Error: No existe receta.");
		}
		return receta;
	}
	
	public ArrayList<RecetaVO> obtenerNoValidadas () {
		return recetaDAO.obtenerNoValidadas();
	}
	
	public ArrayList<String> obtenerIngredientes() {
		
		return ingredienteDAO.obtenerIngredientes();
	}
	
	public void addPuntuacion (String idReceta, String ip, String puntos) {
		
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			System.err.println("Error: Ya existe una puntuaci�n para esa receta e IP.");
		} else {
			if (recetaDAO.existeReceta(idReceta)) {
				PuntuacionVO puntuacion = new PuntuacionVO (idReceta, ip, puntos);
				puntuacionDAO.addPuntuacion(puntuacion);
				System.out.println("Puntuaci�n a�adida correctamente.");
			} else {
				System.err.println("Error: No existe receta.");
			}
		}
	}
	
	public void modificarPuntuacion (String idReceta, String ip, String puntos) {
		
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacionDAO.modificarPuntuacion(puntos);
			System.out.println("Puntuaci�n modificada correctamente.");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
	}
	
	public void eliminarPuntuacion (String idReceta, String ip) {
		
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacionDAO.eliminarPuntuacion(idReceta, ip);
			System.out.println("Puntuaci�n eliminada correctamente.");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
	}
	
	public PuntuacionVO devolverPuntuacion (String idReceta, String ip) {
		
		PuntuacionVO puntuacion = new PuntuacionVO();
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacion = puntuacionDAO.devolverPuntuacion(idReceta, ip);
			System.out.println("Puntuaci�n devuelta correctamente");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
		
		return puntuacion;
	}
}

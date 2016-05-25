package me4l;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operaciones {

	private IngredienteDAO ingredienteDAO;
	private RecetaDAO recetaDAO;
	private PuntuacionDAO puntuacionDAO;
	private ComentarioDAO comentarioDAO;
	
	public Operaciones () {
		
		try{
			Conexion c;
			c = new Conexion();
			Connection conexion = c.getConnection();
			
			this.ingredienteDAO = new IngredienteDAO(conexion);
			this.recetaDAO = new RecetaDAO(conexion);
			this.puntuacionDAO = new PuntuacionDAO(conexion);
			this.comentarioDAO = new ComentarioDAO(conexion);

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
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes,ArrayList<String> unidades) {
		
		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes,unidades);
		recetaDAO.addReceta(receta);
		System.out.println("Receta añadida correctamente.");
	}

	public void addRecetaV (String nombre, String descripcion, String plato, String personas,
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes,ArrayList<String> unidades) {

		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes,unidades);
		recetaDAO.addRecetaV(receta);
		System.out.println("Receta validada añadida correctamente.");
	}
	
	public void modificarReceta (String nombre, String descripcion, String plato, String personas, 
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes,ArrayList<String> unidades, String idReceta) {
		
		if (recetaDAO.existeReceta(idReceta)) {
			RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes,unidades);
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

	public void desvalidarReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.desvalidarReceta(idReceta);
			System.out.println("Receta desvalidada correctamente.");
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

	public ArrayList<RecetaVO> obtenerValidadas () {

		return recetaDAO.obtenerValidadas();
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
			puntuacionDAO.modificarPuntuacion(idReceta, ip, puntos);
			System.out.println("Puntuaci�n modificada correctamente.");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
	}
	
	public void eliminarPuntuacion (String idReceta, String ip) {
		
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacionDAO.eliminarPuntuacion(idReceta, ip);
			System.out.println("Puntuación eliminada correctamente.");
		} else {
			System.err.println("Error: No existe puntuación para esa receta e IP.");
		}
	}
	
	public PuntuacionVO devolverPuntuacion (String idReceta, String ip) {
		
		PuntuacionVO puntuacion = new PuntuacionVO();
		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacion = puntuacionDAO.devolverPuntuacion(idReceta, ip);
			System.out.println("Puntuación devuelta correctamente");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
		
		return puntuacion;
	}

	public void addComentario (String idReceta, String contenido) {

		if (recetaDAO.existeReceta(idReceta)) {
			ComentarioVO comentario = new ComentarioVO (idReceta, contenido);
			comentarioDAO.addComentario(comentario);
			System.out.println("Comentario añadido correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}

	public void modificarComentario (String idComentario, String contenido) {

		if (comentarioDAO.existeComentario(idComentario)) {
			comentarioDAO.modificarComentario(idComentario, contenido);
			System.out.println("Comentario modificado correctamente.");
		} else {
			System.err.println("Error: No existe comentario.");
		}
	}

	public void eliminarComentario (String idComentario) {

		if (comentarioDAO.existeComentario(idComentario)) {
			comentarioDAO.eliminarComentario(idComentario);
			System.out.println("Comentario eliminado correctamente.");
		} else {
			System.err.println("Error: No existe comentario.");
		}
	}

	public ComentarioVO devolverComentario (String idComentario) {

		ComentarioVO comentario = new ComentarioVO();
		if (comentarioDAO.existeComentario(idComentario)) {
			comentario = comentarioDAO.devolverComentario(idComentario);
			System.out.println("Comentario devuelto correctamente");
		} else {
			System.err.println("Error: No existe comentario.");
		}

		return comentario;
	}

	public ArrayList<ComentarioVO> comentariosReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			return comentarioDAO.comentariosReceta(idReceta);
		} else {
			return null;
		}
	}

	public ArrayList<RecetaVO> busqueda (String nombre, String nPersonas, String ingrediente, String plato) {

		ArrayList<RecetaVO> recetas;

		if ((nPersonas == null || nPersonas.equals("")) && (ingrediente == null || ingrediente.equals("")) && (plato == null || plato.equals(""))) {
			recetas = recetaDAO.buscarPorNombre(nombre);
		} else if ((nombre == null || nombre.equals("")) && (nPersonas == null || nPersonas.equals("")) && (plato == null || plato.equals(""))) {
			recetas = recetaDAO.buscarPorIngrediente(ingrediente);
		} else if ((nombre == null || nombre.equals("")) && (ingrediente == null || ingrediente.equals("")) && (plato == null || plato.equals(""))) {
			recetas = recetaDAO.buscarPorNPersonas(nPersonas);
		} else if ((nombre == null || nombre.equals("")) && (ingrediente == null || ingrediente.equals("")) && (nPersonas == null || nPersonas.equals(""))) {
			recetas = recetaDAO.buscarPorPlato(plato);
		} else {
			recetas = recetaDAO.busquedaAvanzada(nombre, nPersonas, ingrediente, plato);
		}
		return recetas;
	}

	public ArrayList<RecetaVO> menuDelDia () {
		return recetaDAO.menuDelDia();
	}
}

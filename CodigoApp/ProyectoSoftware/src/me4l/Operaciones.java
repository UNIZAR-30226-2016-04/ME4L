package me4l;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que se encarga de encapsular todas las operaciones de las clases DAO.
 * Además añade algo de funcionalidad a estas.
 */
public class Operaciones {

	private IngredienteDAO ingredienteDAO;
	private RecetaDAO recetaDAO;
	private PuntuacionDAO puntuacionDAO;
	private ComentarioDAO comentarioDAO;

	/**
	 * Constructor de la clase Operaciones. Inicia una conexión con la BBDD
	 * y crea los objetos DAO para interaccionar mediante esta conexión.
	 */
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

	/**
	 * Función que se encarga de invocar a la función correspondiente del objeto IngredienteDAO para
	 * añadir un ingrediente a la BBDD.
	 *
	 * @param nombre Cadena de caracteres con el nombre del ingrediente que se desea añadir a la BBDD.
	 * @param tipo Cadena de caracteres con el tipo del ingrediente que se desea añadir a la BBDD.
	 */
	public void addIngrediente (String nombre, String tipo) {
		
		IngredienteVO ingrediente = new IngredienteVO(nombre, tipo);
		ingredienteDAO.addIngrediente(ingrediente);
		System.out.println("Ingrediente añadido correctamente.");
	}

	/**
	 * Función que se encarga de invocar a la función correspondiente del onjeto IngredienteDAO para
	 * obtener todos los ingredientes de la BBDD.
	 *
	 * @return Lista de cadena de caracteres con los nombres de todos los ingredientes almacenados
	 * 		   en la BBDD.
	 */
	public ArrayList<String> obtenerIngredientes() {

		return ingredienteDAO.obtenerIngredientes();
	}

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado
	 * como parámetro. Si es asi invoca a la función correspondiente del objeto ComentarioDAO
	 * para añadir un comentario a la receta.
	 *
	 * @param idReceta Cadena de caracteres con el idReceta del comentario que se desea añadir a la BBDD.
	 * @param contenido Cadena de caracteres con el contenido del comentario que se desea añadir a la BBDD.
	 */
	public void addComentario (String idReceta, String contenido) {

		if (recetaDAO.existeReceta(idReceta)) {
			ComentarioVO comentario = new ComentarioVO (idReceta, contenido);
			comentarioDAO.addComentario(comentario);
			System.out.println("Comentario añadido correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe un comentario, en la BBDD, con el id
	 * pasado como parámetro. Si es así invoca a la función correspondiente del objeto
	 * ComentarioDAO para modificarlo.
	 *
	 * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
	 *                     que ha de ser modificado.
	 * @param contenido Cadena de caracteres que representa el nuevo valor del texto del comentario
	 *                  almacenado en la BBDD.
	 */
	public void modificarComentario (String idComentario, String contenido) {

		if (comentarioDAO.existeComentario(idComentario)) {
			comentarioDAO.modificarComentario(idComentario, contenido);
			System.out.println("Comentario modificado correctamente.");
		} else {
			System.err.println("Error: No existe comentario.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe un comentario, en la BBDD,  con el id pasado
	 * como parámetro. Si es así invoca a la función correspondiete del objeto ComentarioDAO para eliminarlo.
	 *
	 * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
	 *                     que ha de ser eliminado.
	 */
	public void eliminarComentario (String idComentario) {

		if (comentarioDAO.existeComentario(idComentario)) {
			comentarioDAO.eliminarComentario(idComentario);
			System.out.println("Comentario eliminado correctamente.");
		} else {
			System.err.println("Error: No existe comentario.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe un comentario, en la BBDD, con el id pasado
	 * como parámetro. Si es así invoca a la función correspondiente del objeto ComentarioDAO
	 * para devolverlo.
	 *
	 * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
	 *                     que ha de ser devuelto.
	 * @return Objeto de tipo ComentarioVO con toda la información almacenada sobre el comentario
	 *         almacenado en la BBDD con el id especificado.
	 */
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

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado como
	 * parámetro. Si es así invoca a la función correspondiente del objeto ComentarioDAO para
	 * devolver todos los comentarios de esa receta.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta de la que se han de
	 *                 devolver todos sus comentarios.
	 * @return Lista de objetos ComentarioVO que contiene todos los comentarios de la
	 *         receta con id especificado como parámetro.
	 */
	public ArrayList<ComentarioVO> comentariosReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			return comentarioDAO.comentariosReceta(idReceta);
		} else {
			return null;
		}
	}

	/**
	 * Función que se encarga de comprobar si existe ya una puntuación, en la BBDD, para el idReceta
	 * e ip pasados como parámetro. Si no es así, invoca a la función correspondiente del objeto
	 * PuntuacionDAO para añadir la puntuación.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta.
	 * @param ip Cadena de caracteres que identifica la ip del usuario que puntúa.
	 * @param puntos Cadena de caracteres que representa la puntuación otorgada por el usuario
	 *               con ip, a la receta con id idReceta.
	 */
	public boolean addPuntuacion (String idReceta, String ip, String puntos) {

		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			System.err.println("Error: Ya existe una puntuaci�n para esa receta e IP.");
			return false;
		} else {
			if (recetaDAO.existeReceta(idReceta)) {
				PuntuacionVO puntuacion = new PuntuacionVO (idReceta, ip, puntos);
				puntuacionDAO.addPuntuacion(puntuacion);
				System.out.println("Puntuaci�n a�adida correctamente.");
			} else {
				System.err.println("Error: No existe receta.");
			}
			return true;
		}
	}

	/**
	 * Función que se encarga de comprobar si existe ya una puntuación, en la BBDD, para el idReceta
	 * e ip pasados como parámetro. Si es así, invoca a la función correspondiente del objeto
	 * PuntuacionDAO para modificar la puntuación.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere modificar la puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 * @param puntos Cadena de caracteres que representa el nuevo valor de la puntuación identificada
	 *               mediante los dos parámetros anteriores.
	 */
	public void modificarPuntuacion (String idReceta, String ip, String puntos) {

		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacionDAO.modificarPuntuacion(idReceta, ip, puntos);
			System.out.println("Puntuaci�n modificada correctamente.");
		} else {
			System.err.println("Error: No existe puntuaci�n para esa receta e IP.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe ya una puntuación, en la BBDD, para el idReceta
	 * e ip pasados como parámetro. Si es así, invoca a la función correspondiente del objeto
	 * PuntuacionDAO para eliminar la puntuación.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere eliminar su puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 */
	public void eliminarPuntuacion (String idReceta, String ip) {

		if (puntuacionDAO.existePuntuacion(idReceta, ip)) {
			puntuacionDAO.eliminarPuntuacion(idReceta, ip);
			System.out.println("Puntuación eliminada correctamente.");
		} else {
			System.err.println("Error: No existe puntuación para esa receta e IP.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe ya una puntuación, en la BBDD, para el idReceta
	 * e ip pasados como parámetro. Si es así, invoca a la función correspondiente del objeto
	 * PuntuacionDAO para devolver la puntuación.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere devolver su puntuación.
	 * @param ip Cadena de caracteres que identifica la ip de una puntuación realizada, sobre
	 *           la receta especificada en el parámetro anterior.
	 * @return Objeto de tipo PuntuacionVO con toda la información almacenada sobre la puntuación
	 *         almacenada en la BBDD con el id e ip especificados.
	 */
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

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con id idReceta.
	 * Si es así, invoca a la función correspondiente del objeto PuntuacionDAO para calcular
	 * la media de todas las puntuaciones de la receta representada por el identificador pasado
	 * como parámetro.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que junto con la ip, se quiere buscar.
	 * @return Cadena de caracteres que representa la media de todas las puntuaciones realizadas
	 * 		   sobre la receta. Si no existe ninguna puntuación para esa receta se devuelve -1.
	 */
	public String mediaPuntuacion (String idReceta) {

		String media = "";
		if (recetaDAO.existeReceta(idReceta)) {
			media = puntuacionDAO.mediaPuntuacion(idReceta);
			System.out.println("Media puntuación devuelta correctamente");
		} else {
			System.err.println("Error: No existen la receta.");
		}
		return media;
	}

	/**
	 * Función que se encarga de invocar a la función correspondiente del objeto RecetaDAO, que
	 * añade una receta no validada a la BBDD.
	 *
	 * @param nombre Cadena de caracteres que representa el nombre de la receta a añadir a la BBDD.
	 * @param descripcion Cadena de caracteres que representa la descripción de la receta a
	 *                    añadir a la BBDD.
	 * @param plato Cadena de caracteres que representa el plato del día de la receta a añadir
	 *              a la BBDD.
	 * @param personas Cadena de caracteres que representa el número de personas de la receta a
	 *                 añadir a la BBDD.
	 * @param ingredientes Lista de cadena de caracteres con los ingredientes de la receta a añadir
	 *                     a la BBDD.
	 * @param pesoIngredientes Lista de cadena de caracteres con el peso de los ingredientes de la
	 *                         receta a añadir a la BBDD.
	 * @param unidades Lista de cadena de caracteres con las unidades de los pesos de los ingredientes
	 *                 de la receta a añadir a la BBDD.
	 */
	public void addReceta (String nombre, String descripcion, String plato, String personas, 
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes,ArrayList<String> unidades) {
		
		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes,unidades);
		recetaDAO.addReceta(receta);
		System.out.println("Receta añadida correctamente.");
	}

	/**
	 * Función que se encarga de invocar a la función correspondiente del objeto RecetaDAO, que
	 * añade una receta validada a la BBDD.
	 *
	 * @param nombre Cadena de caracteres que representa el nombre de la receta a añadir a la BBDD.
	 * @param descripcion Cadena de caracteres que representa la descripción de la receta a
	 *                    añadir a la BBDD.
	 * @param plato Cadena de caracteres que representa el plato del día de la receta a añadir
	 *              a la BBDD.
	 * @param personas Cadena de caracteres que representa el número de personas de la receta a
	 *                 añadir a la BBDD.
	 * @param ingredientes Lista de cadena de caracteres con los ingredientes de la receta a añadir
	 *                     a la BBDD.
	 * @param pesoIngredientes Lista de cadena de caracteres con el peso de los ingredientes de la
	 *                         receta a añadir a la BBDD.
	 * @param unidades Lista de cadena de caracteres con las unidades de los pesos de los ingredientes
	 *                 de la receta a añadir a la BBDD.
	 */
	public void addRecetaV (String nombre, String descripcion, String plato, String personas,
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes,ArrayList<String> unidades) {

		RecetaVO receta = new RecetaVO(nombre, descripcion, plato, personas, ingredientes, pesoIngredientes,unidades);
		recetaDAO.addRecetaV(receta);
		System.out.println("Receta validada añadida correctamente.");
	}

	/**
	 * Función que se encarga de comprobar si existe una receta con el id pasado como parámetro.
	 * Si es así, invoca a la función correspondiente del objeto RecetaDAO que modifica los datos
	 * de una receta existente en la BBDD.
	 *
	 * @param nombre Cadena de caracteres que representa el nombre de la receta a añadir a la BBDD.
	 * @param descripcion Cadena de caracteres que representa la descripción de la receta a
	 *                    añadir a la BBDD.
	 * @param plato Cadena de caracteres que representa el plato del día de la receta a añadir
	 *              a la BBDD.
	 * @param personas Cadena de caracteres que representa el número de personas de la receta a
	 *                 añadir a la BBDD.
	 * @param ingredientes Lista de cadena de caracteres con los ingredientes de la receta a añadir
	 *                     a la BBDD.
	 * @param pesoIngredientes Lista de cadena de caracteres con el peso de los ingredientes de la
	 *                         receta a añadir a la BBDD.
	 * @param unidades Lista de cadena de caracteres con las unidades de los pesos de los ingredientes
	 *                 de la receta a añadir a la BBDD.
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que ha de ser modificada.
	 */
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

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado
	 * como parámetro. Si es así, invoca a la función correspondiente del objeto RecetaDAO que
	 * valida una receta.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que ha de ser validada.
	 */
	public void validarReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.validarReceta(idReceta);
			System.out.println("Receta validada correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado
	 * como parámetro. Si es así, invoca a la función correspondiente del objeto RecetaDAO que
	 * desvalida una receta.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que ha de ser desvalidada.
	 */
	public void desvalidarReceta (String idReceta) {

		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.desvalidarReceta(idReceta);
			System.out.println("Receta desvalidada correctamente.");
		} else {
			System.err.println("Error: No existe receta.");
		}
	}

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado
	 * como parámetro. Si es así, invoca a la función correspondiente del objeto RecetaDAO
	 * que elimina la receta de la BBDD.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que ha de ser eliminada.
	 */
	public void eliminarReceta (String idReceta) {
		
		if (recetaDAO.existeReceta(idReceta)) {
			recetaDAO.eliminarReceta(idReceta);
			System.out.println("Receta eliminada correctamente.");
		} else {
			System.err.println("Error: No existe receta");
		}
		
	}

	/**
	 * Función que se encarga de comprobar si existe una receta, en la BBDD, con el id pasado
	 * como parámetro. Si es así, invoca a la función correspondiente del objeto RecetaDAO
	 * que la devuelve.
	 *
	 * @param idReceta Cadena de caracteres que identifica la receta, existente en la BBDD,
	 *                 que ha de ser devuelta.
	 * @return Objeto de tipo RecetaVO con toda la información almacenada sobre la receta
	 *         almacenada en la BBDD con el id especificado.
	 */
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

	/**
	 * Función que se encarga de invocar a la función correspondiete del objeto RecetaDAO
	 * que busca y devuelve todas las recetas existentes en la BBDD, que todavía no han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene todas las recetas no validadas
	 *         de la BBDD.
	 */
	public ArrayList<RecetaVO> obtenerNoValidadas () {

		return recetaDAO.obtenerNoValidadas();
	}

	/**
	 * Función que se encarga de invocar a la función correspondiete del objeto RecetaDAO
	 * que busca y devuelve todas las recetas existentes en la BBDD, que ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene todas las recetas validadas
	 *         de la BBDD.
	 */
	public ArrayList<RecetaVO> obtenerValidadas () {

		return recetaDAO.obtenerValidadas();
	}

	/**
	 * Función que se encarga de comprobar si los parámetros están vacíos o son null. Según
	 * si lo parámetros no son vacíos o si, invoca a una función o a otra del objeto RecetaDAO.
	 *
	 * @param nombre Cadena de caracteres que representa una keyword.
	 * @param nPersonas Cadena de caracteres que representa un número de personas.
	 * @param ingrediente Cadena de caracteres que representa un ingrediente existente en la BBDD.
	 * @param plato Cadena de caracteres que representa un plato.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas validadas
	 *         de la BBDD, las cuales sus datos coinciden con los pasados como parámetro.
	 */
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

	/**
	 * Función que se encarga de invocar a la función correspondiete del objeto RecetaDAO
	 * que busca y devuelve 3 recetas aleatoria de la BBDD, que ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene 4 recetas validadas extraídas de la
	 *         BBDD aleatoriamente.
	 */
	public ArrayList<RecetaVO> menuDelDia () {
		return recetaDAO.menuDelDia();
	}

	/**
	 * Función que se encarga de invocar a la función correspondiete del objeto RecetaDAO
	 * que busca y devuelve las 3 recetas más nuevas de la BBDD, que ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene 3 recetas validadas extraídas de la
	 *         BBDD.
	 */
	public ArrayList<RecetaVO> recetasMasNuevas () {
		return recetaDAO.recetasMasNuevas();
	}

	/**
	 * Función que se encarga de invocar a la función correspondiete del objeto RecetaDAO
	 * que busca y devuelve las 3 recetas existentes en la BBDD, que ya han sido validadas,
	 * con mayor media de votación.
	 *
	 * @return Lista de objetos RecetaVO que contiene 3 recetas validadas extraídas de la
	 *         BBDD.
	 */
	public ArrayList<RecetaVO> recetasMasVotadas () {
		return recetaDAO.recetasMasVotadas();
	}
}

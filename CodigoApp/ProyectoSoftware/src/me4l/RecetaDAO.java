package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase que implementa un patrón de acceso a BBDD de tipo Table Data Gateway,
 * en este caso, para la tabla de la BBDD que almacena los datos de una receta.
 */
public class RecetaDAO {
	private Connection conexion;

	/**
	 * Constructor de la clase RecetaDAO.
	 *
	 * @param conexion
	 *            Objeto de tipo Connection que contiene la información
	 *            necesaria para realizar la conexión con la BBDD.
	 */
	public RecetaDAO(Connection conexion) {
		this.conexion = conexion;
	}

	/**
	 * Función que se encarga de insertar los datos de una receta no validada
	 * en la BBDD.
	 *
	 * @param receta
	 *            Objeto de tipo RecetaVO que contiene la información de una
	 *            receta que se ha de almacenar en la BBDD.
	 */
	public void addReceta(RecetaVO receta) {

		try {
			Statement s = conexion.createStatement();
			/* Insertar en tabla receta */
			s.execute("INSERT INTO receta VALUES (NULL,'" + receta.getNombre() + "','" + receta.getDescripcion() + "','"
					+ receta.getPlato() + "','" + receta.getNumPersonas() + "','0');");
			ResultSet rs = s.executeQuery("SELECT id FROM receta WHERE nombre='" + receta.getNombre()
					+ "' AND descripcion='" + receta.getDescripcion() + "';");

			/* Insertar en tabla componente */
			rs.next();
			String id = rs.getString("id");
			ArrayList<String> ingredientes = receta.getIngredientes();
			ArrayList<String> pesos = receta.getPesoIngredientes();
			ArrayList<String> unidades = receta.getUnidad();

			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			Iterator<String> iteradorU = unidades.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();
			String unidad = iteradorU.next();
			s.execute("INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "', '"
					+ unidad + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute("INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "', '"
						+ unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de insertar los datos de una receta validada en
	 * la BBDD.
	 *
	 * @param receta
	 *            Objeto de tipo RecetaVO que contiene la información de una
	 *            receta que se ha de almacenar en la BBDD.
	 */
	public void addRecetaV(RecetaVO receta) {

		try {
			Statement s = conexion.createStatement();
			/* Insertar en tabla receta */
			s.execute("INSERT INTO receta VALUES (NULL,'" + receta.getNombre() + "','" + receta.getDescripcion() + "','"
					+ receta.getPlato() + "','" + receta.getNumPersonas() + "','1');");
			ResultSet rs = s.executeQuery("SELECT id FROM receta WHERE nombre='" + receta.getNombre()
					+ "' AND descripcion='" + receta.getDescripcion() + "';");

			/* Insertar en tabla componente */
			rs.next();
			String id = rs.getString("id");
			ArrayList<String> ingredientes = receta.getIngredientes();
			ArrayList<String> pesos = receta.getPesoIngredientes();
			ArrayList<String> unidades = receta.getUnidad();

			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			Iterator<String> iteradorU = unidades.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();
			String unidad = iteradorU.next();
			s.execute("INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "', '"
					+ unidad + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute("INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "', '"
						+ unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de modificar los datos de una receta existente en
	 * la BBDD.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser modificada.
	 * @param receta
	 *            Objeto de la clase RecetaVO con los nuevos datos de la receta
	 *            que se deben actualizar en la BBDD.
	 */
	public void modificarReceta(RecetaVO receta, String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute("UPDATE receta SET Nombre='" + receta.getNombre() + "', Descripcion='" + receta.getDescripcion()
					+ "', Plato='" + receta.getPlato() + "', numeroPersonas='" + receta.getNumPersonas()
					+ "' WHERE id='" + idReceta + "';");
			s.execute("DELETE FROM componente WHERE idReceta='" + idReceta + "';");
			ArrayList<String> ingredientes = receta.getIngredientes();
			ArrayList<String> pesos = receta.getPesoIngredientes();
			ArrayList<String> unidades = receta.getUnidad();

			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			Iterator<String> iteradorU = unidades.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();
			String unidad = iteradorU.next();
			s.execute("INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '1', '" + peso + "', '"
					+ unidad + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute("INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '0', '" + peso
						+ "', '" + unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de validar una receta.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser validada.
	 */
	public void validarReceta(String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute("UPDATE receta SET validada='1' WHERE id='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de desvalidar una receta.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser validada.
	 */
	public void desvalidarReceta(String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute("UPDATE receta SET validada='0' WHERE id='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de eliminar una receta existente en la BBDD.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser eliminada.
	 */
	public void eliminarReceta(String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute("DELETE FROM receta WHERE id = '" + idReceta + "';");
			s.execute("DELETE FROM componente WHERE idReceta = '" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que se encarga de buscar en la BBDD los datos de una receta
	 * existente.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser devuelta.
	 * @return Objeto de tipo RecetaVO con toda la información almacenada sobre
	 *         la receta almacenada en la BBDD con el id especificado.
	 */
	public RecetaVO devolverReceta(String idReceta) {

		try {
			RecetaVO receta = new RecetaVO();
			receta.setId(idReceta);

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE id='" + idReceta + "';");
			rs.next();

			receta.setNombre(rs.getString("nombre"));
			receta.setDescripcion(rs.getString("descripcion"));
			receta.setPlato(rs.getString("plato"));
			receta.setNumPersonas(rs.getString("numeroPersonas"));
			receta.setValidada(rs.getString("validada"));

			ArrayList<String> ingredientes = new ArrayList<>();
			ArrayList<String> peso = new ArrayList<>();
			ArrayList<String> unidades = new ArrayList<>();
			rs = s.executeQuery("SELECT * FROM componente WHERE idReceta='" + idReceta + "';");

			while (rs.next()) {
				if (rs.getString("esPrincipal").equals("1")) {
					ingredientes.add(0, rs.getString("ingrediente"));
					peso.add(0, rs.getString("peso"));
					unidades.add(0, rs.getString("unidad"));
				} else {
					ingredientes.add(rs.getString("ingrediente"));
					peso.add(rs.getString("peso"));
					unidades.add(rs.getString("unidad"));
				}
			}

			receta.setIngredientes(ingredientes);
			receta.setPesoIngredientes(peso);
			receta.setUnidad(unidades);

			s.close();
			return receta;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de comprobar si existe una receta en la BBDD, con
	 * el id especificado como parámetro.
	 *
	 * @param idReceta
	 *            Cadena de caracteres que identifica la receta, existente en la
	 *            BBDD, que ha de ser buscada.
	 * @return Variable booleana con valor true si el id especificado como
	 *         parámetro, se corresponde con una receta de la BBDD; y valor
	 *         false en caso contrario.
	 */
	public boolean existeReceta(String idReceta) {

		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT id FROM receta WHERE id='" + idReceta + "';");
			boolean existe = rs.next();
			rs.close();
			return existe;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que todavía no han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene todas las recetas no
	 *         validadas de la BBDD.
	 */
	public ArrayList<RecetaVO> obtenerNoValidadas() {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE validada='0';");

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD.
	 */
	public ArrayList<RecetaVO> obtenerValidadas() {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE validada='1';");

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas, las cuales su ingrediente
	 * principal es el especificado como parámetro.
	 *
	 * @param nombre
	 *            Cadena de caracteres que representa un ingrediente existente
	 *            en la BBDD.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD, las cuales su ingrediente principal es el
	 *         especificado como parámetro.
	 */
	public ArrayList<RecetaVO> buscarPorIngrediente(String nombre) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + nombre
					+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.validada='1';");

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas, las cuales nombre contiene la
	 * cadena de carateres especificada como parámetro.
	 *
	 * @param nombre
	 *            Cadena de caracteres que representa una keyword.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD, las cuales contienen en su nombre el
	 *         keyword pasado como parámetro.
	 */
	public ArrayList<RecetaVO> buscarPorNombre(String nombre) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs;

			if (nombre == null || nombre.equals("")) {
				rs = s.executeQuery("SELECT * FROM receta;");
			} else {
				rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') "
						+ "AND validada='1';");
			}

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas, las cuales su número de personas
	 * es el especificado como parámetro.
	 *
	 * @param nPersonas
	 *            Cadena de caracteres que representa un número de personas.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD, las cuales su número de personas coincide
	 *         con el pasado como parámetro.
	 */
	public ArrayList<RecetaVO> buscarPorNPersonas(String nPersonas) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "' " + "AND validada='1';");

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas, las cuales su plato es el
	 * especificado como parámetro.
	 *
	 * @param plato
	 *            Cadena de caracteres que representa un plato.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD, las cuales su plato coincide con el
	 *         especificado como parámetro.
	 */
	public ArrayList<RecetaVO> buscarPorPlato(String plato) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE Plato='" + plato + "' AND validada='1';");

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver todas las recetas existentes
	 * en la BBDD, que ya han sido validadas, las cuales sus datos coinciden con
	 * los que son pasados como parámetro. Si algún parámetro == null o es
	 * una cadena vacía, realiza la búsqueda no teniendo en cuenta ese
	 * parámetro.
	 *
	 * @param nombre
	 *            Cadena de caracteres que representa una keyword.
	 * @param nPersonas
	 *            Cadena de caracteres que representa un número de personas.
	 * @param ingrediente
	 *            Cadena de caracteres que representa un ingrediente existente
	 *            en la BBDD.
	 * @param plato
	 *            Cadena de caracteres que representa un plato.
	 * @return Lista de objetos RecetaVO que contiene todas las recetas
	 *         validadas de la BBDD, las cuales sus datos coinciden con los
	 *         pasados como parámetro.
	 */
	public ArrayList<RecetaVO> busquedaAvanzada(String nombre, String nPersonas, String ingrediente, String plato) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs;

			if (nombre == null || nombre.equals("")) {
				if (nPersonas == null || nPersonas.equals("")) {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND Plato='" + plato + "' "
							+ "AND r.validada='1';");
				} else if (ingrediente == null || ingrediente.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "' " + "AND Plato='"
							+ plato + "' AND validada='1';");
				} else if (plato == null || plato.equals("")) {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" + nPersonas
							+ "' AND r.validada='1';");
				} else {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" + nPersonas
							+ "' AND Plato='" + plato + "' AND r.validada='1';");
				}
			} else if (nPersonas == null || nPersonas.equals("")) {

				if (ingrediente == null || ingrediente.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%')"
							+ " AND Plato='" + plato + "' AND validada='1';");
				} else if (plato == null || plato.equals("")) {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " + "UPPER(r.nombre) LIKE UPPER('%"
							+ nombre + "%') AND r.validada='1';");
				} else {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " + "UPPER(r.nombre) LIKE UPPER('%"
							+ nombre + "%') AND Plato='" + plato + "' " + "AND r.validada='1';");
				}
			} else if (plato == null || plato.equals("")) {

				if (ingrediente == null || ingrediente.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') "
							+ "AND nPerosnas='" + nPersonas + "' AND validada='1';");
				} else {
					rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='" + ingrediente
							+ "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " + "UPPER(r.nombre) LIKE UPPER('%"
							+ nombre + "%') AND nPerosnas='" + nPersonas + "' " + "AND r.validada='1';");
				}
			} else {
				rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND "
						+ "numeroPersonas='" + nPersonas + "' AND Plato='" + plato + "' AND validada='1';");
			}

			while (rs.next()) {
				String idReceta = rs.getString("id");
				receta = devolverReceta(idReceta);
				recetas.add(receta);
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver 4 recetas aleatorias, cada
	 * una de un plato diferente, que se encuentran en la BBDD, y ya han sido
	 * validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene 4 recetas validadas
	 *         extraídas de la BBDD aleatoriamente.
	 */
	public ArrayList<RecetaVO> menuDelDia() {

		Random random = new Random();
		ArrayList<RecetaVO> recetas = new ArrayList<>();

		ArrayList<RecetaVO> aux = buscarPorPlato("Entrante");
		int indice = random.nextInt(aux.size() - 1);
		RecetaVO receta = aux.get(indice);
		ArrayList<String> ingredientes = receta.getIngredientes();
		String ingredienteE = ingredientes.get(0);
		recetas.add(receta);

		aux = buscarPorPlato("Primero");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredienteP = ingredientes.get(0);
		int i = 0;
		while (ingredienteP.equals(ingredienteE) && i < 500) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredienteP = ingredientes.get(0);
			i++;
		}
		if (i >= 500) {
			recetas.add(null);
		} else {
			recetas.add(receta);
		}
		
		aux = buscarPorPlato("Segundo");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredienteS = ingredientes.get(0);
		i = 0;
		while (ingredienteS.equals(ingredienteE) || ingredienteS.equals(ingredienteP) && i < 500) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredienteS = ingredientes.get(0);
			i++;
		}
		if (i >= 500) {
			recetas.add(null);
		} else {
			recetas.add(receta);
		}

		aux = buscarPorPlato("Postre");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredientePo = ingredientes.get(0);
		i = 0;
		while (ingredientePo.equals(ingredienteE) || ingredientePo.equals(ingredienteP)
				|| ingredientePo.equals(ingredienteS) && i < 500) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredientePo = ingredientes.get(0);
			i++;
		}
		if (i >= 500) {
			recetas.add(null);
		} else {
			recetas.add(receta);
		}
		for(RecetaVO r: recetas ){
			System.out.println(r.getNombre());
		}
		return recetas;
	}

	/**
	 * Función que se encarga de buscar y devolver las últimas 3 recetas
	 * añadidas, a la BBDD, y ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene 3 recetas validadas
	 *         extraídas de la BBDD.
	 */
	public ArrayList<RecetaVO> recetasMasNuevas() {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE validada='1';");

			rs.last();
			for (int i = 0; i < 3; i++) {
				receta = devolverReceta(rs.getString("id"));
				recetas.add(receta);
				rs.previous();
			}
			s.close();
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Función que se encarga de buscar y devolver las 3 recetas con mayor
	 * media de puntuación de la BBDD, y ya han sido validadas.
	 *
	 * @return Lista de objetos RecetaVO que contiene 3 recetas validadas
	 *         extraídas de la BBDD.
	 */
	public ArrayList<RecetaVO> recetasMasVotadas() {

		try {
			PuntuacionDAO puntuacionDAO = new PuntuacionDAO(this.conexion);
			ArrayList<RecetaVO> recetas = new ArrayList<>();

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE validada='1';");

			double[][] array = new double[3][2];
			while (rs.next()) {
				String idReceta = rs.getString("id");
				double media = Double.parseDouble(puntuacionDAO.mediaPuntuacion(idReceta));

				if (media > array[0][1]) {
					if (media > array[1][1]) {
						if (media > array[2][1]) {
							array[0][0] = array[1][0];
							array[0][1] = array[1][1];
							array[1][0] = array[2][0];
							array[1][1] = array[2][1];
							array[2][0] = Double.parseDouble(idReceta);
							array[2][1] = media;
						} else {
							array[0][0] = array[1][0];
							array[0][1] = array[1][1];
							array[1][0] = Double.parseDouble(idReceta);
							array[1][1] = media;
						}
					} else {
						array[0][0] = Double.parseDouble(idReceta);
						array[0][1] = media;
					}
				}
			}

			recetas.add(devolverReceta(Double.toString(array[2][0])));
			recetas.add(devolverReceta(Double.toString(array[1][0])));
			recetas.add(devolverReceta(Double.toString(array[0][0])));
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

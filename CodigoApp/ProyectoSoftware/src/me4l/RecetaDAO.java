package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RecetaDAO {
	private Connection conexion;
	
	public RecetaDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
	public void addReceta (RecetaVO receta) {
		
		try {
			Statement s = conexion.createStatement();
			/* Insertar en tabla receta */
			s.execute(
					"INSERT INTO receta VALUES (NULL,'" + receta.getNombre() + "','" 
							+ receta.getDescripcion() + "','" + receta.getPlato() 
							+ "','"+ receta.getNumPersonas() + "','0');");
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
			s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "', '" 
							+ unidad + "');");
			
			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "', '"
							+ unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addRecetaV (RecetaVO receta) {

		try {
			Statement s = conexion.createStatement();
			/* Insertar en tabla receta */
			s.execute(
					"INSERT INTO receta VALUES (NULL,'" + receta.getNombre() + "','"
							+ receta.getDescripcion() + "','" + receta.getPlato()
							+ "','"+ receta.getNumPersonas() + "','1');");
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
			s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "', '" 
						+ unidad + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute (
						"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "', '"
							+ unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modificarReceta (RecetaVO receta, String idReceta) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"UPDATE receta SET Nombre='" + receta.getNombre() + "', Descripcion='" + receta.getDescripcion() 
						+ "', Plato='" + receta.getPlato() + "', numeroPersonas='" + receta.getNumPersonas() 
						+"' WHERE id='" + idReceta + "';");
			s.execute(
					"DELETE FROM componente WHERE idReceta='" + idReceta + "';");
			ArrayList<String> ingredientes = receta.getIngredientes();
			ArrayList<String> pesos = receta.getPesoIngredientes();
			ArrayList<String> unidades = receta.getUnidad();

			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			Iterator<String> iteradorU = unidades.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();
			String unidad = iteradorU.next();
			s.execute (
					"INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '1', '" + peso + "', '" 
						+ unidad + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				unidad = iteradorU.next();
				s.execute (
						"INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '0', '" + peso + "', '"
							+ unidad + "');");
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void validarReceta (String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute(
					"UPDATE receta SET validada='1' WHERE id='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void desvalidarReceta (String idReceta) {

		try {
			Statement s = conexion.createStatement();
			s.execute(
					"UPDATE receta SET validada='0' WHERE id='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarReceta (String idReceta) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"DELETE FROM receta WHERE id = '" + idReceta + "';");
			s.execute(
					"DELETE FROM componente WHERE idReceta = '" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public RecetaVO devolverReceta (String idReceta) {
		
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
			
			ArrayList<String> ingredientes = new ArrayList<String>();
			ArrayList<String> peso = new ArrayList<String>();
			ArrayList<String> unidades = new ArrayList<String>();
			rs = s.executeQuery("SELECT * FROM componente WHERE idReceta='" + idReceta + "';");
						
			while(rs.next()) {
				if (rs.getString("esPrincipal").equals("1")) {
					ingredientes.add(0, rs.getString("ingrediente"));
					peso.add(0, rs.getString("peso"));
					unidades.add(0, rs.getString("unidad"));
				}
				else {
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
	
	public boolean existeReceta (String idReceta) {
		
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT id FROM receta WHERE id='" + idReceta + "';");
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
	
	public ArrayList<RecetaVO> obtenerNoValidadas () {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<RecetaVO>();
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
	
	public ArrayList<RecetaVO> obtenerValidadas () {

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

	public ArrayList<RecetaVO> buscarPorIngrediente(String nombre) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
					+ nombre + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.validada='1';");

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

	public ArrayList<RecetaVO> buscarPorNombre(String nombre) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs;

			if (nombre == null || nombre.equals("")) {
				rs =  s.executeQuery("SELECT * FROM receta;");
			} else {
				rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') " +
						"AND validada='1';");
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

	public ArrayList<RecetaVO> buscarPorNPersonas(String nPersonas) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
			RecetaVO receta;
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "' " +
					"AND validada='1';");

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

	public ArrayList<RecetaVO> busquedaAvanzada (String nombre, String nPersonas, String ingrediente, String plato) {

		try {
			ArrayList<RecetaVO> recetas = new ArrayList<>();
            RecetaVO receta;
            Statement s = conexion.createStatement();
            ResultSet rs;

            if (nombre == null || nombre.equals("")) {
				if (nPersonas == null || nPersonas.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND Plato='" + plato + "' " +
							"AND r.validada='1';");
				} else if (ingrediente == null || ingrediente.equals("")) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "' " +
							"AND Plato='" + plato + "' AND validada='1';");
				} else if (plato == null || plato.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" +
							nPersonas + "' AND r.validada='1';");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" +
							nPersonas + "' AND Plato='" + plato + "' AND r.validada='1';");
				}
            } else if (nPersonas == null || nPersonas.equals("")) {
				if (nombre == null || nombre.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND Plato='" + plato + "'" +
							"AND r.validada='1';");
				} else if (ingrediente == null || ingrediente.equals("")) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%')" +
							" AND Plato='" + plato + "' AND validada='1';");
				} else if (plato == null || plato.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND r.validada='1';");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND Plato='" + plato + "' " +
							"AND r.validada='1';");
				}
            } else if (plato == null || plato.equals("")) {
				if (nombre == null || nombre.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id " +
							"AND nPerosnas='" + nPersonas + "' AND r.validada='1';");
				} else if (ingrediente == null || ingrediente.equals("")) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') " +
							"AND nPerosnas='" + nPersonas + "' AND validada='1';");
				} else if (nPersonas == null || nPersonas.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND r.validada='1';");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND nPerosnas='" + nPersonas + "' " +
							"AND r.validada='1';");
				}
			} else {
				if (nombre == null || nombre.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas +"' " +
							"AND Plato='" + plato + "' AND validada='1';");
				} else if (nPersonas == null || nPersonas.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							 "Plato='" + plato + "' AND validada='1';");
				} else if (plato == null || plato.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							"numeroPersonas='" + nPersonas +"' AND validada='1';");
				} else {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							"numeroPersonas='" + nPersonas +"' AND Plato='" + plato + "' AND validada='1';");
				}
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

	public ArrayList<RecetaVO> menuDelDia () {

		Random random = new Random();
		ArrayList<RecetaVO> recetas = new ArrayList<>();

		ArrayList<RecetaVO> aux = buscarPorPlato("Entrante");
		int indice = random.nextInt(aux.size() - 1);
		RecetaVO receta = aux.get(indice);
		ArrayList<String> ingredientes = receta.getIngredientes();
		String ingredienteE = ingredientes.get(0);
		recetas.add(receta);
				
		recetas.add(receta);
		aux = buscarPorPlato("Primero");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredienteP = ingredientes.get(0);
		while (ingredienteP.equals(ingredienteE)) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredienteP = ingredientes.get(0);
		}
		recetas.add(receta);
		
		aux = buscarPorPlato("Segundo");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredienteS = ingredientes.get(0);
		while (ingredienteS.equals(ingredienteE) || ingredienteS.equals(ingredienteP)) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredienteS = ingredientes.get(0);
		}
		recetas.add(receta);

		aux = buscarPorPlato("Postre");
		indice = random.nextInt(aux.size() - 1);
		receta = aux.get(indice);
		ingredientes = receta.getIngredientes();
		String ingredientePo = ingredientes.get(0);
		while (ingredientePo.equals(ingredienteE) || ingredientePo.equals(ingredienteP) || ingredientePo.equals(ingredienteS)) {
			indice = random.nextInt(aux.size() - 1);
			receta = aux.get(indice);
			ingredientes = receta.getIngredientes();
			ingredientePo = ingredientes.get(0);
		}
		recetas.add(receta);

		return recetas;
	}
}

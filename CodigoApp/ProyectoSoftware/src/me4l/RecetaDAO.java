package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

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
			
			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();			
			s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "');");
			
			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();			
				s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "');");
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

			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();
			s.execute (
					"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '1', '" + peso + "');");

			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();
				s.execute (
						"INSERT INTO componente VALUES ('" + id + "', '" + ingrediente + "', '0', '" + peso + "');");
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
			
			Iterator<String> iteradorI = ingredientes.iterator();
			Iterator<String> iteradorP = pesos.iterator();
			String ingrediente = iteradorI.next();
			String peso = iteradorP.next();			
			s.execute (
					"INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '1', '" + peso + "');");
			
			while (iteradorI.hasNext()) {
				ingrediente = iteradorI.next();
				peso = iteradorP.next();			
				s.execute (
					"INSERT INTO componente VALUES ('" + idReceta + "', '" + ingrediente + "', '0', '" + peso + "');");
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
			rs = s.executeQuery("SELECT * FROM componente WHERE idReceta='" + idReceta + "';");
						
			while(rs.next()) {
				if (rs.getString("esPrincipal").equals("1")) {
					ingredientes.add(0, rs.getString("ingrediente"));
					peso.add(0, rs.getString("peso"));
				}
				else {
					ingredientes.add(rs.getString("ingrediente"));
					peso.add(rs.getString("peso"));
				}
			}
			
			receta.setIngredientes(ingredientes);
			receta.setPesoIngredientes(peso);
			
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
				rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%');");
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
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "';");

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
			ResultSet rs = s.executeQuery("SELECT * FROM receta WHERE Plato='" + plato + "';");

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
				if (nPersonas == null) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND Plato='" + plato + "';");
				} else if (ingrediente == null) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas + "' " +
							"AND Plato='" + plato + "';");
				} else if (plato == null) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" +
							nPersonas + "';");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND r.numeroPersonas='" +
							nPersonas + "' AND Plato='" + plato + "';");
				}
            } else if (nPersonas == null) {
				if (nombre == null || nombre.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND Plato='" + plato + "';");
				} else if (ingrediente == null) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(r.nombre) LIKE UPPER('%" + nombre + "%')" +
							" AND Plato='" + plato + "';");
				} else if (plato == null) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%');");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND Plato='" + plato + "';");
				}
            } else if (plato == null) {
				if (nombre == null || nombre.equals("")) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id " +
							"AND nPerosnas='" + nPersonas + "';");
				} else if (ingrediente == null) {
					rs =  s.executeQuery("SELECT * FROM receta WHERE UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') " +
							"AND nPerosnas='" + nPersonas + "';");
				} else if (nPersonas == null) {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%');");
				} else {
					rs =  s.executeQuery("SELECT r.* FROM receta r, componente c WHERE c.ingrediente='"
							+ ingrediente + "' AND c.esPrincipal='1' AND c.idReceta=r.id AND " +
							"UPPER(r.nombre) LIKE UPPER('%" + nombre + "%') AND nPerosnas='" + nPersonas + "';");
				}
			} else {
				if (nombre == null || nombre.equals("")) {
					rs = s.executeQuery("SELECT * FROM receta WHERE numeroPersonas='" + nPersonas +"' " +
							"AND Plato='" + plato + "';");
				} else if (nPersonas == null) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							 "Plato='" + plato + "';");
				} else if (plato == null) {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							"numeroPersonas='" + nPersonas +"';");
				} else {
					rs = s.executeQuery("SELECT * FROM receta WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%') AND " +
							"numeroPersonas='" + nPersonas +"' AND Plato='" + plato + "';");
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
}

package app;

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PuntuacionDAO {
	
	private Connection conexion;
	
	public PuntuacionDAO (Connection conexion) {
		this.conexion = conexion;
	}
	
	public void addPuntuacion (PuntuacionVO puntuacion) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"INSERT INTO puntuacion VALUES ('" + puntuacion.getIdReceta() + "', '"
					+ puntuacion.getIp() + "', '" + puntuacion.getPuntos() + "');");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarPuntuacion (String puntos) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"UPDATE puntuacion SET puntos='" + puntos + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarPuntuacion (String idReceta, String ip) {
		
		try {
			Statement s = conexion.createStatement();
			s.execute(
					"DELETE FROM puntuacion WHERE ip='" + ip + "' AND idReceta ='" + idReceta + "';");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PuntuacionVO devolverPuntuacion (String idReceta, String ip) {
		
		try {
			PuntuacionVO puntuacion = new PuntuacionVO();
			puntuacion.setIdReceta(idReceta);
			puntuacion.setIp(ip);
			
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM puntuacion WHERE ip='" + ip + "' AND idReceta='" + idReceta + "';");
			
			rs.next();
			puntuacion.setPuntos(rs.getString("puntos"));
			
			return puntuacion;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean existePuntuacion (String idReceta, String ip) {
		
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM puntuacion WHERE ip='" + ip + "' AND idReceta='" + idReceta + "';");
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
	
}

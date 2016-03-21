package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String servidor = "jdbc:mysql://127.0.0.1:3306/webmusica";
	private static String user = "arqSoftware";
	private static String pass = "webmusica12345";
	private static String driver = "com.mysql.jdbc.Driver";
	private static Connection conexion;

	public Conexion() throws SQLException, ClassNotFoundException {
		Class.forName(driver); // Levanto el Driver
		conexion = DriverManager.getConnection(servidor, user, pass); // Establezco
																		// conexion
	}

	public Connection getConnection() {
		return conexion;
	}

}

package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

//	private static String servidor = "jdbc:mysql://127.0.0.1:3306/me4l";
//	private static String user = "proySoftware";
//	private static String pass = "me4l1234";
	private static String servidor = "jdbc:mysql://81.32.239.119:8100/meal";
	private static String user = "root";
	private static String pass = "root";
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

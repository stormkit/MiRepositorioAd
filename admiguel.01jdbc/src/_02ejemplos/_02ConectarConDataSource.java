package _02ejemplos;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class _02ConectarConDataSource {

	static String DRIVER = "com.mysql.jdbc.Driver";
	static String URL = "jdbc:mysql://localhost:3306/colegio";
	static String USR = "root";
	static String PWD = "mysql";

	public static void main(String[] args) {
		Connection con = null;
		// Creamos datasource y configuramos sus parámetros.
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		ds.setUsername(USR);
		ds.setPassword(PWD);
		// Realizamos la conexión
		try {
			con = ds.getConnection();
			System.out.println("Conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed())
					con.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

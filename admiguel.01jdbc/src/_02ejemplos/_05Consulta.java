package _02ejemplos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class _05Consulta {
	static String DRIVER = "com.mysql.jdbc.Driver";
	static String URL = "jdbc:mysql://localhost:3306/ciclismo";
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
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			System.out.println("Conectado");
			
			st = con.createStatement();
			rs = st.executeQuery("select * from ciclista");
			
			//mostramos el nombre y el dorsal del último ciclista de la tabla
			rs.last();
			System.out.println(rs.getString("nombre"));
			System.out.println(rs.getInt("dorsal"));
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()){
					rs.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (st != null && !st.isClosed()){
					st.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
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

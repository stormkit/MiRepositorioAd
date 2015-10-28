package _03ejercicios;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _03ConexionPropiedadesParciales {
	public static void main(String[] args) {
		Connection con = null;
		
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("configuracion\\propiedadesParciales.txt"));
			
			//Usuario y contraseña
			Scanner tec = new Scanner(System.in);
			System.out.print("USUARIO: ");
			String usr = tec.nextLine();
			System.out.println("CONTRASEÑA: ");
			String pwd = tec.nextLine();
			
//			p.setProperty("username", usr);
//			p.setProperty("password", pwd);
			
			
			//--------------------------------
			
			BasicDataSource ds = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
			ds.setUsername(usr);
			ds.setPassword(pwd);
			
			
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

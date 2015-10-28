package _03ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _07NuevoEquipo {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement stmInsert = null;
		PreparedStatement stmUpdate = null;
			
		ResultSet rs = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("configuracion\\propiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(p);
			con = ds.getConnection();
			
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Nombre equipo a crear: ");
			String eq = tec.nextLine();
			System.out.println("Director del equipo a crear");
			String dir = tec.nextLine();
			System.out.println("Cambiar a los ciclistas del equipo: ");
			String eqCambiar = tec.nextLine();
			
			stmInsert = con.prepareStatement("INSERT INTO equipo (nomeq,director) VALUES (?,?)");
			stmUpdate = con.prepareStatement("UPDATE ciclista SET nomeq = ? WHERE nomeq = ?");
			
			//Insercion
			stmInsert.setString(1, eq);
			stmInsert.setString(2,dir);
			int inserciones = stmInsert.executeUpdate();
			System.out.println("Se han insertado " + inserciones + " equipos");
			
			//Actualización
			stmUpdate.setString(1,eq);
			stmUpdate.setString(2, eqCambiar);
			int cambios = stmUpdate.executeUpdate();
			System.out.println("Se han cambiado de equpo " + cambios + " ciclistas");
			
			

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stmInsert != null && !stmInsert.isClosed()){
					stmInsert.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(stmUpdate != null && !stmUpdate.isClosed()){
					stmUpdate.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(con  != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
			
			
		
		
	}
}

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

public class _06ConsultasSimplesPreparedStatement {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("configuracion\\propiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(p);
			con = ds.getConnection();
			
			Scanner tec = new Scanner(System.in);
			
//			//Apartado d
//			//Preparamos la consulta
//			stm = con.prepareStatement("SELECT dorsal, nombre, nomeq FROM ciclista WHERE nomeq = ?");
//			System.out.println("Indica nombre del equipo: ");
//			String nombreEquipo = tec.next();
//			//Pasamos parametros a la consulta
//			stm.setString(1, nombreEquipo);
//			//Ejecutamos la consulta
//			rs = stm.executeQuery();
		
			//Apartado e
			//Preparamos la consulta
			stm = con.prepareStatement("SELECT dorsal, nombre, nomeq FROM ciclista WHERE nomeq = ? and nombre LIKE ?");
			
			System.out.println("Indica nombre del equipo: ");
			String nombreEquipo = tec.next();
			System.out.println("Indica parte del nombre del ciclista: ");
			String nombreCicli = tec.next();

			//Pasamos parametros a la consulta
			stm.setString(1, nombreEquipo);
			stm.setString(2, "%"+nombreCicli+"%");
			
			//Ejecutamos la consulta
			rs = stm.executeQuery();
			
			//Mostramos resultados
			while(rs.next()){
				int d = rs.getInt("dorsal");  // o bien rs.getInt(1);
				String n = rs.getString("nombre");
				String e = rs.getString("nomeq");
				
				//System.out.println("Dorsal " + d + " - Nombre: " + n + " - Equipo: " + e);
				System.out.format("Dorsal %-5d Nombre %-20s Equipo %-15s %n",d,n,e);
				
			}
			
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
				if(stm != null && !stm.isClosed()){
					stm.close();
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

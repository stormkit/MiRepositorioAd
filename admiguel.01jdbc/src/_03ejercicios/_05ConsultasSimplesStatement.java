package _03ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _05ConsultasSimplesStatement {
	public static void main(String[] args) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("configuracion\\propiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(p);
			con = ds.getConnection();
			
			stm = con.createStatement();
//			//a
//			rs = stm.executeQuery("SELECT dorsal, nombre, nomeq FROM ciclista");
//			while(rs.next()){
//				int d = rs.getInt("dorsal");  // o bien rs.getInt(1);
//				String n = rs.getString("nombre");
//				String e = rs.getString("nomeq");
//				
//				//System.out.println("Dorsal " + d + " - Nombre: " + n + " - Equipo: " + e);
//				System.out.format("Dorsal %-5d Nombre %-20s Equipo %-15s %n",d,n,e);
//				
//			}
//			rs.close();
//			
//			//b
//			rs = stm.executeQuery("SELECT ciclista.dorsal, ciclista.nombre, equipo.director "
//					+ "FROM ciclista,equipo WHERE ciclista.nomeq = equipo.nomeq");
//			
//			while(rs.next()){
//				int d = rs.getInt("dorsal");  // o bien rs.getInt(1);
//				String n = rs.getString("nombre");
//				String dir = rs.getString("director");
//				
//				
//				System.out.format("Dorsal %-5d Nombre %-20s Director %-15s %n",d,n,dir);
//				
//			}
//			rs.close();
//			//c
//			rs = stm.executeQuery("SELECT dorsal, nombre, nomeq FROM ciclista WHERE nomeq = 'ONCE'");
//			while(rs.next()){
//				int d = rs.getInt("dorsal");  // o bien rs.getInt(1);
//				String n = rs.getString("nombre");
//				String e = rs.getString("nomeq");
//				
//				//System.out.println("Dorsal " + d + " - Nombre: " + n + " - Equipo: " + e);
//				System.out.format("Dorsal %-5d Nombre %-20s Equipo %-15s %n",d,n,e);
//				
//			}
//			rs.close();
			//d
			Scanner tec = new Scanner(System.in);
			System.out.println("Indica nombre del equipo: ");
			String nombreEquipo = tec.next();
			
			String sql = "SELECT dorsal, nombre, nomeq FROM ciclista WHERE nomeq = '" + nombreEquipo + "'";
			System.out.println(sql);
			rs = stm.executeQuery(sql);
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

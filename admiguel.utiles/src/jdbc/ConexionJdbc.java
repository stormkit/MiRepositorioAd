package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import excepciones.ConnectionException;

public class ConexionJdbc {
	private static Connection con = null;

	// lo hago static para poder conectar desde fuera
	public static Connection conectar(String ficheroPropiedades){
		
		Properties p = new Properties();
		try{
			p.load(new FileInputStream(ficheroPropiedades));
			DataSource ds = BasicDataSourceFactory.createDataSource(p);
						
			con =ds.getConnection();
			

		}catch(FileNotFoundException e){
			e.printStackTrace();
			throw new ConnectionException("Error al conectar");
		}catch(IOException e){
			e.printStackTrace();
			throw new ConnectionException("Error al conectar");
		}catch(SQLException e){
			e.printStackTrace();
			throw new ConnectionException("Error al conectar");
		}catch(Exception e){
			e.printStackTrace();
			throw new ConnectionException("Error al conectar");
		}
		return con;
	}
	
	//funciones para cerrar
	public static void cerrar(){
		try{
			if(con!=null && !con.isClosed())
				con.close();
			}catch(SQLException e){
				
			}
	}
	public static void cerrar(Statement o){
		try{
			if(o!=null && !o.isClosed())
				o.close();
			}catch(SQLException e){
				
			}
	}
	public static void cerrar(ResultSet o){
		try{
			if(o!=null && !o.isClosed())
				o.close();
			}catch(SQLException e){
				
			}
	}
	public static Connection getConnection(){
		return con;
	}
	
	public static void setAutoCommit(boolean a){
		try {
			con.setAutoCommit(a);
		} catch (SQLException e) {
		
		}
	}
	public static boolean getAutoCommit(){
		boolean a = false;
		try {
			a= con.getAutoCommit();
		} catch (SQLException e) {
		
		}
		return a;
	}
	public static void rollback(){
		try {
			con.rollback();
		} catch (SQLException e) {
			
		}
	}
	public static void commit(){
		try {
			con.commit();
		} catch (SQLException e) {
			
		}
	}
	
	
	
}

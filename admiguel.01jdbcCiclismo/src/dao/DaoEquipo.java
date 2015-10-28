package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Equipo;

public class DaoEquipo {

	public static void insertar(Equipo e) throws BusinessException {
		// TODO Auto-generated constructor stub
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("INSERT INTO equipo (nomeq, director) VALUES (?,?)");
			pstm.setString(1, e.getNomeq());
			pstm.setString(2, e.getDirector());
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede insertar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	public static void eliminar(Equipo e) throws BusinessException {
		// TODO Auto-generated constructor stub
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("DELETE FROM  equipo WHERE nomeq = ?");
			pstm.setString(1, e.getNomeq());
			
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede eliminar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

}

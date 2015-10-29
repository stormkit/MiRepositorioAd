package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Equipo;

public class DaoEquipo implements InterfaceDaoGenerico<Equipo, Integer>{

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
	
	/*c) En el paquete programas del proyecto crea los programas que se indican a
continuación. Los programas contendrán el interfaz de usuario y utilizarán las
clases DaoCiclista y/o DaoEquipo para acceder a la base de datos(consultar o
modificar):
i. _11NuevoEquipo, que cree un nuevo equipo con los datos que se
solicitan al usuario (nombre y director). 
*/

	@Override
	public void grabar(Equipo objeto) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		
		String sql="insert into equipo (nomeq, director) values (? , ?)";
		
		PreparedStatement pstm=null;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, objeto.getNomeq());
			pstm.setString(2, objeto.getDirector());
			pstm.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			throw new BusinessException("El equipo no se puede insertar");

		}finally {
			ConexionJdbc.cerrar(pstm);
		}

	}

	@Override
	public void actualizar(Equipo objeto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grabarOActualizar(Equipo objeto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Equipo objeto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Equipo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipo> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}

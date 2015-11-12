package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;
import pojos.Equipo;

public class DaoEquipo implements InterfaceDaoGenerico<Equipo, String> {
	@Override
	public void grabar(Equipo o) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("INSERT INTO equipo (nomeq, director) VALUES (?,?)");
			
			pstm.setString(1, o.getNomeq());
			pstm.setString(2, o.getDirector());
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede insertar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void actualizar(Equipo o) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("UPDATE ciclista SET director = ? WHERE nomeq LIKE ?");
			pstm.setString(1, o.getDirector());
			pstm.setString(2, o.getNomeq());
						
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede eliminar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void grabarOActualizar(Equipo o) throws BusinessException {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT count(*) FROM equipo WHERE nomeq=?");
			pstm.setString(1, o.getNomeq());
			rs = pstm.executeQuery();
			if(rs.first()) actualizar(o);
			else grabar(o);
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede grabar o actualizar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void borrar(Equipo o) throws BusinessException {
		borrar(o.getNomeq());
	}

	@Override
	public void borrar(String id) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("DELETE FROM  equipo WHERE nomeq LIKE ?");
			pstm.setString(1, id);
			
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El equipo no se puede eliminar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public Equipo buscarPorId(String id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Equipo e = null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM equipo WHERE nomeq LIKE ?");
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if(rs.first()) {
				e = new Equipo(id,rs.getString("director"));
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar equipo");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return e;
	}

	@Override
	public List<Equipo> buscarTodos() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Equipo e = null;
		List<Equipo> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM equipo");
			rs = pstm.executeQuery();
			while(rs.next()) {
				e = new Equipo(rs.getString("nomeq"),rs.getString("director"));
				l.add(e);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar equipo");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	
	// Usando otros métodos ya implementados
	public void borrarEquipoCompleto(Equipo o) throws BusinessException {
		
		Connection con = ConexionJdbc.getConnection();
		boolean autoCommitAntes = ConexionJdbc.getAutoCommit();
		DaoCiclista daoC =  new DaoCiclista();
		
		try{
			ConexionJdbc.setAutoCommit(false);
			List<Ciclista> l = daoC.getCiclistasPorEquipo(o.getNomeq());
			for(Ciclista c: l) daoC.borrar(c);
			borrar(o);
			ConexionJdbc.commit();
			
		} catch(BusinessException ex){
			ConexionJdbc.rollback();
			//throw new BusinessException("No se puede borrar equipo completo");
			throw ex;
		} finally{
			ConexionJdbc.setAutoCommit(autoCommitAntes);
		}
	}
	
	//Usando sentencias de SQL
	public void borrarEquipoCompleto2(Equipo o) throws BusinessException {
		
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstmE = null;
		PreparedStatement pstmC = null;
		boolean autoCommitAntes = ConexionJdbc.getAutoCommit();
		DaoCiclista daoC =  new DaoCiclista();
		
		try{
					
			pstmC = con.prepareStatement("DELETE FROM ciclista WHERE nomeq LIKE ?");
			pstmC.setString(1, o.getNomeq());
			pstmE = con.prepareStatement("DELETE FROM equipo WHERE nomeq LIKE ?");
			pstmE.setString(1, o.getNomeq());
			
			ConexionJdbc.setAutoCommit(false); //Iniciar transaccion
			//borrar (primero ciclistas y luego el equipo
			pstmC.executeUpdate();
			pstmE.executeUpdate();
			
			ConexionJdbc.commit(); //Terminar transaccion
			
		} catch(SQLException ex){
			ConexionJdbc.rollback(); //Terminar transaccion
			throw new BusinessException("No se puede borrar equipo completo");
		} finally{
			ConexionJdbc.setAutoCommit(autoCommitAntes);
		}
	}
	
	public List<Equipo> getEquiposPorNombreYDirector(String textoNombre, String textoDirector) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Equipo e = null;
		List<Equipo> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			if(textoNombre == null){
				if(textoDirector == null){
					pstm = con.prepareStatement("SELECT * FROM equipo");
				} else {
					pstm = con.prepareStatement("SELECT * FROM equipo WHERE director LIKE ?");
					pstm.setString(1, "%"+textoDirector+"%");
				}
			} else {
				if(textoDirector == null){
					pstm = con.prepareStatement("SELECT * FROM equipo WHERE nomeq LIKE ?");
					pstm.setString(1, "%"+textoNombre+"%");
				} else {
					pstm = con.prepareStatement("SELECT * FROM equipo WHERE nomeq LIKE ? AND director LIKE ?");
					pstm.setString(1, "%"+textoNombre+"%");
					pstm.setString(2, "%"+textoDirector+"%");
				}
			}
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				e = new Equipo(rs.getString("nomeq"),rs.getString("director"));
				l.add(e);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar equipo");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	public List<Equipo> getEquiposPorNombreYDirector2(String textoNombre, String textoDirector) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Equipo e = null;
		List<Equipo> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			String sql = "SELECT * FROM equipo WHERE 1=1";
			if(textoNombre != null){
				sql += " AND nomeq LIKE ?";
			}
			if(textoDirector != null){
				sql += "AND director LIKE ?";
			}
			pstm = con.prepareStatement(sql);
			
			int numParam = 1;
			if(textoNombre != null){
				pstm.setString( numParam,"%"+textoNombre+"%" );
				numParam++;
			}
			if(textoDirector != null){
				pstm.setString( numParam ,"%"+textoDirector+"%" );
			}
			
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				e = new Equipo(rs.getString("nomeq"),rs.getString("director"));
				l.add(e);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar equipo");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	public void anyadirEquipo(Equipo e, List<Ciclista> miembros) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		boolean autoCommitAntes = ConexionJdbc.getAutoCommit();
		DaoCiclista daoC = new DaoCiclista();
		
		try{
			ConexionJdbc.setAutoCommit(false); //Inicio transaccion
			
			//insertar el equipo
			grabar(e); 
			
			//Insertar los ciclistas
			for(int i = 0; i<miembros.size(); i++){
				Ciclista c = miembros.get(i);
				daoC.grabar(c);
			}
//			for(Ciclista c: miembros) {
//				daoC.grabar(c);
//			}
			
			ConexionJdbc.commit(); //Confirmar transaccion
		} catch(BusinessException ex){
			ConexionJdbc.rollback(); //Deshacer cambios
			throw ex;
		} finally{
			ConexionJdbc.setAutoCommit(autoCommitAntes);
		}
		
	}
	
	
} 

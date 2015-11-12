package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Etapa;

public class DaoEtapa implements InterfaceDaoGenerico <Etapa, Integer>{

	@Override
	public void grabar(Etapa objeto) throws BusinessException {
		
	}

	@Override
	public void actualizar(Etapa objeto) throws BusinessException {
		
		Connection con=ConexionJdbc.getConnection();
		
		String sql="UPDATE etapa SET km = ?, salida = ?, llegada = ?, dorsal = ? WHERE netapa = ?";
		
		PreparedStatement pstm=null;
		try {
			
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, objeto.getKm());
			pstm.setString(2, objeto.getSalida());
			pstm.setString(3, objeto.getLlegada());
			pstm.setInt(4, objeto.getDorsal());
			pstm.setInt(5, objeto.getNetapa());
			
			pstm.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException ("La actualización no ha sido posible");
		}
		finally{
			
			ConexionJdbc.cerrar(pstm);
		}
		
		
	}

	@Override
	public void grabarOActualizar(Etapa objeto) throws BusinessException {
		
	}

	@Override
	public void borrar(Etapa objeto) throws BusinessException {
		
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		
	}

	@Override
	public Etapa buscarPorId(Integer id) {
		
		Etapa e=null;
		
		Connection con=ConexionJdbc.getConnection();
		
		String sql="select * from etapa where netapa=?";
		
		ResultSet rs=null;	
		PreparedStatement pstm=null;
		
		try {
			
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			rs=pstm.executeQuery();
			
		if(rs.first()){
			
				e=null;
				
			}else  {
				
					
		    	e=new Etapa(rs.getInt("netapa"), rs.getInt("km"), rs.getString("salida"), rs.getString("llegada"), rs.getInt("dorsal"));
				
				
				}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Error al buscar Etapa");
			
		}finally{
			
			ConexionJdbc.cerrar(pstm);
		}
		
		return e;
	}

	@Override
	public List<Etapa> buscarTodos() {
		return null;
	}
	
	public List<Etapa> buscar(String salida, String llegada, Integer desdeKm, Integer hastaKm){
		
		List<Etapa> l=new ArrayList<>();
		PreparedStatement pstm=null;

		try {
			
		Connection con=ConexionJdbc.getConnection();
		
		ResultSet rs=null;
		
        String sql="Select * from etapa where 1=1";
		
		if(salida != null){
			
			sql += " and salida like ?";
		}
		
		if(llegada != null){
			
			sql += " and llegada like ?";
		}
		if(desdeKm != null){
			
			sql += " and km <= ?";
		}
        if(hastaKm != null){
			
			sql += " and km <= ?";
		}
		
		int numParam=1;
		
		if(salida != null){
			
			
				
	     pstm.setString(numParam, "%"+salida+"%");
			numParam++;
			
		}
		
        if(llegada != null){
			
				pstm.setString(numParam, "%"+llegada+"%");
				numParam++;
			
		}
        
        if(desdeKm != null){
			
			pstm.setInt(numParam, desdeKm);
			numParam++;
		
     	}
        
        if(hastaKm != null){
			
    			pstm.setInt(numParam, hastaKm);
    			numParam++;
    		
    	}
	
        
		pstm=con.prepareStatement(sql);
		rs=pstm.executeQuery();
		Etapa e=null;
			 
		  while (rs.next()){
					
					e=new Etapa(rs.getInt("netapa"),rs.getInt("km"), rs.getString("salida"),rs.getString("llegada"), rs.getInt("dorsal"));
					l.add(e);
					
				}
			
		}catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException ("No se han podido añadir las etapas");
			
		}finally {
			
		ConexionJdbc.cerrar(pstm);
		}
		
		return l;	

	
	}
	
	public void insertar(List<Etapa>l)throws BusinessException{
		
		
		Etapa e=null;
		
		PreparedStatement pstm=null;
		
		ResultSet rs=null;
		
		String sql="insert into etapa(km, salida, llegada, dorsal)values(?,?,?,?)";
		
		boolean valorAutocomitInicio=ConexionJdbc.getAutoCommit();
		ConexionJdbc.setAutoCommit(false);
		
		try{
			
		Connection con=ConexionJdbc.getConnection();
		int registro=0;
		for (int i=0;i<l.size();i++){
			
			e=new Etapa(null, l.get(registro).getKm(), l.get(registro).getSalida(),l.get(registro).getLlegada(),l.get(registro).getDorsal());
			pstm=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			if(pstm.executeUpdate() != 0){
					
				rs= pstm.getGeneratedKeys();
				rs.first();
				int netapa = rs.getInt(1);
				e.setNetapa(netapa);
				pstm.setInt(1, e.getKm());
				pstm.setString(2, e.getSalida());
				pstm.setString(3, e.getLlegada());
				pstm.setInt(1, e.getDorsal());
			
			}

           pstm.executeUpdate();
           registro++;


		}
		
		ConexionJdbc.commit();
		
	} catch (SQLException ex) {
		
		ConexionJdbc.rollback();
		
		ex.printStackTrace();
		throw new BusinessException ("La inserción de las etapas  no ha sido posible");
	}
		
	finally{
		
		ConexionJdbc.setAutoCommit(valorAutocomitInicio);

		ConexionJdbc.cerrar(pstm);
	}
		
		
	}
}


package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;
import pojos.Equipo;

public class DaoCiclista implements InterfaceDaoGenerico<Ciclista, Integer> {
	@Override
	public void grabar(Ciclista o) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("INSERT INTO ciclista (nombre, nomeq, nacimiento) VALUES (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, o.getNombre());
			pstm.setString(2, o.getNomeq());
			pstm.setDate(3, new java.sql.Date(o.getNacimiento().getTime()));

			if(pstm.executeUpdate()!=0) {
				//Obtenemos la clave generada
				ResultSet claves = pstm.getGeneratedKeys();
				claves.first();
				int dorsal = claves.getInt(1);
				//Guardamos el dorsal en el pojo
				o.setDorsal(dorsal);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El ciclista no se puede insertar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void actualizar(Ciclista o) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("UPDATE ciclista SET nombre = ?, nomeq = ?, nacimiento = ? WHERE dorsal = ?");
			pstm.setString(1, o.getNombre());
			pstm.setString(2, o.getNomeq());
			pstm.setDate(3, new java.sql.Date(o.getNacimiento().getTime()));
			pstm.setInt(4, o.getDorsal());
			
			
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El ciclista no se puede eliminar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void grabarOActualizar(Ciclista o) throws BusinessException {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT count(*) FROM ciclista WHERE dorsal=?");
			pstm.setInt(1, o.getDorsal());
			rs = pstm.executeQuery();
			if(rs.first()) actualizar(o);
			else grabar(o);
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El ciclista no se puede grabar o actualizar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void borrar(Ciclista o) throws BusinessException {
		borrar(o.getDorsal());
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		PreparedStatement pstm =null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("DELETE FROM  ciclista WHERE dorsal = ?");
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new BusinessException("El ciclista no se puede eliminar");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public Ciclista buscarPorId(Integer id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Ciclista c = null;
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM ciclista WHERE dorsal=?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.first()) {
				c = new Ciclista(id,rs.getString("nombre"),rs.getString("nomeq"),rs.getDate("nacimiento"));
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar ciclista");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return c;
	}

	@Override
	public List<Ciclista> buscarTodos() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Ciclista c = null;
		List<Ciclista> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM ciclista");
			rs = pstm.executeQuery();
			while(rs.next()) {
				c = new Ciclista(rs.getInt("dorsal"),rs.getString("nombre"),rs.getString("nomeq"),rs.getDate("nacimiento"));
				l.add(c);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar ciclista");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	public List<Ciclista> getCiclistasPorNombre(String texto) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Ciclista c = null;
		List<Ciclista> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM ciclista WHERE nombre LIKE ?");
			pstm.setString(1, "%"+texto+"%");
			rs = pstm.executeQuery();
			while(rs.next()) {
				c = new Ciclista(rs.getInt("dorsal"),rs.getString("nombre"),rs.getString("nomeq"),rs.getDate("nacimiento"));
				l.add(c);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar ciclista");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	public List<Ciclista> getCiclistasPorEquipo(String nombreEquipo) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Ciclista c = null;
		List<Ciclista> l = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		try{
			pstm = con.prepareStatement("SELECT * FROM ciclista WHERE nomeq LIKE ?",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			pstm.setString(1, nombreEquipo);
			rs = pstm.executeQuery();
			while(rs.next()) {
				c = new Ciclista(rs.getInt("dorsal"),rs.getString("nombre"),rs.getString("nomeq"),rs.getDate("nacimiento"));
				l.add(c);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new RuntimeException("Error al buscar ciclista");
		}finally{
			ConexionJdbc.cerrar(pstm);
		}
		return l;
	}
	public void borrarCiclistasDeEquipo(Equipo eq) throws BusinessException {
		PreparedStatement pstm = null;
		Connection con = ConexionJdbc.getConnection();
		boolean autoCommitAntes = false;
		try{
			autoCommitAntes = ConexionJdbc.getAutoCommit(); //GUARDO EL ESTADO DE AUTOCOMMIT
			ConexionJdbc.setAutoCommit(false); //COMIENZA LA TRANSACCION
			
			pstm = con.prepareStatement("DELETE FROM ciclista WHERE nomeq LIKE ?");
			pstm.setString(1, eq.getNomeq());
			pstm.executeUpdate();
			
			ConexionJdbc.commit(); //FIN DE LA TRANSACCION
		}catch(SQLException e){
			ConexionJdbc.rollback(); //FIN DE LA TRANSACCION
			e.printStackTrace();
			throw new BusinessException("No se han podido borrar todos los ciclistas");
		} finally {
			
			ConexionJdbc.setAutoCommit(autoCommitAntes); //RESTAURO ESTADO DE AUTOCOMMIT
			
			ConexionJdbc.cerrar();
		}
		
	}
	
	public List<Ciclista> getCiclistasPorNombreYNacimiento(String textoNombre, Date fechaI, Date fechaF){
		
		List<Ciclista>l=new ArrayList<>();;

		try{
			
		
		Connection con=ConexionJdbc.getConnection();
		Boolean autocomit=ConexionJdbc.getAutoCommit();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		ConexionJdbc.setAutoCommit(false);
		String sql="Select * from ciclista where 1=1";
		
		if(textoNombre != null){
			
			sql += " and nombre like ?";
		}
		
		if(fechaI != null){
			
			sql += " and nacimiento >= ?";
		}
		if(fechaF != null){
			
			sql += " and nacimiento <= ?";
		}
		
		int numParam=1;
		if(textoNombre != null){
			
			
				
	    pstm.setString(numParam, "%"+textoNombre+"%");
			
			
		}
		
        if(textoNombre != null){
			
				pstm.setString(numParam, textoNombre);
				numParam++;
			
		}
        if(fechaI != null){
			
			pstm.setDate(numParam, (Date) fechaI);
			numParam++;
		
	}
        if(fechaF != null){
			
    			pstm.setDate(numParam, (Date) fechaF);
    			numParam++;
    		
    	}
	
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			
            Ciclista c=null;
			while (rs.next()){
				c=new Ciclista(rs.getInt("dorsal"),rs.getString("nombre"), rs.getString("nomeq"),rs.getDate("nacimiento"));
				l.add(c);
				
			}
			
			ConexionJdbc.commit();
		} catch (SQLException e) {

			ConexionJdbc.rollback();
			e.printStackTrace();
			throw new RuntimeException ("No se han podido añadir los ciclistas");
		}
		
		
		
		
		
		return l;
	}
}

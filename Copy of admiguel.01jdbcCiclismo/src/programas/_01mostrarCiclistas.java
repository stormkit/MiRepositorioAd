package programas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConexionJdbc; //importo el proyecto ad.utiles.  [btn derecho sobre el proyecto / propiedades / proyectos / add]

public class _01mostrarCiclistas {

	public static void main(String[] args) {
		
		//conectar
		Connection con = ConexionJdbc.conectar("configuracion//propiedadesCiclismo.txt");
		
		//Mostrar ciclistas
		ResultSet rs = null;
		Statement  stm = null;
		try{
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT dorsal, nombre, nomeq FROM ciclista");
			while(rs.next()){
				int dor = rs.getInt("dorsal"); // o bien rs.getInt(1); (columna 1) devuelve el dato que haya en esa columna
				String nom = rs.getString("nombre");
				String equ = rs.getString("nomeq");
			
			//System.out.println("Dorsal "+ dor + " - Nombre "+ nom +" - Equipo: "+ equ);
			System.out.format("Dorsal: %-5d Nombre: %-20s Equipo: %-15s %n",dor,nom,equ); // % -> valor // %(numero)-> espacios a ocupar // %n -> salto de linea
			System.out.println("---------------------");
		
			}
			}catch (SQLException e1){
				e1.printStackTrace();
			}finally{
				//cerrar	
				ConexionJdbc.cerrar(rs);
				ConexionJdbc.cerrar(stm);
				ConexionJdbc.cerrar();
			}
	}
}

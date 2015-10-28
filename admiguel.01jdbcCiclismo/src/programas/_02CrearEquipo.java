package programas;

import dao.DaoEquipo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Equipo;

public class _02CrearEquipo {

	public static void main(String[] args) {
		ConexionJdbc.conectar("configuracion//propiedadesCiclismo.txt");
		
		Equipo progresa = new Equipo("progresa","Javier");
		try {
			DaoEquipo.insertar(progresa);
		} catch (BusinessException e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
		} finally {
			ConexionJdbc.cerrar();
		}
	
	}
}

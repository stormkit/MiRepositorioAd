package programas;

import java.util.Scanner;

import dao.DaoEquipo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Equipo;

public class _03BorrarEquipo {

	public static void main(String[] args) {
		ConexionJdbc.conectar("configuracion//propiedadesCiclismo.txt");
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce nombre equipo a borrar: ");
		String nomeq = tec.nextLine();
		
		Equipo progresa = new Equipo(nomeq, null);
		try {
			DaoEquipo.eliminar(progresa);
		} catch (BusinessException e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
		} finally {
			ConexionJdbc.cerrar();
		}
	
	}
}

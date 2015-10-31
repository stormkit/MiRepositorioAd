package programas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import dao.DaoEquipo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Equipo;

public class _11NuevoEquipo {

	public static void main(String[] args) {

		/*i. _11NuevoEquipo, que cree un nuevo equipo con los datos que se
solicitan al usuario (nombre y director). */
		
		Scanner tec=new Scanner(System.in);
		System.out.println("Inroduzca el nombre del nuevo equipo a crear");
		String nombreEquipo=tec.nextLine();
		System.out.println("Introduzca el nombre del director del nuevo equipo a crear");
		String nombreDirector=tec.nextLine();
		tec.close();
		
		Equipo e1=new Equipo(nombreEquipo, nombreDirector);
		
		ConexionJdbc.conectar("configuracion//propiedadesCiclismo.txt");

		DaoEquipo dao=new DaoEquipo();
		
		try {
			
			dao.grabar(e1);
			
			System.out.println("EQUIPO CREADO CORRECTAMENTE");
			System.out.println("El equipo se ha creado con nombre: " + e1.getNomeq() + " y " + e1.getDirector());
		} catch (BusinessException e) {
			
			System.out.println("ERROR");
			System.out.println(e.getMessage());
			
		} finally {
			
			ConexionJdbc.cerrar();
		}
		
	}
	
	

}

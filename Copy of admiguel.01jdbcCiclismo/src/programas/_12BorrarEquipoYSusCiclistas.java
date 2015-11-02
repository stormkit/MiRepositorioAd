package programas;

import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import dao.DaoEquipo;
import excepciones.BusinessException;
import pojos.Ciclista;
import pojos.Equipo;

public class _12BorrarEquipoYSusCiclistas {

	public static void main(String[] args) {

		String nombreEquipo=null;
		Scanner lector=new Scanner(System.in);
		System.out.println("Introduzca nombre del equipo a eliminar");
		nombreEquipo=lector.nextLine();
		lector.close();
		
		Equipo e=new Equipo(nombreEquipo, null);
		DaoEquipo dao=new DaoEquipo();
		
		List <Ciclista>l=dao.buscarTodos(e);
		
		System.out.println("Se van a borrar los siguientes ciclistas del equipo " + nombreEquipo);
		
		DaoCiclista dao2=new DaoCiclista();;
        for (Ciclista c : l){
        	
        	System.out.println(c.toString());
        	try {
				dao2.borrar(c);
				System.out.println(c.getNombre()+"Borrado");
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }
	
        try {
			dao.borrar(e);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}

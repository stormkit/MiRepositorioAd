package programas;

import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _09MostrarCiclistasPorNombre {
	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce parte del nombre del ciclista: ");
		String nombre = tec.nextLine();

		ConexionJdbc.conectar("configuracion\\propiedadesciclismo.txt");
		DaoCiclista dao = new DaoCiclista();

		// Recupero ciclistas por nombre
		List<Ciclista> l = dao.getCiclistasPorNombre(nombre);
		if (l.size() == 0) System.out.println("No hay ciclistas con el nombre indicado");
		else {
			for(Ciclista c: l){
				System.out.println(c.toString());
				//System.out.println(c.getDorsal() + " " + c.getNombre());
			}
		}

		ConexionJdbc.cerrar();
	}
}

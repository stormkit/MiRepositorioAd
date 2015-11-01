package programas;

import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _10BorrarTodosDeEquipo {
	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce nombre de equipo a vaciar: ");
		String nombre = tec.nextLine();

		ConexionJdbc.conectar("configuracion\\propiedadesciclismo.txt");
		DaoCiclista dao = new DaoCiclista();

		// Recupero ciclistas por nombre
		List<Ciclista> l = dao.getCiclistasPorEquipo(nombre);
		if (l.size() == 0)
			System.out.println("No hay ciclistas en el equipo indicado");
		else {
			for (Ciclista c : l) {
				try {
					dao.borrar(c);
					System.out.println("Borrado ciclista " + c.getDorsal());
				} catch (BusinessException e) {
					System.out.println("No se puede borrar el ciclista " + c.getDorsal());
				}

			}

//			try {
//				for (Ciclista c : l) {
//					dao.borrar(c);
//					System.out.println("Borrado ciclista " + c.getDorsal());
//				}
//			} catch (BusinessException e) {
//				System.out.println("No se pudo borrar algun ciclista");
//			}

		}

		ConexionJdbc.cerrar();
	}
}

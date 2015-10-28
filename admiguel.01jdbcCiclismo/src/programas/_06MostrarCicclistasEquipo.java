package programas;

import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _06MostrarCicclistasEquipo {
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Qué equipo quieres mostrar: ");
		String equipo = tec.nextLine();
		
		ConexionJdbc.conectar("configuracion\\propiedadesciclismo.txt");
		DaoCiclista dao = new DaoCiclista();
		
		List<Ciclista> l = dao.buscarTodos();
		for(Ciclista c: l){
			if(c.getNomeq().equals(equipo)) System.out.println(c.toString());
		}
		ConexionJdbc.cerrar();
	}

}

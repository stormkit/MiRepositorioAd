package programas;

import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _07CambiarDeEquipo {
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Qué dorsal quieres cambiar: ");
		int dorsal = tec.nextInt(); 
		System.out.println("A qué equipo: ");
		String equipo = tec.next();
		
		ConexionJdbc.conectar("configuracion\\propiedadesciclismo.txt");
		DaoCiclista dao = new DaoCiclista();
		
		
		//Recupero el ciclista
		Ciclista c = dao.buscarPorId(dorsal);
		if(c!=null){
			//Le cambio el equipo
			c.setNomeq(equipo);
			//Actualizamos la base de datos 
			try {
				dao.actualizar(c);
			} catch (BusinessException e) {
				System.out.println("No se puede cambiar al ciclista");
			}
		} else System.out.println("El ciclista no exite");
		
		ConexionJdbc.cerrar();
	}

}

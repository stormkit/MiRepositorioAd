package programas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.DaoCiclista;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _15MostrarCiclistasPorNombreYNacimiento {

	public static void main(String[] args) {

		ConexionJdbc.conectar("configuracion//propiedadesciclismo.txt");
		Scanner lector =new Scanner (System.in);
		System.out.println("Introduzca nombre del ciclista");
		String nombreCiclista=lector.nextLine();
		System.out.println("Introduzca fecha de nacimiento inicial");
		String fechaInicial=lector.nextLine();
		System.out.println("Introduzca la fecha de nacimiento final");
		String fechaFinal=lector.nextLine();
		
		if (nombreCiclista.equals("")){ nombreCiclista=null;}
		try {
			Date fechaI=new SimpleDateFormat("dd//MM//yyyy").parse(fechaInicial);
			Date fechaF=new SimpleDateFormat("dd//MM//yyyy").parse(fechaFinal);
			
			DaoCiclista dao=new DaoCiclista();
			List <Ciclista>l=dao.getCiclistasPorNombreYNacimiento(nombreCiclista, new java.sql.Date (fechaI.getTime()), new java.sql.Date (fechaF.getTime()));
			
			for(Ciclista c:l){
				
				System.out.format("%-20s %-15s %-15s%n");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

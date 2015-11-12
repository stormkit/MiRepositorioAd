package programas;

import java.util.Scanner;

import dao.DaoCiclista;
import dao.DaoEtapa;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;
import pojos.Etapa;

public class _01Ganador {
	public static void main(String[] args) {
		
		ConexionJdbc.conectar("configuracion//propiedadesciclismo.txt");
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduzca el número de la etapa");
		int numeroEtapa=tec.nextInt();
		System.out.println("Introduzca el dorsal del ciclista");
		int dorsalCiclista=tec.nextInt();
		
		DaoCiclista daoC=new DaoCiclista();
		Ciclista c=daoC.buscarPorId(dorsalCiclista);
		
		
		
		DaoEtapa daoE=new DaoEtapa();
	    Etapa e=daoE.buscarPorId(numeroEtapa);
	    
	    e.setDorsal(c.getDorsal());
		
		try {
			daoE.actualizar(e);
			
		} catch (BusinessException e1) {
			e1.printStackTrace();
			e1.getMessage();
			
		}finally{
			
			ConexionJdbc.cerrar();
		}
		
		
	}
}

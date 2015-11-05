package programas;

import java.util.ArrayList;
import java.util.List;

import dao.DaoEquipo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;
import pojos.Equipo;

public class _16CrearEquipoConCiclistas {

	public static void main(String[] args) {

		ConexionJdbc.conectar("configuracion//propiedadesciclismo.txt");
		
		try{
			
			Equipo e=new Equipo("DAM2","Pepe Alberick");
			
			List<Ciclista>l =new ArrayList<>();
			
			l.add(new Ciclista(null,"Miguel Aparicio", "DAM2", new java.util.Date()));
			
			l.add(new Ciclista (null,"Rodolfo Perez", "DAM2", new java.util.Date()));

			l.add(new Ciclista(null,"Angel gomez", "DAM2", new java.util.Date()));

			l.add(new Ciclista (null,"Ana sierra", "DAM2", new java.util.Date()));


			
		DaoEquipo dao1=new DaoEquipo();
		dao1.anyadirEquipo(e, l);
			
			
			
		}catch(BusinessException ex){
			
			System.out.println("No se ha podido realizar la operación");
			
		}
	}

}

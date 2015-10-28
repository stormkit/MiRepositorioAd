package programas;

import java.util.List;

import dao.DaoCiclista;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _05MostrarCiclistas {
	public static void main(String[] args) {
		ConexionJdbc.conectar("configuracion\\propiedadesciclismo.txt");
		DaoCiclista dao = new DaoCiclista();
		
		List<Ciclista> l = dao.buscarTodos();
		for(Ciclista c: l){
			System.out.println(c.toString());
		}
		ConexionJdbc.cerrar();
		
//		for(int i = 0; i<l.size();i++){
//			System.out.println(l.get(i).toString());
//		}
	}

}

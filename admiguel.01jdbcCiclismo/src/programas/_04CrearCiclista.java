package programas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DaoCiclista;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Ciclista;

public class _04CrearCiclista {

	public static void main(String[] args) {
		ConexionJdbc.conectar("configuracion//propiedadesCiclismo.txt");
		
//		Ciclista c = new Ciclista(101,"pepe","ONCE",new Date());
		Ciclista c=null;
		try {
			c = new Ciclista(null,"pepe","ONCE",new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2000"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			new DaoCiclista().grabar(c);
			System.out.println("CREADO CORRECTAMENTE");
			System.out.println("El ciclista se ha creado con dorsal: " + c.getDorsal());
		} catch (BusinessException e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
		} finally {
			ConexionJdbc.cerrar();
		}
	
	}
}

package programas;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class ConsultarEquipoYSusCiclistasHibernate {

	public static void main(String[] args) {

		Scanner tec=new Scanner(System.in);
		
		System.out.println("Qué equipo quieres consultar");
		String nombreEquipo=tec.nextLine();
		
		SessionFactory sf=UtilesHibernate.getSessionFactory();
		Session s=sf.getCurrentSession();
		s.beginTransaction();
		Equipo e=(Equipo) s.get(Equipo.class, nombreEquipo);
		if (e != null){System.out.println("el director es " + e.getDirector());
		System.out.println("cliclistas de este equipo");
		
		for(Ciclista c: e.getMiembros()){
			
			System.out.println(c.toString());
		}}
		s.getTransaction().commit();
		
	}

}

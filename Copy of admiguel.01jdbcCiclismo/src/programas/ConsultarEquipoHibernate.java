package programas;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class ConsultarEquipoHibernate {

	public static void main(String[] args) {

		Scanner tec=new Scanner(System.in);
		
		System.out.println("Qué equipo quieres consultar");
		String nombreEquipo=tec.nextLine();
		SessionFactory sf=UtilesHibernate.getSessionFactory();
		Session s=sf.getCurrentSession();
		s.beginTransaction();
		Equipo e=(Equipo) s.get(Equipo.class, nombreEquipo);
		if (e != null){System.out.println("el director es " + e.getDirector());}
		s.getTransaction().commit();
		
	}

}

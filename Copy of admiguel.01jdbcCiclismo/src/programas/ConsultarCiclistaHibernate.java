package programas;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class ConsultarCiclistaHibernate {

	public static void main(String[] args) {

		Scanner tec=new Scanner(System.in);
		
		System.out.println("Qué dorsal del ciclista quieres consultar");
		int dorsal=tec.nextInt();
		
		SessionFactory sf=UtilesHibernate.getSessionFactory();
		Session s=sf.getCurrentSession();
		s.beginTransaction();
		Ciclista c=(Ciclista) s.get(Ciclista.class, dorsal);
		System.out.println("Dorsal: " + c.getDorsal());
		System.out.println("Nombre: " + c.getNombre());
		System.out.println("Director: " + c.getEquipo().getDirector());
		s.getTransaction().commit();
		
	}

}

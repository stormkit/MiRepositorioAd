package programas;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class InsertarCiclistaHibernate {

	public static void main(String[] args) {

		
		
		SessionFactory sf=UtilesHibernate.getSessionFactory();
		Session s=sf.getCurrentSession();
		s.beginTransaction();
		
		Ciclista c=new Ciclista();
		Equipo e=new Equipo();
		e.setNomeq("ONCE");
		c.setNombre("Luis");
		c.setEquipo(e);
		c.setNacimiento(new Date());
		
		s.save(c);
		
		System.out.println("Insertado ciclista con dorsal " + c.getDorsal());
		System.out.println("Director del ciclista " + c.getEquipo().getDirector());
		
		s.getTransaction().commit();
		
	}

}

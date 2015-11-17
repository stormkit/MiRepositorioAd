package programas;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SourceType;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class InsertarCiclistaSiExisteHibernate {

	public static void main(String[] args) {

		Scanner tec=new Scanner(System.in);
		System.out.println("Introduzca dorsal de ciclista");
		int dorsal=tec.nextInt();
		
		
		SessionFactory sf=UtilesHibernate.getSessionFactory();
		Session s=sf.getCurrentSession();
		s.beginTransaction();
		
		Ciclista c=(Ciclista) s.get(Ciclista.class, dorsal);
		Equipo e=c.getEquipo();
		
		if(c == null){
			
			System.out.println("No existe este ciclista");
			
		}else{

		   for(Ciclista compi:e.getMiembros() ){
			   
			   if(!compi.equals(c)){
				   
				   System.out.println(compi.getNombre() + " , " + compi.getDorsal());
				   
			   }
		   }
		}
		
		s.getTransaction().commit();
		
	}

}

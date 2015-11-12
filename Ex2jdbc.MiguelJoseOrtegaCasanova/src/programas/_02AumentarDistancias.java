package programas;

import java.util.List;

import dao.DaoEtapa;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Etapa;

public class _02AumentarDistancias {
	public static void main(String[] args) {
		
		DaoEtapa daoE=new DaoEtapa();
		try {	
		List <Etapa> l=daoE.buscar(null, null,100,200);
		Etapa e=null;
		
		int registro=0;
		
		for(int i=0;i<l.size();i++){
			
			e=new Etapa(l.get(registro).getNetapa(), (l.get(registro).getKm())+20, l.get(registro).getSalida(), l.get(registro).getLlegada(), l.get(registro).getDorsal());
			
		
				
				daoE.actualizar(e);
				registro++;
				
			
			
		}
		
		} catch (BusinessException e1) {
			
			e1.printStackTrace();
			e1.getMessage();
		}finally{
			
			ConexionJdbc.cerrar();
			
			}

	}
}

package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Equipo") // No es necesaria si es el mismo nombre el del pojo que el de la tabla

public class Equipo implements Serializable{

	@Id
	@Column (name = "nomeq") // No es necesaria
	private String nomeq;
	
	@Column (name="director")//No es necesaria
	private String director;
	
	public Equipo(){
		
	}
	public Equipo(String nomeq, String director){
		this.nomeq = nomeq;
		this.director = director;
		
	}
	
	public String getNomeq() {
		return nomeq;
	}
	public void setNomeq(String nomeq) {
		this.nomeq = nomeq;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String dire) {
		this.director = dire;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeq == null) ? 0 : nomeq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		if (nomeq == null) {
			if (other.nomeq != null)
				return false;
		} else if (!nomeq.equals(other.nomeq))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "equipo [nomeq=" + nomeq + ", dire=" + director + "]";
	}
	
		
}

package pojos;

import java.util.Date;

public class Ciclista {
	private Integer dorsal;
	private String nombre;
	private String nomeq;
	private Date nacimiento;
	public Ciclista(){}
	public Ciclista(Integer dorsal, String nombre, String nomeq, Date nacimiento) {
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.nomeq = nomeq;
		this.nacimiento = nacimiento;
	}
	@Override
	public String toString() {
		return "Ciclista [dorsal=" + dorsal + ", nombre=" + nombre + ", nomeq="
				+ nomeq + ", nacimiento=" + nacimiento + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dorsal == null) ? 0 : dorsal.hashCode());
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
		Ciclista other = (Ciclista) obj;
		if (dorsal == null) {
			if (other.dorsal != null)
				return false;
		} else if (!dorsal.equals(other.dorsal))
			return false;
		return true;
	}
	public Integer getDorsal() {
		return dorsal;
	}
	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomeq() {
		return nomeq;
	}
	public void setNomeq(String nomeq) {
		this.nomeq = nomeq;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	
	
}

package pojos;

public class Etapa {
	private Integer netapa;
	private Integer km;
	private String salida;
	private String llegada;
	private Integer dorsal;
	
	public Etapa(){}

	public Etapa(Integer netapa, Integer km, String salida, String llegada,	Integer dorsal) {
		this.netapa = netapa;
		this.km = km;
		this.salida = salida;
		this.llegada = llegada;
		this.dorsal = dorsal;
	}

	public Integer getNetapa() {
		return netapa;
	}

	public void setNetapa(Integer netapa) {
		this.netapa = netapa;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((netapa == null) ? 0 : netapa.hashCode());
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
		Etapa other = (Etapa) obj;
		if (netapa == null) {
			if (other.netapa != null)
				return false;
		} else if (!netapa.equals(other.netapa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "netapa=" + netapa + ", km=" + km + ", salida=" + salida
				+ ", llegada=" + llegada + ", dorsal=" + dorsal ;
	}
	
}

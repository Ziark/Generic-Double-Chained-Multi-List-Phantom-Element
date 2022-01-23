package tads;

public class Activitat implements Comparable<Activitat>{
	
	private String codiActivitat, nomActivitat;
	private double preuActivitat;
	
	public Activitat(String cAct, String nAct, double pAct) {
		codiActivitat = cAct;
		nomActivitat = nAct;
		preuActivitat = pAct;
	}

	@Override
	public String toString() {
		return "Activitat [codiActivitat=" + codiActivitat + ", nomActivitat=" + nomActivitat + ", preuActivitat="
				+ preuActivitat + "]";
	}
	
	/**
	 * Metode equals per comparar dues activitats.
	 * Aquestes son iguals si els seus codis ho son.
	 * @param id codi de l'activitat
	 * @return cert si els codis son iguals, fals en cas contrari
	 */
	public boolean equals (String id){
		return (this.codiActivitat.equals(id));
	}

	/**
	 * Metode per comparar dos instancies de la clase Activitat. El criteri de 
	 * comparacio es: primer, el nom de l'Activitat i segon, el codi de l'Activitat. 
	 * Si this < o retorna negatiu.
	 * Si this == o retorna 0.
	 * Si this > o retorna positiu.
	 */
	@Override
	public int compareTo(Activitat o) {
		if (this.nomActivitat.compareTo(o.getNomActivitat())==0) return this.codiActivitat.compareTo(o.getCodiActivitat());
		else return this.nomActivitat.compareTo(o.getNomActivitat());
	}

	public String getCodiActivitat() {
		return codiActivitat;
	}

	public String getNomActivitat() {
		return nomActivitat;
	}

	public double getPreuActivitat() {
		return preuActivitat;
	}

	public void setCodiActivitat(String codiActivitat) {
		this.codiActivitat = codiActivitat;
	}

	public void setNomActivitat(String nomActivitat) {
		this.nomActivitat = nomActivitat;
	}

	public void setPreuActivitat(double preuActivitat) {
		this.preuActivitat = preuActivitat;
	}
	
	
	
}

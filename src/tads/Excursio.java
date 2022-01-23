package tads;

public class Excursio implements Comparable<Excursio>{
	
	private String nomExcursio, codiExcursio;
	private int numTur=0;
	private double preu=0;
	
	public Excursio (String cExc, String nExc) {
		codiExcursio = cExc;
		nomExcursio = nExc;
		preu = 0;
		numTur++;
	}

	@Override
	public String toString() {
		return "Excursio [codiExcursio=" + codiExcursio + ", nomExcursio=" + nomExcursio + "]";
	}
	
	/**
	 * Metode equals per comparar si dues excursions son iguals.
	 * Ho son si tenen el mateix codi.
	 * @param id codi identificador de l'excursio
	 * @return Cert si els codis son iguals, fals en cas contrari
	 */
	public boolean equals (String id){
		return (this.codiExcursio.equals(id)); 
	}
	
	/**
	 * Metode per comparar dues instancies de la classe Excursio. El criteri de comparacio es: 
	 * primer, el nom de l'Excursio i segon, el codi de l'Excursio.
	 * Si this < o retorna negatiu.
	 * Si this == o retorna 0.
	 * Si this > o retorna positiu.
	 */
	public int compareTo(Excursio o) {
		if (this.nomExcursio.compareTo(o.getNomExcursio()) == 0) return this.codiExcursio.compareTo(o.getCodiExcursio());
		else return this.nomExcursio.compareTo(o.getNomExcursio());
	}

	public String getNomExcursio() {
		return nomExcursio;
	}

	public String getCodiExcursio() {
		return codiExcursio;
	}

	public int getNumTur() {
		return numTur;
	}

	public void setNumTur(int numTur) {
		this.numTur = numTur;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

		
	
}

package tads;

import java.util.Arrays;

public class Turista implements Comparable<Turista>{

		private int idTurista;
		private String[] nomTurista = new String[2];
		
		public Turista(int id, String nom, String cognom) {
			idTurista = id;
			nomTurista[0] = nom;
			nomTurista[1] = cognom;
		}

		@Override
		public String toString() {
			return "Turista [idTurista=" + idTurista + ", nomTurista=" + Arrays.toString(nomTurista) + "]";
		}
		
		public boolean equals(int id) {
			return(this.idTurista == id);
		}

		/**
		 * Metode per comparar dos instancies de la classe Turista. El criteri de comparacio es:
		 * primer el nom del Turista i segon, el cognom del turista.
		 * Si this < o retorna negatiu.
		 * Si this == o retorna 0.
		 * Si this > o retorna positiu.
		 */
		public int compareTo(Turista o) {
			if (this.nomTurista[1].compareTo(o.getCognomTurista())==0) return this.nomTurista[0].compareTo(o.getNomTurista());
			else return this.nomTurista[1].compareTo(o.getCognomTurista());
		}

		public int getIdTurista() {
			return idTurista;
		}

		public String getNomTurista() {
			return nomTurista[0];
		}
		
		public String getCognomTurista() {
			return nomTurista[1];
		}	
}

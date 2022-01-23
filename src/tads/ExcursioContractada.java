package tads;

public class ExcursioContractada {

	TADMultillistaGenerica<Excursio, Turista> mll;
	
	public ExcursioContractada(TADMultillistaGenerica<Excursio, Turista> mult) {
		mll = mult;
	}
	
	/**
	 * Afegeix una relacio entre un turista i una excursio, i, a mes,
	 * sumem 1 al numero de turistes d'aquesta excursio
	 * @param exc Excursio
	 * @param tur Turista
	 */
	public void relacionarExcTur(Excursio exc, Turista tur) {
		mll.relacionar(exc, tur);
		TADMultillistaGenerica<Excursio, Turista>.NodeE auxE;
		auxE = mll.getPrimerElem1();
		while (auxE.elemE.compareTo(exc) != 0) auxE = auxE.seguent;
		if (auxE.elemE.compareTo(exc) == 0) {
			int numAux = auxE.elemE.getNumTur()+1;
			auxE.elemE.setNumTur(numAux);
		}
	}
	
	/**
	 * Busca una Excursio a la multillista per mitja del seu codi
	 * @param codi Codi de la Excursio a buscar
	 * @return la excursio amb el codi especificat o null si no la troba
	 */
	public Excursio BuscarExc (String codi) {
		Excursio auxExc = new Excursio(null, null);
		TADMultillistaGenerica<Excursio,Turista>.NodeE auxE;
		auxE = mll.getPrimerElem1();
		if (auxE == null) auxExc = null;
		else {
			if (auxE.elemE == null) auxExc = null;
			else {
				while ((!auxE.elemE.equals(codi)&&(auxE.seguent != null))) auxE = auxE.seguent;
				if(auxE.elemE.equals(codi)) auxExc = auxE.elemE;
				else auxExc = null;
			}
		}
		return auxExc;
	}
	
	/**
	 * Busca un Turista a la multillista per mitja del seu codi
	 * @param codi codi del turista a buscar
	 * @return el turista amb el codi introduit, null si no el troba
	 */
	public Turista BuscarTur (int codi) {
		Turista auxTur = new Turista(0, null, null);
		TADMultillistaGenerica<Excursio, Turista>.NodeO auxO;
		auxO = mll.getPrimerElem2();
		if (auxO == null) auxTur = null;
		else {
			if (auxO.elemO == null) auxTur = null;
			else {
				while ((!auxO.elemO.equals(codi)&&(auxO.seguent != null))) auxO = auxO.seguent;
				if(auxO.elemO.equals(codi)) auxTur = auxO.elemO;
				else auxTur = null;
			}
		}
		return auxTur;
	}
	
	/**
	 * Metode per consultar els turistes que van a una excursio
	 * @param exc excursio de la qual es volen consultar els turistes que la composen
	 * @return una llista amb tots els turistes que te l'excursio especificada
	 */
	public LlistaDinamica<Turista> retornarTuristes(Excursio exc){
		TADMultillistaGenerica<Excursio, Turista>.NodeE col = mll.PrimerElem1;
		LlistaDinamica<Turista> llistaAux = new LlistaDinamica<Turista>();
		while ((col.elemE.compareTo(exc) != 0) && (col.seguent != null)) col = col.seguent;
		if (col.elemE.compareTo(exc) == 0) {
			TADMultillistaGenerica<Excursio,Turista>.NodeMult fila = col.primer;
			while (fila.SegElem2 != null) {
				llistaAux.inserir(fila.elem2.seguent.elemO);
				fila = fila.SegElem2;
			}
			if (fila != null) llistaAux.inserir(fila.elem2.elemO);
		}
		return llistaAux;
	}
	
	/**
	 * Metode per consultar les excursions a les que va un turista
	 * @param tur turista del qual es vol saber a quines excursions va
	 * @return una llista amb totes les excursions a les que va el turista
	 */
	public LlistaDinamica<Excursio> retornarExcursions(Turista tur){
		LlistaDinamica<Excursio> llistaAux = new LlistaDinamica<Excursio>();
		TADMultillistaGenerica<Excursio,Turista>.NodeO fila = mll.PrimerElem2;
		while ((fila.elemO.compareTo(tur) != 0)&&(fila.seguent != null)) fila = fila.seguent;
		if (fila.elemO.compareTo(tur) == 0) {
			TADMultillistaGenerica<Excursio,Turista>.NodeMult col = fila.primer;
			while (col.SegElem1 != null) {
				llistaAux.inserir(col.elem1.elemE);
				col = col.SegElem1;
			}
			if (col != null) llistaAux.inserir(col.elem1.elemE);
		}
		return llistaAux;
	}
	
}

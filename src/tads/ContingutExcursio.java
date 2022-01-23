package tads;
/**
 * Aquesta classe guarda la relació entre Activitat i Excursió amb una multillista
 * @author Marc Isidre Balada Fibla
 * @since 11/04/2018
 * @param <E> - Variable del tipus genèrica
 */
public class ContingutExcursio{

	TADMultillistaGenerica<Excursio, Activitat> mll;
	
	public ContingutExcursio(TADMultillistaGenerica<Excursio, Activitat> mult) {
		mll = mult;
	}
	
	/**
	 * Metode que afegeix a la multillista una relacio entre una activitat i una excursio.
	 * A mes, incrementa el preu de l'excursio per cada activitat que la composa
	 * @param act
	 * @param exc
	 */
	public void relacionarExcAct(Excursio exc, Activitat act) {
		mll.relacionar(exc, act);
		TADMultillistaGenerica<Excursio, Activitat>.NodeE auxE;
		auxE = mll.PrimerElem1;
		while (auxE.elemE.compareTo(exc) != 0) auxE = auxE.seguent;
		if(auxE.elemE.compareTo(exc) == 0) {
			auxE.elemE.setPreu(auxE.elemE.getPreu() + act.getPreuActivitat());
		}
	}
	
	/**
	 * Metode que busca una excursio, per mitja del seu codi, a la multillista
	 * @param codi codi de l'excursio que busquem
	 * @return la excursio amb el codi especificat
	 */
	public Excursio BuscarExc(String codi) {
		Excursio auxExc = new Excursio(null, null);
		TADMultillistaGenerica<Excursio, Activitat>.NodeE auxE;
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
	 * Metode que busca una activitat, per mitja del seu codi, a la multillista
	 * @param codi codi de l'activitat que busquem
	 * @return l'activitat amb el codi especificat
	 */
	public Activitat BuscarAct(String codi) {
		Activitat auxTur = new Activitat(null, null, 0);
		TADMultillistaGenerica<Excursio, Activitat>.NodeO auxO;
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
	 * Metode que retorna una llista amb les activitats que te l'excursio especificada
	 * @param exc Excursio de la que volem saber les activitats
	 * @return una llista amb les activitats que te la excursio especificada
	 */
	public LlistaDinamica<Activitat> retornarActivitats(Excursio exc){
		TADMultillistaGenerica<Excursio, Activitat>.NodeE col = mll.PrimerElem1;
		LlistaDinamica<Activitat> llistaAux = new LlistaDinamica<Activitat>();
		while ((col.elemE.compareTo(exc) != 0) && (col.seguent != null)) col = col.seguent;
		if (col.elemE.compareTo(exc) == 0) {
			TADMultillistaGenerica<Excursio, Activitat>.NodeMult fila = col.primer;
			while (fila.SegElem2 != null) {
				llistaAux.inserir(fila.elem2.elemO);
				fila = fila.SegElem2;
			}
			if (fila != null) llistaAux.inserir(fila.elem2.elemO);
		}
		return llistaAux;
	}
	
	/**
	 * Metode que retorna una llista amb les excursions que fan una activitat especificada
	 * @param act Activitat de la que volem saber les excursions
	 * @return una llista amb les excursions que duen l'activitat especificada
	 */
	public LlistaDinamica<Excursio> retornarExcursions(Activitat act){
		LlistaDinamica<Excursio> llistaAux = new LlistaDinamica<Excursio>();
		TADMultillistaGenerica<Excursio, Activitat>.NodeO fila = mll.PrimerElem2;
		while ((fila.elemO.compareTo(act) != 0)&&(fila.seguent != null)) fila = fila.seguent;
		if (fila.elemO.compareTo(act) == 0) {
			TADMultillistaGenerica<Excursio, Activitat>.NodeMult col = fila.primer;
			while (col.SegElem1 != null) {
				llistaAux.inserir(col.elem1.elemE);
				col = col.SegElem1;
			}
			if (col != null) llistaAux.inserir(col.elem1.elemE);
		}
		return llistaAux;
	}
	
}

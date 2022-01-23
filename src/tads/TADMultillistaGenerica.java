package tads;

import excepcions.LlistaBuida;

/**
 * Interficie generica per guardar totes les relacions del programa
 * @author Marc Isidre Balada Fibla
 * @since 13/04/2018
 * @param <E> Element amb relacio amb l'element <O>
 * @param <O> Element amb relacio amb l'element <E>
 */
public class TADMultillistaGenerica<E extends Comparable<E>,O extends Comparable<O>> {
	
	NodeE PrimerElem1;
	NodeO PrimerElem2;
	
	public class NodeE {
		E elemE;
		NodeE seguent;
		NodeMult primer;
		
		public NodeE(E elem) {
			elemE = elem;
			seguent = null;
			primer = null;
		}
	}
	
	public class NodeO {
		O elemO;
		NodeO seguent;
		NodeMult primer;
		
		public NodeO(O elem) {
			this.elemO = elem;
			this.seguent = null;
			this.primer = null;
		}
	}
	
	public class NodeMult {
		NodeE elem1;
		NodeO elem2;
		NodeMult SegElem1;
		NodeMult SegElem2;
		
		public NodeMult(NodeE e1, NodeO e2) {
			this.elem1 = e1;
			this.elem2 = e2;
			this.SegElem1 = null;
			this.SegElem2 = null;
		}
	}
	
	public TADMultillistaGenerica(){
		PrimerElem1 = null;
		PrimerElem2 = null;
	}
	
	//Getters i Setters
	public NodeE getPrimerElem1() {
		return PrimerElem1;
	}

	public void setPrimerElem1(NodeE primerElem1) {
		PrimerElem1 = primerElem1;
	}

	public NodeO getPrimerElem2() {
		return PrimerElem2;
	}

	public void setPrimerElem2(NodeO primerElem2) {
		PrimerElem2 = primerElem2;
	}
	

	/**
	 * Metode que estableix la relacio entre dos elements a la Multillista
	 * @param e1
	 * @param e2
	 * @throws LlistaBuida
	 */
	public void relacionar (E e1, O e2) {
		
		//Primer comprovem el cas en que no tinguem res a la multillista
		if ((PrimerElem1 == null)&&(PrimerElem2 == null)){
			NodeE elemE = new NodeE(e1);
			NodeO elemO = new NodeO(e2);
			PrimerElem1 = elemE;
			PrimerElem2 = elemO;
			NodeMult relacio = new NodeMult(elemE,elemO);
			PrimerElem1.primer = relacio;
			PrimerElem2.primer = relacio;
		}
		//Si ja tenim alguna cosa
		else {
			boolean hiEsE1 = false;
			int opcio;
			NodeE auxE1 = PrimerElem1;
			NodeO auxE2 = PrimerElem2;
			
			//busquem l'element igual del "tipus 1" per la multillista
			while ((auxE1.elemE.compareTo(e1) != 0)&&(auxE1.seguent != null)) auxE1 = auxE1.seguent;
			//si NO el trobem pero no hi ha més elements
			if ((auxE1.elemE.compareTo(e1) != 0)&&(auxE1.seguent == null)) {
				//l'afegim a la seguent posicio
				NodeE elemE = new NodeE(e1);
				auxE1.seguent = elemE;
			}
			//en cas que el trobem
			else hiEsE1 = true;
			//Ara el mateix per l'altre element
			//busquem l'element igual del "tipus 2" per la multillista
			while ((auxE2.elemO.compareTo(e2) != 0)&&(auxE2.seguent != null)) auxE2 = auxE2.seguent;
			//si NO el trobem pero no hi ha més elements
			if ((auxE2.elemO.compareTo(e2) != 0)&&(auxE1.seguent == null)) {
				//l'afegim a la seguent posicio
				NodeO elemO = new NodeO(e2);
				auxE2.seguent = elemO;
				if (hiEsE1) opcio = 1; 
				else opcio = 3;
			}
			//en cas que el trobem
			else {
				if (hiEsE1) opcio = 0;
				else opcio = 2;
			}
			
			NodeMult relAux;
			NodeE canviE;
			NodeO canviO;
			boolean nullpointer = false;
			
			switch (opcio) {
				//els 2 elements hi son
				case 0: 
					relAux = new NodeMult(auxE1.seguent, auxE2.seguent);
					relAux.elem1 = auxE1;
					relAux.elem2 = auxE2;
					canviE = PrimerElem1;
					canviO = PrimerElem2;
					while(canviE.elemE.compareTo(e1) != 0) canviE = canviE.seguent;
					while(canviO.elemO.compareTo(e2) != 0) canviO = canviO.seguent;
					relAux.SegElem1 = auxE2.primer;
					relAux.SegElem2 = auxE1.primer;
					if(canviE.elemE.compareTo(e1) == 0) canviE.primer = relAux;
					if(canviO.elemO.compareTo(e2) == 0) canviO.primer = relAux;
				break;
				//sols hi es el primer
				case 1:
					relAux = new NodeMult(auxE1, auxE2.seguent);
					relAux.elem1 = auxE1;
					relAux.elem2 = auxE2.seguent;					
					relAux.SegElem1 = null;
					canviO = PrimerElem2;
					canviE = PrimerElem1;
					while (canviO.elemO.compareTo(e2) != 0) canviO = canviO.seguent;
					while (canviE.elemE.compareTo(e1) != 0) canviE = canviE.seguent;
					relAux.SegElem2 = auxE1.primer;
					if(canviO.elemO.compareTo(e2) == 0) canviO.primer = relAux;
					if(canviE.elemE.compareTo(e1) == 0) canviE.primer = relAux;
				break;
				//sols hi es el segon
				case 2:
					relAux = new NodeMult(auxE1.seguent, auxE2);
					relAux.elem1 = auxE1.seguent;
					relAux.elem2 = auxE2;
					canviE = PrimerElem1;
					canviO = PrimerElem2;
					while(canviE.elemE.compareTo(e1) != 0) canviE = canviE.seguent;
					
					while((canviO.elemO.compareTo(e2) != 0)&&(!nullpointer)) {
						//System.out.println("A"+canviO.elemO+"\n"+e2+"\n"+canviO.seguent);
						if(canviO.seguent != null)canviO = canviO.seguent;
						else nullpointer = true;
						//System.out.println("B"+canviO.elemO+"\n"+e2+"\n"+canviO.seguent);
					}
					relAux.SegElem1 = auxE2.primer;
					relAux.SegElem2 = null;
					if(canviE.elemE.compareTo(e1) == 0) canviE.primer = relAux;
					if(canviO.elemO.compareTo(e2) == 0) canviO.primer = relAux;
				break;
				//cap dels 2 hi es
				case 3:
					relAux = new NodeMult(auxE1.seguent, auxE2.seguent);
					relAux.elem1 = auxE1.seguent;
					relAux.elem2 = auxE2.seguent;
					relAux.SegElem1 = null;
					relAux.SegElem2 = null;
					canviE = PrimerElem1;
					canviO = PrimerElem2;
					while(canviE.elemE.compareTo(e1) != 0) canviE = canviE.seguent;
					while(canviO.elemO.compareTo(e2) != 0) canviO = canviO.seguent;
					if(canviE.elemE.compareTo(e1) == 0) canviE.primer = relAux;
					if(canviO.elemO.compareTo(e2) == 0) canviO.primer = relAux;
				break;
				
			}
		}
	}
	
	/**
	 * Retorna tots els elements <O> de la llista amb els que esta relacionat l'element <E>
	 * @param e element del qual es comprovara la relcio
	 * @return una llista d'elements <O> (els relacionats amb <E>)
	 */
	public TADLlistaGenerica<O> relacionsAmbO (E e){
		TADLlistaGenerica<O> llistaElem = new LlistaDinamica<O>();
		NodeE elemFila = PrimerElem1;
		while ((elemFila.elemE.compareTo(e) != 0) && (elemFila.seguent != null)) elemFila = elemFila.seguent;
		if (elemFila.elemE.compareTo(e) == 0) {
			NodeMult elemCol = elemFila.primer;
			while(elemCol.SegElem2 != null) {
				llistaElem.inserir(elemCol.elem2.elemO);
				elemCol = elemCol.SegElem2;
			}
		}
		return llistaElem;
	}
	
	/**
	 * * Retorna tots els elements <E> de la llista amb els que esta relacionat l'element <O>
	 * @param o element del qual es comprovara la relcio
	 * @return una llista d'elements <E> (els relacionats amb <O>)
	 */
	public TADLlistaGenerica<E> relacionsAmbE (O o){
		TADLlistaGenerica<E> llistaElem = new LlistaDinamica<E>();
		NodeO elemCol = PrimerElem2;
		while ((elemCol.elemO.compareTo(o) != 0) && (elemCol.seguent != null)) elemCol = elemCol.seguent;
		if (elemCol.elemO.compareTo(o) == 0) {
			NodeMult elemFila = elemCol.primer;
			while(elemFila.SegElem1 != null) {
				llistaElem.inserir(elemFila.elem1.elemE);
				elemFila = elemFila.SegElem1;
			}
		}
		return llistaElem;
	}
	
	/**
	 * Metode que ens permet consultar si existeix una relacio a la multillista entre els elements especificats
	 * @param e
	 * @param o
	 * @return cert si existeix la relacio, fals en cas contrari
	 */
	public boolean consultar(E e, O o) {
		boolean hiEs = false;
		NodeE elemFila = PrimerElem1;
		while ((elemFila.seguent != null)&&(elemFila.elemE.compareTo(e) != 0)) elemFila = elemFila.seguent;
		if (elemFila.elemE.compareTo(e) == 0) {
			NodeMult elemCol = elemFila.primer;
			while((elemCol.elem2.elemO.compareTo(o) != 0)&&(elemCol.SegElem2 != null)) elemCol = elemCol.SegElem2;
			if(elemCol.elem2.elemO.compareTo(o) == 0) hiEs = true;
		}
		return hiEs;
	}
}

package tads;

import java.util.Iterator;

/**
 * Iterador personal per tractar els cassos particulars amb nodes de la practica
 * @author Marc Isidre Balada Fibla
 *
 * @param <T>
 */
public class MeuIterator<T extends Comparable<T>> implements Iterator<T> {
	private LlistaDinamica<T> llista = new LlistaDinamica<T>();	
	private int posicioIterator;
	private TADNode<T> node;
	
	public MeuIterator(LlistaDinamica<T> ll) {
		llista = ll;
		node = llista.getPrimer();
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	@Override
	public boolean hasNext() {
		return ((posicioIterator<llista.numElem()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		T aux = (T)node.getValor();
		node = node.getSeguent();
		posicioIterator++;
		return aux;
	}

	

}
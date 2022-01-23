package tads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import excepcions.LlistaBuida;

/**
 * Implementació de la llista amb una estructura java.util.collection
 * @author Marc Isidre Balada Fibla
 * @param <E> element genèric 
 */
public class LlistaJUCollection<E extends Comparable <E>> implements Iterable<E>, TADLlistaGenerica<E> {
	
	private ArrayList<E> llista;
	
	public LlistaJUCollection() {
		llista = new ArrayList<E>();
	}

	public void inserir(E e) {
		llista.add(e);
		Collections.sort(llista);
	}

	public boolean existeix(E e) throws LlistaBuida{
		if (numElem()==0) throw new LlistaBuida();
		else return llista.contains(e);
	}

	public E consultar(int posicio) throws LlistaBuida{
		if (numElem()==0) throw new LlistaBuida();
		else return llista.get(posicio);
	}

	public int numElem() {
		return llista.size();
	}

	public String toString() {
		return "LlistaJUCollection [llista=" + llista + "]";
	}
	
	public Iterator<E> iterator() {
		return llista.iterator();
	}		
}

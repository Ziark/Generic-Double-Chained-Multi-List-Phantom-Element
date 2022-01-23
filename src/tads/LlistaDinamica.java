package tads;

import java.util.Iterator;
import excepcions.LlistaBuida;

public class LlistaDinamica<E extends Comparable<E>> implements Iterable<E>, TADLlistaGenerica<E>{

	private TADNode<E> primer;
	private int numElem;
	
	public LlistaDinamica() {
		primer = null;
		numElem = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void inserir(E e) {
		TADNode<E> nou = new TADNode<E>(e);
		TADNode<E> aux = primer;
		Object auxElem;
		Object elem;
		
		if (primer==null) {
			primer = nou;
			numElem++;
		}
		else {
			elem=nou.getValor();
			auxElem=aux.getValor();
			boolean esElPrimer = false;
			
			while(!esElPrimer) {
				if (((Comparable<E>)elem).compareTo((E)auxElem)<0) {
					if(aux.getAnterior()==null) {
						aux.setAnterior(nou);
						nou.setSeguent(aux);
						primer=nou;
						numElem++;
						esElPrimer=true;
					}
					else {
						nou.setAnterior(aux.getAnterior());
						aux.getAnterior().setSeguent(nou);
						nou.setSeguent(aux);
						aux.setAnterior(nou);
						numElem++;
						esElPrimer=true;
					}
				}
				else {
					if(aux.getSeguent()==null) {
						aux.setSeguent(nou);
						nou.setAnterior(aux);
						numElem++;
						esElPrimer=true;
					}
					else {
						aux = aux.getSeguent();
					}
				}
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	public boolean existeix(E e) throws LlistaBuida {
		boolean esAquestElement = false;
		int i=0;
		TADNode<E> nou = new TADNode<E>(e);
		TADNode<E> aux = primer;
		Object elem = nou.getValor();
		while((!esAquestElement)&&(i<numElem)){
			if(((Comparable<E>)elem).compareTo((E)aux.getValor()) == 0) esAquestElement=true;
			else {
				aux=aux.getSeguent();
				i++;
			}
		}
		return esAquestElement;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public E consultar(int posicio) throws LlistaBuida {
		boolean esAquestElem=false;
		TADNode<E> aux=primer;
		int i=0;
		while ((i<=posicio)&&(!esAquestElem)) {
			if (i==posicio) esAquestElem = true;
			else aux=aux.getSeguent();
		}
		return (E)aux.getValor();
	}
	
	/**
	 * 
	 */
	public int numElem() {
		return numElem;
	}
	
	/**
	 * Referencia al primer node de la llista
	 * @return el primer node de la llista
	 */
	public TADNode<E> getPrimer() {
		return primer;
	}

	@Override
	public Iterator<E> iterator() {
		MeuIterator<E> pI=new MeuIterator<E>(this);
		return pI;
	}

}

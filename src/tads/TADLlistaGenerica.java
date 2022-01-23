package tads;

import java.util.Iterator;

import excepcions.LlistaBuida;

/**
 * Interface per a definir el contenidor/col·lecció llista generica
 * @author Marc Isidre Balada Fibla
 * @param <E> defineix el contingut de la llista
 */
public interface TADLlistaGenerica<E extends Comparable <E>> {
	
		/**
		 * Afegeix un element a la llista
		 * @param e - element a afegir
		 * @throws LlistaPlena si a la llista no hi caben més elements
		 */
		public void inserir(E e);
		
		/**
		 * Rep per paramatre l'element sobre el que es vol fer la consulta i comporva si existeix
		 * @param e element a comprovar l'existència
		 * @post retorna booleà depent si existeix o no
		 * */
		public boolean existeix(E e) throws LlistaBuida;
		
		/**
		 * Rep per paramatre la posicio a consultar i  retrona l'objecte corresponent
		 * @param e
		 * @throws  
		 * @post retorna l'element en la posició especificada
		 * */
		public E consultar(int posicio) throws LlistaBuida;
		
		/**
		 * Comprovem el número d'elements
		 * @post retorna el num de elements que hi ha a la llista
		 * */
		public int numElem();
				
		/**
		 * Imprimeix per pantalla tots els elements de la cua
		 * @return els elements de la cua
		 */
		public String toString();
		
		/**
		 * Metode de la classe Iterator per recorrer un conjunt d'elements <E>
		 * @return 
		 */
		public Iterator<E> iterator();


}

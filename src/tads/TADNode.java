package tads;

public class TADNode<E> {
	private Object valor;
	private TADNode<E> seguent;
	private TADNode<E> anterior;
	
	public TADNode(E v) {
		valor=v;
		seguent=null;
		anterior=null;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public TADNode<E> getSeguent() {
		return seguent;
	}

	public void setSeguent(TADNode<E> seguent) {
		this.seguent = seguent;
	}

	public TADNode<E> getAnterior() {
		return anterior;
	}

	public void setAnterior(TADNode<E> anterior) {
		this.anterior = anterior;
	}
	

}

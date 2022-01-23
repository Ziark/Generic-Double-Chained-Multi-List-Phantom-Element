package excepcions;

public class LlistaBuida extends Exception{
	private static final long serialVersionUID = 1L;
	
	public LlistaBuida() {
		super("Error: Llista buida");
	}
}

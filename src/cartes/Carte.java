package cartes;

public abstract class Carte {

	protected Carte() {}

	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass();
	}
	
	@Override
	public int hashCode() {
	    return java.util.Objects.hash(getClass());
	}
}

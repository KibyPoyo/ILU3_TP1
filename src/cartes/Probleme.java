package cartes;

public abstract class Probleme extends Carte implements Comparable<Probleme> {
	private Type type;

	protected Probleme(Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
	    return super.equals(obj) && type == ((Probleme)obj).type;
	}
	
	@Override
	public int hashCode() {
	    return java.util.Objects.hash(type);
	}
	
	@Override
	public int compareTo(Probleme probleme) {
		return probleme.getType().compareTo(type);
	}
}
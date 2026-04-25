package cartes;

public class Borne extends Carte implements Comparable<Borne> {
	private int km;

	public Borne(int km) {
		super();
		this.km = km;
	}
	
	public int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return km + "KM";
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && km == ((Borne)obj).getKm();
	}
	
	@Override
	public int compareTo(Borne borne) {
		return Integer.compare(borne.getKm(),km);
	}
}

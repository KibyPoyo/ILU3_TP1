package cartes;

public class Configuration{
	private int nbExemplaires;
	private Carte carte;
	
	public Configuration(Carte carte, int nbExemplaires) {
		super();
		this.nbExemplaires = nbExemplaires;
		this.carte = carte;
	}
	
	public int getNbExemplaires() {
		return nbExemplaires;
	}

	public Carte getCarte() {
		return carte;
	}

	@Override
	public String toString() {
		return nbExemplaires + " " + carte;
	}
	


}

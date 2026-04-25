package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup implements Comparable<Coup> {
	private Joueur joueurSource;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurSource, Joueur joueurCible, Carte carteJouee) {
		this.joueurSource = joueurSource;
		this.joueurCible = joueurCible;
		this.carteJouee = carteJouee;
	}
	
	public Joueur getJoueurSource() {
		return joueurSource;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}

	public boolean estValide() {
		return joueurCible == null
			|| (joueurCible == joueurSource && !(carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) && joueurSource.estDepotAutorise(carteJouee))
			|| (joueurCible != joueurSource && (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) && joueurCible.estDepotAutorise(carteJouee));
	}
	
	@Override
	public int compareTo(Coup coup) {
	    Joueur cibleDuCoup = coup.getJoueurCible();
	    if (joueurSource.equals(joueurCible) && joueurSource.equals(cibleDuCoup)) {
	        return 0;
	    }
	    if (joueurCible == null && cibleDuCoup == null) {
	        return 0;
	    }
	    if (joueurSource.equals(this.joueurCible)) {
	        return -1;
	    }
	    if (joueurSource.equals(cibleDuCoup)) {
	        return 1;
	    }
	    if (joueurCible != null && cibleDuCoup == null) {
	        return -1;
	    }
	    if (joueurCible == null) {
	        return 1;
	    }
	    return joueurCible.compareTo(cibleDuCoup);
	}
	
	@Override
	public String toString() {
		if (joueurCible == null) {
			return "defausse la carte " + carteJouee.toString();
		}
		return "depose la carte " + carteJouee.toString() + " dans la zone de jeu de " + joueurCible.getNom();
	}
	
}

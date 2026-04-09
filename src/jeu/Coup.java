package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur joueurSource;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurSource, Joueur joueurCible, Carte carteJouee) {
		this.joueurSource = joueurSource;
		this.joueurCible = joueurCible;
		this.carteJouee = carteJouee;
	}

	public boolean estValide() {
		return joueurCible == null
			|| (joueurCible == joueurSource && !(carteJouee instanceof Attaque || carteJouee instanceof Limite))
			|| (joueurCible != joueurSource && (carteJouee instanceof Attaque || carteJouee instanceof Limite));
	}
	
	
}

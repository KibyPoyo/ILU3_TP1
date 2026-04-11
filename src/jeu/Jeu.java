package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<>();
	private Iterator<Joueur> itJoueurs = joueurs.iterator();
	private static final int NBCARTES = 6;

	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, jdc.donnerCartes());
		listeCartes = GestionCartes.melanger(listeCartes);
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	public void inscrire(Joueur joueurAInscrire) {
		joueurs.addLast(joueurAInscrire);
	}
	
	public void inscrire(Collection<Joueur> joueursAInscrire) {
		for (Joueur joueur : joueursAInscrire) {
			inscrire(joueur);
		}
	}
	
	public void distribuerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				Carte carte = sabot.piocher();
				joueur.donner(carte);
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
	    StringBuilder compteRendu = new StringBuilder();
	    
	    Carte cartePiochee = joueur.prendreCarte(sabot);
	    compteRendu.append("Le joueur " + joueur.getNom() + " a pioche " + cartePiochee + "\n");
	    compteRendu.append("Il a dans sa main : " + joueur.afficherMain() + "\n");

	    Coup coup = joueur.choisirCoup(joueurs);
	    Carte carteJouee = coup.getCarteJouee();
	    Joueur joueurCible = coup.getJoueurCible();
	    
	    if (joueurCible == null) {
	        sabot.ajouterCarte(carteJouee);
	        compteRendu.append(joueur.getNom() + " depose la carte " + carteJouee + " dans la defausse\n");
	    } else {
	        joueurCible.deposer(carteJouee);
	        String zone = (joueur.equals(joueurCible)) ? "sa zone de jeu" : "la zone de jeu de " + joueurCible.getNom();
	        compteRendu.append(joueur.getNom() + " depose la carte " + carteJouee + " dans " + zone + "\n");
	    }

	    joueur.retirerDeLaMain(carteJouee);
	    return compteRendu.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
	    if (joueurs.isEmpty()) return null;
	    if (!itJoueurs.hasNext()) itJoueurs = joueurs.iterator();
	    return itJoueurs.next();
	}

	public String lancer() {
		StringBuilder compteRendu = new StringBuilder();
		Joueur joueur = null;
		while (joueur == null || (joueur.donnerKmParcourus() < 1000 && !sabot.estVide())) {
			joueur = donnerJoueurSuivant();
			compteRendu.append(jouerTour(joueur) + "\n");
		}
		if (joueur.donnerKmParcourus() >= 1000) {
			compteRendu.append("Victoire de " + joueur.getNom() + " !\n");
		} else if (sabot.estVide()) {
			compteRendu.append("Match nul : le sabot est vide.\n");
	    }
		return compteRendu.toString();
	}
}

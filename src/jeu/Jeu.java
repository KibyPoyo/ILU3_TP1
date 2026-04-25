package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

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
	    compteRendu.append("C'est au tour du joueur " + joueur.getNom() + "\n");
	    compteRendu.append("A pioche           : " + cartePiochee + "\n");
	    compteRendu.append(joueur.afficherEtatJoueur());

	    Coup coup = joueur.choisirCoup(joueurs);
	    Carte carteJouee = coup.getCarteJouee();
	    Joueur joueurCible = coup.getJoueurCible();
	    if (joueurCible == null) {
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
		Joueur joueurCourant = null;
		do {
			joueurCourant = donnerJoueurSuivant();
			compteRendu.append(jouerTour(joueurCourant) + "\n");
		} while ((joueurCourant.donnerKmParcourus() < 1000 && !sabot.estVide()));
		
		List<Joueur> classement = classement();
		compteRendu.append("Classement final : \n");
		int rang = 1;
		for (Joueur joueur : classement) {
			compteRendu.append("- " + rang);
			if (rang == 1) compteRendu.append("er ");
			else compteRendu.append("eme");
			compteRendu.append(" : " + joueur.getNom() + " avec " + joueur.donnerKmParcourus() + "/1000km\n");
			rang++;
		}
		
		if (classement.get(0).donnerKmParcourus() >= 1000) {
			compteRendu.append("Victoire de " + classement.get(0).getNom() + " !\n");
		} else if (sabot.estVide()) {
			compteRendu.append("Personne n'a pu finir le parcours : le sabot est vide.\n");
	    }
		return compteRendu.toString();
	}
	
	public List<Joueur> classement() {
		TreeSet<Joueur> treeSet = new TreeSet<>(new Comparator<Joueur>() {
			@Override
	        public int compare(Joueur joueur1, Joueur joueur2) {
				int km1 = joueur1.donnerKmParcourus();
				int km2 = joueur2.donnerKmParcourus();
				
				if (km1 != km2) return Integer.compare(km2, km1);
				return joueur1.getNom().compareTo(joueur2.getNom());
			}
		});
		
		treeSet.addAll(joueurs);
		
		return new ArrayList<>(treeSet);
	}
}

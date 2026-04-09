package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;

	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}

	public String getNom() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}

	public void donner(Carte carte) {
		main.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		Carte carte = sabot.piocher();
		if (carte == null) {
			return null;
		} else {
			main.prendre(carte);
			return carte;
		}
	}

	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}

	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}

	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}

	public Set<Coup> coupsPossibles(Set<Joueur> participants)  {
		HashSet<Coup> coups = new HashSet<>();
		MainJoueur mainJoueur = this.getMain();
		for (Iterator<Carte> it = mainJoueur.iterator(); it.hasNext();) {
			Carte carte = it.next();
			for (Joueur joueurCible : participants) {
				Coup coup = new Coup(this, joueurCible, carte);
				if (coup.estValide()) {
					coups.add(coup);
				}
			}
		}
		return coups;
	}
	
	public Set<Coup> coupsDefausse() {
		HashSet<Coup> coups = new HashSet<>();
		MainJoueur mainJoueur = this.getMain();
		for (Iterator<Carte> it = mainJoueur.iterator(); it.hasNext();) {
			Carte carte = it.next();
			Coup coupDefausse = new Coup(this, null, carte);
			coups.add(coupDefausse);
		}
		return coups;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() && nom.equals(((Joueur) obj).getNom());
	}

	@Override
	public int hashCode() {
	    return java.util.Objects.hash(nom);
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + "]";
	}
}

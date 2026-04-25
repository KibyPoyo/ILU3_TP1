package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cartes.Botte;
import cartes.Carte;
import strategie.Strategie;

public class Joueur implements Comparable<Joueur> {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main = new MainJoueur();
	private Strategie strategie = new Strategie(){};

	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}

	public String getNom() {
		return nom;
	}
	
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
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
		for (Iterator<Carte> it = main.iterator(); it.hasNext();) {
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
		for (Iterator<Carte> it = main.iterator(); it.hasNext();) {
			Carte carte = it.next();
			Coup coupDefausse = new Coup(this, null, carte);
			coups.add(coupDefausse);
		}
		return coups;
	}

	public void retirerDeLaMain(Carte carte) {
		for (Iterator<Carte> it = main.iterator(); it.hasNext();) {
			Carte carteCurseur = it.next();
			if (carte.equals(carteCurseur)) {
				main.jouer(carte);
				break;
			}
		}
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> setCoupsPossibles = coupsPossibles(participants);
		if (!setCoupsPossibles.isEmpty()) {
			return strategie.selectionnerCoup(setCoupsPossibles);
		} else {
			Set<Coup> setCoupsDefausse = coupsDefausse();
			return strategie.selectionnerDefausse(setCoupsDefausse);
		}
	}
	
	public String afficherEtatJoueur() {
		StringBuilder etat = new StringBuilder();
		etat.append("Bottes             : " + zoneDeJeu.toStringBottes());
		etat.append("\nLimitation 50      : " + (zoneDeJeu.donnerLimitationVitesse() == 50));
		etat.append("\nEtat pile bataille : " + donnerSommetPile());
		etat.append("\nMain               : " + main.toString());
		etat.append("\nA parcouru         : " + donnerKmParcourus() + "/1000km\n");
		return etat.toString();
	}
	
	public String afficherMain() {
	    return main.toString();
	}
	
	public Carte donnerSommetPile() {
		return zoneDeJeu.sommetPileBataille();
	}
	
	public Set<Botte> donnerBottes() {
		return zoneDeJeu.getBottes();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() && nom.equals(((Joueur) obj).getNom());
	}
	
	@Override
	public int compareTo(Joueur joueur) {
		int differenceKm = Integer.compare(donnerKmParcourus(), joueur.donnerKmParcourus());
		if (differenceKm != 0) {
			return differenceKm;
		}
		return getNom().compareTo(joueur.getNom());
	}

	@Override
	public int hashCode() {
	    return java.util.Objects.hash(nom);
	}

	@Override
	public String toString() {
		return "Joueur " + nom;
	}
}

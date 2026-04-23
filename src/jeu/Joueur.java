package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main = new MainJoueur();
	private Random random = new Random();

	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}

	public String getNom() {
		return nom;
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
	
	private <T> T choisirValeurAleatoireDansSet(Set<T> set) {
		List<T> liste = new ArrayList<>(set);
		int i = random.nextInt(liste.size());
		return liste.get(i);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> setCoupsPossibles = coupsPossibles(participants);
		if (!setCoupsPossibles.isEmpty()) {
			return choisirValeurAleatoireDansSet(setCoupsPossibles);
		} else {
			Set<Coup> setCoupsDefausse = coupsDefausse();
			return choisirValeurAleatoireDansSet(setCoupsDefausse);
		}
	}
	
	public String afficherEtatJoueur() {
		StringBuilder etat = new StringBuilder();
		etat.append("Bottes             : " + zoneDeJeu.toStringBottes());
		etat.append("\nLimitation 50      : " + (zoneDeJeu.donnerLimitationVitesse() == 50));
		etat.append("\nEtat pile bataille : " + zoneDeJeu.sommetPileBataille());
		etat.append("\nMain               : " + main.toString());
		etat.append("\nA parcouru         : " + donnerKmParcourus() + "/1000km\n");
		return etat.toString();
	}
	
	public String afficherMain() {
	    return main.toString();
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
		return "Joueur " + nom;
	}
}

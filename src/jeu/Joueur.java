package jeu;

import cartes.Borne;
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
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() && nom.equals(((Joueur) obj).getNom());
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + "]";
	}
}

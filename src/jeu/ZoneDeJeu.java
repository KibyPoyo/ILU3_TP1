package jeu;

import java.util.ArrayList;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private ArrayList<Limite> pileLimites = new ArrayList<Limite>();
	private ArrayList<Bataille> pileBataille = new ArrayList<Bataille>();
	private ArrayList<Borne> collecBornes = new ArrayList<Borne>();

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite ) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int total = 0;
		for (Borne borne : collecBornes) {
			total += borne.getKm();
		}
		return total;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			collecBornes.add(borne);
		} else if (carte instanceof Limite limite) {
			pileLimites.add(0, limite);
		} else if (carte instanceof Bataille bataille) {
			pileBataille.add(0, bataille);
	    } else {
	        throw new IllegalArgumentException("Type de carte non reconnu : " + carte.getClass());
	    }
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && pileBataille.get(0).equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotFeuVertAutorise() {
		return pileBataille.isEmpty() || pileBataille.get(0).equals(Cartes.FEU_ROUGE)
				|| !pileBataille.get(0).equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && donnerKmParcourus() < 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		return (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite)) 
                 || (limite instanceof FinLimite && pileLimites.get(0) instanceof DebutLimite);
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} else if (bataille instanceof Parade) {
			return (bataille.equals(Cartes.FEU_VERT) && estDepotFeuVertAutorise())
				|| (!bataille.equals(Cartes.FEU_VERT) && (!pileBataille.isEmpty() && pileBataille.get(0).equals(bataille)));
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
	    } else {
	        throw new IllegalArgumentException("Type de carte non reconnu : " + carte.getClass());
	    }
	}
}

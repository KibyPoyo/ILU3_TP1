package jeu;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private LinkedList<Limite> pileLimites = new LinkedList<>();
	private LinkedList<Bataille> pileBataille = new LinkedList<>();
	private LinkedList<Borne> collecBornes = new LinkedList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite || estPrioritaire()) {
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
		switch (carte) {
		    case Borne borne -> collecBornes.add(borne);
		    case Limite limite -> pileLimites.add(0, limite);
		    case Bataille bataille -> pileBataille.add(0, bataille);
		    case Botte botte -> bottes.add(botte);
		    default -> throw new IllegalArgumentException("Type de carte non reconnu : " + carte.getClass());
  }
	}
	
	private boolean possedeBottePour(Bataille bataille) {
		for (Botte botte : bottes) {
	        if (botte.getType().equals(bataille.getType())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean peutAvancer() {
		boolean prio = estPrioritaire();
		if (pileBataille.isEmpty()) {
			return estPrioritaire();
		} else {
			Bataille sommet = pileBataille.get(0);
			if (sommet.equals(Cartes.FEU_VERT)) return true;
			return prio && (sommet.equals(Cartes.FEU_ROUGE)
	                 || (sommet instanceof Parade)
	                 || (sommet instanceof Attaque attaque && possedeBottePour(attaque)));
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		return !estPrioritaire()
				&& (pileBataille.isEmpty() || pileBataille.get(0).equals(Cartes.FEU_ROUGE)
					|| !pileBataille.get(0).equals(Cartes.FEU_VERT));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && donnerKmParcourus() < 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		return !estPrioritaire()
				&& (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite)) 
                    || (limite instanceof FinLimite && pileLimites.get(0) instanceof DebutLimite);
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (possedeBottePour(bataille)) {
			return false;
		} else if (bataille instanceof Attaque) {
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
		} else if (carte instanceof Botte) {
			return true;
	    } else {
	        throw new IllegalArgumentException("Type de carte non reconnu : " + carte.getClass());
	    }
	}
	
	public boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
}

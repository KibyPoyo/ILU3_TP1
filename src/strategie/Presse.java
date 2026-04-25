package strategie;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import cartes.Attaque;
import cartes.Botte;
import cartes.Carte;
import cartes.Type;
import jeu.Coup;
import jeu.Joueur;

public interface Presse extends Strategie, Priorite {
	Random RANDOM = new Random();

    @Override
    default Set<Coup> trierCoups(Set<Coup> coups) {
        Set<Coup> coupsTries = new TreeSet<>(new Comparator<Coup>() {
        	
        	@Override
            public int compare(Coup c1, Coup c2) {
                int result = c1.compareTo(c2);
                if (result == 0) {
                    return comparerCartes(c1.getJoueurCible(), c1.getCarteJouee(), c2.getCarteJouee());
                }
                return result;
            }
        	
        	private int comparerCartes(Joueur joueur, Carte carte1, Carte carte2) {			
        		Integer comparaison = null;
        		
        		comparaison = donnerPrioriteLimites(carte1, carte2);
        		if(comparaison != null) {
        			return comparaison;
        		}			
        		
        		comparaison = donnerPrioriteBornes(carte1, carte2);
        		if(comparaison != null) {
        			return comparaison;
        		}
        		
        		if (joueur == null) {
        	        return RANDOM.nextBoolean() ? 1 : -1;
        	    }
        		Carte carteSommet = joueur.donnerSommetPile();
        		if(carteSommet instanceof Attaque attaque) {

        			Type typeProbleme = attaque.getType();
        			if(joueur.donnerBottes().contains(new Botte(typeProbleme))) {
        				typeProbleme = Type.FEU;
        			}
        			
        			comparaison = donnerPrioriteBottes(typeProbleme, carte1, carte2);
        			if(comparaison != null) {
        				return comparaison;
        			}					
        		}
        		
        		comparaison = donnerPrioriteParades(carte1, carte2);
        		if(comparaison != null) {
        			return comparaison;
        		}
        		return RANDOM.nextBoolean() ? 1 : -1;
        	}
        });
        coupsTries.addAll(coups);
        return coupsTries;
    }
}
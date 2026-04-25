package cartes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JeuDeCartes {
	private Map<Carte, Integer> configuration = new LinkedHashMap<>();

    public JeuDeCartes() {
        configuration.put(new Borne(25), 10);
        configuration.put(new Borne(50), 10);
        configuration.put(new Borne(75), 10);
        configuration.put(new Borne(100), 12);
        configuration.put(new Borne(200), 4);
        
        configuration.put(new Parade(Type.FEU), 14);
        configuration.put(new FinLimite(), 6);
        configuration.put(new Parade(Type.ESSENCE), 6);
        configuration.put(new Parade(Type.CREVAISON), 6);
        configuration.put(new Parade(Type.ACCIDENT), 6);
        
        configuration.put(new Attaque(Type.FEU), 5);
        configuration.put(new DebutLimite(), 4);
        configuration.put(new Attaque(Type.ESSENCE), 3);
        configuration.put(new Attaque(Type.CREVAISON), 3);
        configuration.put(new Attaque(Type.ACCIDENT), 3);
        
        configuration.put(new Botte(Type.FEU), 1);
        configuration.put(new Botte(Type.ESSENCE), 1);
        configuration.put(new Botte(Type.CREVAISON), 1);
        configuration.put(new Botte(Type.ACCIDENT), 1);
    }
    
    public String affichageJeuDeCartes() {
    	StringBuilder texte = new StringBuilder();
		for (Entry<Carte, Integer> entry : configuration.entrySet()) {
            texte.append(entry.getValue() + " " + entry.getKey() + "\n");
        }
		return texte.toString();
	}
    
    public Carte[] donnerCartes() {
    	int nombreCartes = 0;
		for (int nb : configuration.values()) {
			nombreCartes += nb;
        }
    	Carte[] cartes = new Carte[nombreCartes];
    	
		int indexCarte = 0;
		for (Entry<Carte, Integer> entry : configuration.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                cartes[indexCarte++] = entry.getKey();
            }
        }		
		return cartes;
	}
    
    public boolean checkCount() {
        Carte[] cartes = donnerCartes();
        
        for (Entry<Carte, Integer> entry : configuration.entrySet()) {
            int compteur = 0;
            for (Carte carte : cartes) {
                if (carte.equals(entry.getKey())) compteur++;
            }
            if (compteur != entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
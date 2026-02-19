package cartes;

public class JeuDeCartes {
    private Configuration[] typesDeCartes = new Configuration[] {
        new Configuration(new Borne(25), 10),
        new Configuration(new Borne(50), 10),
        new Configuration(new Borne(75), 10),
        new Configuration(new Borne(100), 12),
        new Configuration(new Borne(200), 4),
        
        new Configuration(new Parade(Type.FEU), 14),
        new Configuration(new FinLimite(), 6),
        new Configuration(new Parade(Type.ESSENCE), 6),
        new Configuration(new Parade(Type.CREVAISON), 6),
        new Configuration(new Parade(Type.ACCIDENT), 6),
        
        new Configuration(new Attaque(Type.FEU), 5),
        new Configuration(new DebutLimite(), 4),
        new Configuration(new Attaque(Type.ESSENCE), 3),
        new Configuration(new Attaque(Type.CREVAISON), 3),
        new Configuration(new Attaque(Type.ACCIDENT), 3),
        
        new Configuration(new Botte(Type.FEU), 1),
        new Configuration(new Botte(Type.ESSENCE), 1),
        new Configuration(new Botte(Type.CREVAISON), 1),
        new Configuration(new Botte(Type.ACCIDENT), 1)
    };
    
    public String affichageJeuDeCartes() {
    	StringBuilder texte = new StringBuilder();
		for (int i = 0; i < typesDeCartes.length; i++) {
			texte.append(typesDeCartes[i] + "\n");
		}
		return texte.toString();
	}
    
    public Carte[] donnerCartes() {
    	int nombreCartes = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			nombreCartes += typesDeCartes[i].getNbExemplaires();
		}
    	Carte[] cartes = new Carte[nombreCartes];
    	
		int indexCarte = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
	        int nbExemplaires = typesDeCartes[i].getNbExemplaires();
	        Carte carte = typesDeCartes[i].getCarte();
	        for (int j = 0; j < nbExemplaires; j++) {
	            cartes[indexCarte] = carte; // Attention lecture seulement
	            indexCarte++;
	        }
	    }
		
		return cartes;
	}
    
    
    public static void main(String[] args) {
    	JeuDeCartes jdc = new JeuDeCartes();
    	String texte = jdc.affichageJeuDeCartes();
    	System.out.println(texte);
	}
}
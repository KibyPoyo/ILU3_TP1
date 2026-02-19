package cartes;

public enum Type {
	FEU("Feu rouge","Feu vert", "Prioritaire"),
	ESSENCE("Panne d'essence", "Bidon d'essence", "Citerne"),
    CREVAISON("Crevaison", "Roue de secours", "Increvable"),
    ACCIDENT("Accident", "Réparations", "As du volant");

	private final String attaque;
    private final String parade;
    private final String botte;

    Type(String attaque, String parade, String botte) {
        this.attaque = attaque;
        this.parade = parade;
        this.botte = botte;
    }

	public String getAttaque() {
		return attaque;
	}

	public String getParade() {
		return parade;
	}

	public String getBotte() {
		return botte;
	}
}

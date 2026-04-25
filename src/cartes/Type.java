package cartes;

public enum Type {
    ACCIDENT("Accident", "Réparations", "As du volant"),
    CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	ESSENCE("Panne d'essence", "Bidon d'essence", "Citerne"),
	FEU("Feu rouge","Feu vert", "Prioritaire");

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

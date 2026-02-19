package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {

    public static boolean checkCount(JeuDeCartes jeu) {
        return jeu.donnerCartes().length == 106;
    }

    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        System.out.print(jeu.affichageJeuDeCartes());

        if (!checkCount(jeu)) {
            System.out.println("[KO] Nombre de cartes incorrectes");
        } else {
        	System.out.println("[OK] Nombre de cartes correctes");
        }
    }
}
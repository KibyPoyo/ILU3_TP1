package tests_fonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {

    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();

        if (jeu.donnerCartes().length != 106) {
            System.out.println("[KO] Nombre de cartes incorrectes");
        } else {
        	System.out.println("[OK] Nombre de cartes correctes");
        }
        
        if (jeu.checkCount()) {
            System.out.println("[OK] La configuration est respectée.");
        } else {
            System.out.println("[KO] Le nombre d'exemplaires est incorrect.");
        }
    }
}
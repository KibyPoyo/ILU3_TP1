package tests_fonctionnels;

import jeu.Jeu;
import jeu.Joueur;
import jeu.ZoneDeJeu;

public class TestJeu {

    public static void main(String[] args) {
        Jeu jeu = new Jeu();

        Joueur jack = new Joueur("Jack", new ZoneDeJeu());
        Joueur bill = new Joueur("Bill", new ZoneDeJeu());
        Joueur luffy = new Joueur("Luffy", new ZoneDeJeu());

        jeu.inscrire(jack);
        jeu.inscrire(bill);
        jeu.inscrire(luffy);

        jeu.distribuerCartes();

        System.out.println(jeu.lancer());
    }
}
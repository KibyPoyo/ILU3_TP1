package tests_fonctionnels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String args[]) {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste mélangée sans erreur ? " + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));

		System.out.println("\nListes d'entiers :");		
		testListeEntiers(new ArrayList<>(Arrays.asList()));
        testListeEntiers(new ArrayList<>(Arrays.asList(1, 1, 2, 1, 3)));
        testListeEntiers(new ArrayList<>(Arrays.asList(1, 4, 3, 2)));
        testListeEntiers(new ArrayList<>(Arrays.asList(1, 1, 2, 3)));
    }

    public static void testListeEntiers(List<Integer> liste) {
        System.out.println("Liste initiale : " + liste);
        System.out.println("Rassemblement correct ? " + GestionCartes.verifierRassemblement(liste));
        
        List<Integer> resultat = GestionCartes.rassembler(liste);
        System.out.println("Après rassemblement : " + resultat);
        System.out.println("Rassemblement correct ? " + GestionCartes.verifierRassemblement(resultat));
        System.out.println("");
	}

}
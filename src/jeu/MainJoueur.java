package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
	private List<Carte> main = new ArrayList<>();
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}

	@Override
	public String toString() {
		return "MainJoueur [main=" + main + "]";
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}

}

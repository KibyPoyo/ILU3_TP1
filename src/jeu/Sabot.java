package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes = 0;
	private int nbOperations = 0;
	
	public Sabot(Carte[] cartes) {
		super();
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes < 106) {
			cartes[nbCartes] = carte;
			nbCartes++;
			nbOperations++;
		}
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur = iterator();
		if (iterateur.hasNext()) {
			Carte carte = iterateur.next();
			iterateur.remove();
			return carte;
		}
		return null;
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationReference = nbOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verifierConcurrence();
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Carte carte = cartes[indiceIterateur];
			indiceIterateur++;
			nextEffectue = true;
			return carte;
		}
		
		@Override
		public void remove() {
			verifierConcurrence();
			if (nbCartes == 0 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur-1; i < cartes.length-1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			nbOperations++;
			nbOperationReference++;
			indiceIterateur--;
			nbCartes--;
			
		}
		
		private void verifierConcurrence() {
			if (nbOperations != nbOperationReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
}

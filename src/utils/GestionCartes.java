package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random random = new Random();
	
	// Version sans iterator
	public static <T> T extraire(List<T> liste) {
        int index = random.nextInt(liste.size());
        return liste.remove(index);
    }

	// Version avec iterator
	public static <T> T extraireIterator(List<T> liste) {
        int index = random.nextInt(liste.size());
        ListIterator<T> iterator = liste.listIterator();
        
        T element = null;
        for (int i = 0; i <= index; i++) {
            element = iterator.next();
        }
        iterator.remove();
        
        return element;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> listeMelangee = new ArrayList<T>();
		
		while (!liste.isEmpty()) {
			T carte = extraire(liste);
			listeMelangee.add(carte);
		}
		
		return listeMelangee;
	}
	
	// Optimisation possible : parcourir une fois chaque élément DISTINCT
	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		if (liste1.size() != liste2.size()) return false;
		
		for (T carte : liste1) {
			if (Collections.frequency(liste1, carte) != Collections.frequency(liste2, carte)) {
				return false;
			}
		}
		
		return true;
	}


	public static <T> List<T> rassembler(List<T> liste) {
		List<T> listeRassemblee = new ArrayList<T>();
				
		while (!liste.isEmpty()) {
			T carteReference = liste.remove(0);
			listeRassemblee.add(carteReference);
			
			for (ListIterator<T> iterator = liste.listIterator(); iterator.hasNext();) {
				T carte = iterator.next();
				if (carteReference.equals(carte)) {
					iterator.remove();
					listeRassemblee.add(carte);
				}
			}
		}
		
		return listeRassemblee;
	}
	
//	private static <T> ListIterator<T> dupliquerListIterator(ListIterator<T> iteratorSource, List<T> liste) {
//		ListIterator<T> iteratorDuplique = liste.listIterator();				
//		int cible = iteratorSource.nextIndex(); 
//        while (iteratorDuplique.nextIndex() < cible) {
//        	iteratorDuplique.next();
//        }
//        return iteratorDuplique;
//	}
	
	private static <T> boolean elementExisteDansListe(List<T> liste, T elementCible, int startIndex) {
		ListIterator<T> iterator = liste.listIterator();
		if (startIndex >= liste.size()) {
			return false;
		}
		
        while (iterator.nextIndex() < startIndex) {
        	iterator.next();
        }
        
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (element.equals(elementCible)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		ListIterator<T> iterator1 = liste.listIterator();
		if (!iterator1.hasNext()) return true;
		
		T carteReference = iterator1.next();
		while (iterator1.hasNext()) {
			T carteEtudiee = iterator1.next();
			
			if (!carteReference.equals(carteEtudiee) && elementExisteDansListe(liste, carteReference, iterator1.nextIndex())) {
				return false;
			}
			
			carteReference = carteEtudiee;
		}
		
		return true;
	}
	
	
	
}

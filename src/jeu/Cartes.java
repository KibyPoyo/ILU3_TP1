package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Parade;
import cartes.Type;

public interface Cartes {
    Botte PRIORITAIRE = new Botte(Type.FEU);
    Attaque FEU_ROUGE = new Attaque(Type.FEU);
    Parade FEU_VERT = new Parade(Type.FEU);
}

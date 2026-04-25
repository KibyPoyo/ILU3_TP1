package strategie;

import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;

public interface Priorite {

	public default Integer donnerPrioriteBornes(Carte carte1, Carte carte2) {
		if (carte1 instanceof Borne borne1 && carte2 instanceof Borne borne2) {
			return borne1.compareTo(borne2);
		}
		if (carte1 instanceof Borne) {
			return 1;
		}
		if (carte2 instanceof Borne) {
			return -1;
		}
		return null;
	}

	public default Integer donnerPrioriteParades(Carte carte1, Carte carte2) {
		if (carte1 instanceof Parade parade1 && carte2 instanceof Parade parade2) {
			return parade1.compareTo(parade2);
		}
		if (carte1 instanceof Parade) {
			return 1;
		}
		if (carte2 instanceof Parade) {
			return -1;
		}
		return null;
	}

	public default Integer donnerPrioriteBottes(Type typeProbleme, Carte carte1, Carte carte2) {
		if (carte1 instanceof Botte botte && botte.getType() == typeProbleme) {
			return 1;
		}
		if (carte2 instanceof Botte botte && botte.getType() == typeProbleme) {
			return -1;
		}
		return null;
	}

	public default Integer donnerPrioriteLimites(Carte carte1, Carte carte2) {
		if (carte1 instanceof FinLimite) {
			return 1;
		}
		if (carte2 instanceof FinLimite) {
			return -1;
		}
		return null;
	}
}

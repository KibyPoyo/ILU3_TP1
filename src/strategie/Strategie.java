package strategie;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import jeu.Coup;

public interface Strategie {

    default Set<Coup> trierCoups(Set<Coup> coups) {
    	if (coups.isEmpty()) {
    		return Collections.emptySet();
    	}
        Set<Coup> coupsTries = new TreeSet<>(new Comparator<Coup>() {
            private Random rand = new Random();

            @Override
            public int compare(Coup c1, Coup c2) {
                if (c1.equals(c2)) return 0;
                return rand.nextBoolean() ? 1 : -1;
            }
        });
        coupsTries.addAll(coups);
        return coupsTries;
    }
    
    default Coup selectionnerCoup(Set<Coup> coups) {
        if (coups.isEmpty()) {
            return null;
        }
        Set<Coup> coupsTries = trierCoups(coups);
        return ((TreeSet<Coup>)coupsTries).first();
    }
    
    default Coup selectionnerDefausse(Set<Coup> coups) {
        if (coups.isEmpty()) {
            return null;
        }
        Set<Coup> coupsTries = trierCoups(coups);
        return ((TreeSet<Coup>)coupsTries).last();
    }
}
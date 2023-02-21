package bataille;

import java.util.LinkedList;
import protagoniste.EtreVivant;

public class Camp<E extends EtreVivant> {
	private LinkedList<EtreVivant> liste = new LinkedList<>();
	
	public void ajouter(EtreVivant etreVivant) {
		if (!liste.contains(etreVivant)) {
			liste.add(etreVivant);
		}
	}
	
	public void eliminer(EtreVivant etreVivant) {
		if (liste.contains(etreVivant)) {
			liste.remove(etreVivant);
		}
	}

	@Override
	public String toString() {
		return "Camp [liste=" + liste + "]";
	}
	
	
}

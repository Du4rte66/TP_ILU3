package bataille;

import java.util.Iterator;
import java.util.LinkedList;
import protagoniste.EtreVivant;

public class Camp<E extends EtreVivant> implements Iterable<E> {
	private LinkedList<E> liste = new LinkedList<>();
	
	public void ajouter(E etreVivant) {
		if (!liste.contains(etreVivant)) {
			liste.add(etreVivant);
		}
	}
	
	public void eliminer(E etreVivant) {
		if (liste.contains(etreVivant)) {
			liste.remove(etreVivant);
		}
	}

	@Override
	public String toString() {
		return "Camp [liste=" + liste + "]";
	}
	
	@Override
	public Iterator<E> iterator() {
		return liste.iterator();
	}
}

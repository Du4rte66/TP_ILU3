package bataille;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

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
	
	public int nbCombattants() {
		  return liste.size();
		}

	public E selectionner() {
		Random randomGenerateur = new Random();
		int numeroCombattant = randomGenerateur.nextInt(liste.size());
		return liste.get(numeroCombattant);
	}
}

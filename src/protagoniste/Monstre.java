package protagoniste;

import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;
import java.util.NoSuchElementException;

import attaque.Pouvoir;
import bataille.Bataille;

public class Monstre<P extends Pouvoir> extends EtreVivant{
	private P[] attaques;
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;
	private Random rand = new Random();
	private GestionAttaque gestionAttaque;

	@SafeVarargs
	public Monstre(int forceDeVie, String nom, ZoneDeCombat zoneDeCombat, Domaine domaine, P... attaques) {
		super(forceDeVie, nom);
		this.zoneDeCombat = zoneDeCombat;
		this.domaine = domaine;
		this.attaques = attaques;
	}
	
	public ZoneDeCombat getZoneDeCombat() {
		return this.zoneDeCombat;
	}

	public Domaine getDomaine() {
		return this.domaine;
	}
	
	public void entreEnCombat() {
		for(P attaque : attaques) {
			attaque.regenerPouvoir();
		}
		gestionAttaque = new GestionAttaque(attaques);
	}
	
	public P attaque() {
		P temp = this.gestionAttaque.next();
		System.out.println(temp);
		return temp;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + "Monstre [attaques=" + Arrays.toString(attaques) + ", zone de combat=" + zoneDeCombat + ", domaine="
				+ domaine + "]]";
	}
	
	@Override
	public void rejointBataille(Bataille bataille) {
		this.rejointBataille(bataille);
		this.bataille.ajouter(this);
	}
	
	@Override
	public void mourir() {
		this.bataille.eliminer(this);
	}

	//classe interne
	private class GestionAttaque implements Iterator<P> {
		private P[] attaquesPossibles;
		private int nbAttaquesPossibles;
		
		public GestionAttaque(P[] attaquesPossibles) {
			this.attaquesPossibles = attaquesPossibles.clone();
			this.nbAttaquesPossibles = attaquesPossibles.length;
		}
		
		public boolean hasNext() {
			//TODO transformer for en boucle i -> nbAttaquesPossibles
			int i = 0;
			int nbPouvoirOperationnel = 0;
			for(P attaque : attaquesPossibles) {
				if(!attaque.isOperationnel()) {
					attaquesPossibles[i] = attaquesPossibles[--nbAttaquesPossibles];
					
				} else {
					nbPouvoirOperationnel++;
				}
				i++;
			}
			return nbPouvoirOperationnel > 0;
		}

		@Override
		public P next() {
			if (hasNext()) {
				System.out.println("Random entre 0 et " + nbAttaquesPossibles);
				int hasard = rand.nextInt(nbAttaquesPossibles);
				
				return this.attaquesPossibles[hasard];
			}
			//si le tableau est vide
			throw new NoSuchElementException();
		}
	}

	
}

package protagoniste;

import java.util.Arrays;
import java.util.Iterator;

import attaque.Pouvoir;

public class Monstre<P extends Pouvoir> extends EtreVivant{
	private P[] attaques;
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;

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

	@Override
	public String toString() {
		return super.toString() + " = Monstre [attaques=" + Arrays.toString(attaques) + ", zoneDeCombat=" + zoneDeCombat + ", domaine="
				+ domaine + "]";
	}

	//classe interne
	private class GestionAttaque implements Iterator<P> {
		private P[] attaquesPossibles;
		private int nbAttaquesPossibles;
		
		public GestionAttaque(P[] attaquesPossibles) {
			this.attaquesPossibles = attaquesPossibles;
			this.nbAttaquesPossibles = attaquesPossibles.length;
		}
		
		public boolean hasNext() {
			int i = 0;
			int nbPouvoirOperationnel = 0;
			for(P attaque : attaquesPossibles) {
				if(!attaque.isOperationnel()) {
					attaquesPossibles[i] = attaquesPossibles[nbAttaquesPossibles - 1];
					nbAttaquesPossibles--;
					
				} else {
					nbPouvoirOperationnel++;
				}
				i++;
			}
			return nbPouvoirOperationnel > 0;
		}

		@Override
		public P next() {
			// TODO Auto-generated method stub
			return null;
		}


	}
}

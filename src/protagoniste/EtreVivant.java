package protagoniste;

import bataille.Bataille;

public abstract class EtreVivant implements Comparable<EtreVivant> {
	protected int forceDeVie;
	protected String nom;
	protected Bataille bataille;
	
	protected EtreVivant(String nom, int forceDeVie) {
		this.nom = nom;
		this.forceDeVie = forceDeVie;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getForceDeVie() {
		return forceDeVie;
	}

	@Override
	public String toString() {
		return "Etre vivant [" + nom + ", " + forceDeVie + " HP, ";
	}
	
	public abstract void mourir();
	
	public void rejointBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	
	@Override
	public int compareTo(EtreVivant etreVivantToCompare) {
		return nom.compareTo(etreVivantToCompare.nom);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EtreVivant) {
			EtreVivant etreVivant = (EtreVivant) obj;
			return nom.equals(etreVivant.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31 * (this.forceDeVie + this.nom.hashCode() + this.bataille.hashCode()); 
	}
	
	public boolean enVie() {
		return forceDeVie > 0;
	}
}

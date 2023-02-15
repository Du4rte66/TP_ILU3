package protagoniste;

import bataille.Bataille;

public abstract class EtreVivant {
	protected int forceDeVie;
	protected String nom;
	protected Bataille bataille;
	
	protected EtreVivant(int forceDeVie, String nom) {
		this.forceDeVie = forceDeVie;
		this.nom = nom;
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
}

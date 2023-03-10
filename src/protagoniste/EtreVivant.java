package protagoniste;

import bataille.Bataille;

public abstract class EtreVivant {
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
	public boolean equals(Object obj) {
		if (obj instanceof EtreVivant) {
			EtreVivant etreVivant = (EtreVivant) obj;
			return nom.equals(etreVivant.nom);
		}
		return false;
	}
}

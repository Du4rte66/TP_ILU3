package protagoniste;

public abstract class EtreVivant {
	protected int forceDeVie;
	protected String nom;
	
	public EtreVivant(int forceDeVie, String nom) {
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
}

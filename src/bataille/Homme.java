package bataille;

import protagoniste.EtreVivant;

public class Homme extends EtreVivant{
	public Homme(String nom) {
		super(70, nom);
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

	@Override
	public String toString() {
		return "Homme [nom=" + nom + ", force de vie=" + forceDeVie + "]";
	}
	
	
}

package protagoniste;

import bataille.Bataille;

public class Homme extends EtreVivant{
	public Homme(String nom) {
		super(nom, 70);
	}
	
	@Override
	public void rejointBataille(Bataille bataille) {
		super.rejointBataille(bataille);
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

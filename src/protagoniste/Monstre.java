package protagoniste;

import java.util.Arrays;
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

	

}

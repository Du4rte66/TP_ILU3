package protagoniste;

import java.util.Arrays;

public class Monstre<P> extends EtreVivant{
	P[] attaques;

	@SafeVarargs
	public Monstre(int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, P... attaques) {
		super(forceDeVie, zoneDeCombat, domaine);
		this.attaques = attaques;
	}
	


	@Override
	public String toString() {
		return "Monstre [zoneDeCombat=" + zoneDeCombat + ", domaine=" + domaine + ", attaques="
				+ Arrays.toString(attaques) + "]";
	}

}

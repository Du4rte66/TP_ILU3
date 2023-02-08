package protagoniste;

import java.util.Arrays;

public class Monstre extends EtreVivant{
	private String[] attaques;

	public Monstre(int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, String... attaques) {
		super(forceDeVie, zoneDeCombat, domaine);
		this.attaques = attaques;
	}
	
	public String[] getAttaques() {
		return attaques;
	}

	@Override
	public String toString() {
		return "Monstre [zoneDeCombat=" + zoneDeCombat + ", domaine=" + domaine + ", attaques="
				+ Arrays.toString(attaques) + "]";
	}

}

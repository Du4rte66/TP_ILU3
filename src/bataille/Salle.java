package bataille;

import protagoniste.ZoneDeCombat;

public class Salle {
	private int numSalle;
	private ZoneDeCombat zoneDeCombat;
	
	public Salle(int numSalle, ZoneDeCombat zoneDeCombat) {
		this.numSalle = numSalle;
		this.zoneDeCombat = zoneDeCombat;
	}

	public int getNumSalle() {
		return numSalle;
	}

	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	@Override
	public String toString() {
		return "Salle n°" + numSalle + " de type de combat " + zoneDeCombat;
	}
	
	
}

package attaque;


import java.util.HashSet;
import java.util.Set;

import protagoniste.ZoneDeCombat;

public abstract class Arme extends ForceDeCombat implements Orderable<Arme>{
	private Set<ZoneDeCombat> zonesDeCombat = new HashSet<>();
	
	protected Arme(int pointDeDegat, String nom, ZoneDeCombat... zonesDeCombat) {
		super(pointDeDegat, nom);
		for(ZoneDeCombat z : zonesDeCombat) {
			this.zonesDeCombat.add(z);
		}
	}
	
	public Set<ZoneDeCombat> getZonesDeCombat() {
		return zonesDeCombat;
	}
	
	@Override
	public int compareTo(Arme armeAComparer) {
		int comparaison = this.getNom().compareTo(armeAComparer.getNom());
		if(comparaison == 0) {
			Boolean b = armeAComparer.isOperationnel();
			return b.compareTo(this.isOperationnel());
		}
		return comparaison;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Arme) {
			Arme arme = (Arme) obj;
			return getPointDeDegat() == arme.getPointDeDegat() && getNom().equals(arme.getNom());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31 * getPointDeDegat() * getNom().hashCode();
	}
}
package protagoniste;

public abstract class EtreVivant {
	protected int forceDeVie;
	protected String nom;
	protected ZoneDeCombat zoneDeCombat;
	protected Domaine domaine;
	
	public EtreVivant(int forceDeVie, String nom, ZoneDeCombat zoneDeCombat, Domaine domaine) {
		this.zoneDeCombat = zoneDeCombat;
		this.domaine = domaine;
		this.forceDeVie = forceDeVie;
	}
	
	public int getForceDeVie() {
		return forceDeVie;
	}

	public ZoneDeCombat getZoneDeCombat() {
		return this.zoneDeCombat;
	}

	public Domaine getDomaine() {
		return this.domaine;
	}

	@Override
	public String toString() {
		return "EtreVivant [zoneDeCombat=" + zoneDeCombat + ", domaine=" + domaine + ", forceDeVie=" + forceDeVie + "]";
	}
	
	
}

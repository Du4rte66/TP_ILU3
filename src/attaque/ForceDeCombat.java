package attaque;

public abstract class ForceDeCombat {
	private int pointDeDegat;
	private String nom;
	protected boolean operationnel = true;
	
	public ForceDeCombat(int pointDeDegat, String nom) {
		this.pointDeDegat = pointDeDegat;
		this.nom = nom;
	}
	
	public int getPointDeDegat() {
		return this.pointDeDegat;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public boolean isOperationnel() {
		return this.operationnel;
	}
	
	public int utiliser() {
		return pointDeDegat;
	}

	@Override
	public String toString() {
		String chaine = "[" + nom + ", " + pointDeDegat + " DMG, ";
		chaine += this.operationnel ? "operationnel]" : "non operationnel]";
		return chaine;
	}
	
	

}

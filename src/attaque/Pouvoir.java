package attaque;

public abstract class Pouvoir extends ForceDeCombat {
	private int nbUtilisationPouvoir;
	private int nbUtilisationPouvoirInitial;
	
	public Pouvoir(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		super(pointDeDegat, nom);
		this.nbUtilisationPouvoir = nbUtilisationPouvoir;
		this.nbUtilisationPouvoirInitial = nbUtilisationPouvoir;
	}
	
	public void regenerPouvoir() {
		this.operationnel = true; //de nouveau opérationnel
		this.nbUtilisationPouvoir = this.nbUtilisationPouvoirInitial; //reset nb utilisations pouvoir
	}
	
	public int utiliser() {
		if(isOperationnel()) {
			this.nbUtilisationPouvoir--;
			if(this.nbUtilisationPouvoir < 1) {
				this.operationnel = false;			
			}
		}
		
		return this.utiliser();
	}
}
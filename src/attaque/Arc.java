package attaque;

import protagoniste.ZoneDeCombat;

public class Arc extends Arme {
	private int nbFlechesRestantes;
	
	public Arc(int nbFlechesRestantes) {
		super(50, "Arc", ZoneDeCombat.AQUATIQUE, ZoneDeCombat.AERIEN, ZoneDeCombat.TERRESTRE);
		this.nbFlechesRestantes = nbFlechesRestantes;
	}
	
	@Override
	public int utiliser() {
		if(isOperationnel()) {
			nbFlechesRestantes--;
			
		}
		if(nbFlechesRestantes < 1 ) {
			operationnel = false;
		}
		
		return super.utiliser();
	}
}
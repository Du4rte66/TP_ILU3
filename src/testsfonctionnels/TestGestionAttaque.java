package testsfonctionnels;

import protagoniste.*;
import attaque.*;

public class TestGestionAttaque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monstre<Feu> dragotenebre = new Monstre<>(200, "dragotenebre", ZoneDeCombat.AERIEN, Domaine.FEU, 
				new Lave(2), new Eclair(3), new BouleDeFeu(6));
		System.out.println(dragotenebre);
	}

}

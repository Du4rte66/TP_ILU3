package testsfonctionnels;

import protagoniste.*;
import attaque.*;

public class TestGestionAttaque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monstre<Feu> dragotenebre = new Monstre<>(200, "dragotenebre", ZoneDeCombat.AERIEN, Domaine.FEU, 
				new Lave(1), new Eclair(1), new BouleDeFeu(2));
		System.out.println(dragotenebre);
		dragotenebre.entreEnCombat();
		System.out.println(dragotenebre.attaque());
		System.out.println(dragotenebre.attaque());
		System.out.println(dragotenebre.attaque());
	}

}

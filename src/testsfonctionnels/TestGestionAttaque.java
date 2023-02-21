package testsfonctionnels;

import protagoniste.*;
import attaque.*;

public class TestGestionAttaque {

	public static void main(String[] args) {
		Monstre<Feu> dragotenebre = new Monstre<>(200, "dragotenebre", ZoneDeCombat.AERIEN, Domaine.FEU, 
				new Lave(1), new Eclair(2), new BouleDeFeu(1));
		System.out.println(dragotenebre);
		dragotenebre.entreEnCombat();
		System.out.println(dragotenebre.attaque().utiliser());
		System.out.println(dragotenebre);
		System.out.println(dragotenebre.attaque().utiliser());
		System.out.println(dragotenebre);
		System.out.println(dragotenebre.attaque().utiliser());
		System.out.println(dragotenebre);
		System.out.println(dragotenebre.attaque().utiliser());
		System.out.println(dragotenebre);


	}

}

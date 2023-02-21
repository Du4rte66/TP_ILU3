package testsfonctionnels;

import protagoniste.*;
import attaque.*;
import bataille.Bataille;
import bataille.Camp;
import livre.AideEcrivain;

public class TestGestionAttaque {

	public static void main(String[] args) {
		Monstre<Feu> dragotenebre = new Monstre<>("dragotenebre", 200, ZoneDeCombat.AERIEN, Domaine.FEU, 
				new Lave(1), new Eclair(3), new BouleDeFeu(4));
		
		Monstre<Tranchant> vampirien = new Monstre<>("vampirien", 10,
				 ZoneDeCombat.AERIEN, Domaine.TRANCHANT, new Morsure(10));
		
		Monstre<Glace> marinsangant = new Monstre<>("marinsangant", 150,
				 ZoneDeCombat.AQUATIQUE, Domaine.GLACE, new PicsDeGlace(10),
				new Tornade(1));
		
		Monstre<Tranchant> guillotimort = new Monstre<>("guillotimort", 80,
				 ZoneDeCombat.TERRESTRE, Domaine.TRANCHANT, new LameAcier(10),
				new Griffe());
		
		System.out.println("Tous les monstres sont là");
		
		Homme thomas = new Homme("Thomas");
		Homme louis = new Homme("Louis");
		Heros arthur = new Heros("Arthur");
		Heros archibald = new Heros("Archibald");
		Homme alain = new Homme("Alain");
		
		System.out.println("Tous les hommes/heros sont là");
		
		Bataille bataille = new Bataille();
		thomas.rejointBataille(bataille);
		louis.rejointBataille(bataille);
		arthur.rejointBataille(bataille);
		archibald.rejointBataille(bataille);
		alain.rejointBataille(bataille);
		dragotenebre.rejointBataille(bataille);
		vampirien.rejointBataille(bataille);
		marinsangant.rejointBataille(bataille);
		guillotimort.rejointBataille(bataille);
		Camp<Homme> campsHumain = bataille.getCampHumains();
		Camp<Monstre<?>> campsMonstre = bataille.getCampMonstres();
		System.out.println("**** TP2 ****");
		System.out.println("\ncamp des humains :\n" + campsHumain);
		System.out.println("\ncamp des monstres :\n" + campsMonstre); 
		
		AideEcrivain aideEcrivain = new AideEcrivain(bataille);
		System.out.println("\nvisualisation des forces humaines :\n"
		 + aideEcrivain.visualiserForcesHumaines());
		
		thomas.mourir();
		System.out.println("\ncamp des humains :\n" + campsHumain);
		dragotenebre.mourir();
		System.out.println("\ncamp des monstres :\n" + campsMonstre); 
		}
}

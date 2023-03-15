package testsfonctionnels;

import protagoniste.*;

import java.util.Iterator;
import java.util.NavigableSet;
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
		
		System.out.println("\n**** TP3 ****");
		System.out.println("\nordre naturel :\n"
		 + aideEcrivain.ordreNaturelMonstre());

		System.out.println("\nordre par domaine :\n"
				 + aideEcrivain.ordreMonstreDomaine());
		
		System.out.println("\nordre par zone de combat :\n"
				 + aideEcrivain.ordreMonstreZone());
		
		System.out.println(aideEcrivain.firstMonstreDomaine(Domaine.TRANCHANT));
	
		aideEcrivain.initMonstresDeFeu();
		NavigableSet<Monstre<?>> monstres = aideEcrivain.getMonstresDeFeu();
		String affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu 1 :\n" + affichage);
		
		Monstre<Glace> soufflemort = new Monstre<>("soufflemort", 120,
			ZoneDeCombat.AERIEN, Domaine.GLACE, new Tornade(8));
		Monstre<Feu> cramombre = new Monstre<>("cramombre", 80,
			ZoneDeCombat.TERRESTRE, Domaine.FEU, new BouleDeFeu(2), new Lave(1), new Eclair(1));
		soufflemort.rejointBataille(bataille);
		cramombre.rejointBataille(bataille); 
		
		aideEcrivain.initMonstresDeFeu();
		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu 2 :\n" + affichage); 
		
		Monstre<Glace> givrogolem = new Monstre<>("givrogolem", 200,
			ZoneDeCombat.TERRESTRE, Domaine.GLACE, new PicsDeGlace(10), new Tornade(1));
		givrogolem.rejointBataille(bataille);
		aideEcrivain.initMonstresDeFeu();
		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu 3 :\n" + affichage); 
		
		Monstre<Feu> aqualave = new Monstre<>("aqualave", 30,
		 ZoneDeCombat.AQUATIQUE, Domaine.FEU, new Lave(5));
		Monstre<Tranchant> requispectre = new Monstre<>("requispectre", 200,
		 ZoneDeCombat.AQUATIQUE, Domaine.TRANCHANT, new Griffe());
		
		aqualave.rejointBataille(bataille);
		requispectre.rejointBataille(bataille);
		aideEcrivain.initMonstresDeFeu();
		aideEcrivain.initMonstresDeGlace();
		aideEcrivain.initMonstresTranchant();
		
		monstres = aideEcrivain.getMonstresDeFeu();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de feu :\n" + affichage);
		monstres = aideEcrivain.getMonstresDeGlace();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres de glace :\n" + affichage);
		monstres = aideEcrivain.getMonstresTranchants();
		affichage = affichageMonstres(monstres);
		System.out.println("\nmonstres tranchants :\n" + affichage);
		
		
		
		
		
		
	
	}
	
	public static String affichageMonstres(NavigableSet<Monstre<?>> monstres) {
		String texte = "";
//		TODO
//		for (Iterator iterator = monstres.iterator(); iterator.hasNext();) {
//			Monstre<?> monstre = (Monstre<?>) iterator.next();
//			
//		}
		for (Monstre<?> monstre : monstres) {
			texte += monstre.getNom() + " monstre de " + monstre.getDomaine() + ", ";
		}
		return texte;
	}
	
	
}

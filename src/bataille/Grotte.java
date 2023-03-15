package bataille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class Grotte {
	private Map<Salle, List<Salle>> planGrotte = new LinkedHashMap<>();
	private Map<Salle, Bataille> batailles = new HashMap<>();
	private Set<Salle> sallesExplorees = new HashSet<>();
	private int numeroSalleDecisive;
	
	
	public void ajouterSalle(ZoneDeCombat zoneDeCombat, Monstre<?>... monstre) {
		Salle newSalle = new Salle(planGrotte.size() + 1, zoneDeCombat);
		Bataille newBataille = new Bataille();
		for(Monstre<?> m : monstre) {
			newBataille.ajouter(m);
		}
		planGrotte.put(newSalle, new ArrayList<Salle>());
		batailles.put(newSalle, newBataille);
		
	}
	
	public String afficherPlanGrotte() {
		  StringBuilder affichage = new StringBuilder();
		  for (Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
			  Salle salle = entry.getKey();
			  List<Salle> acces = planGrotte.get(salle);
			  affichage.append("La " + salle + ".\nelle possede " + acces.size() + " acces : ");
			  for (Salle access : acces) {
				  affichage.append(" vers la salle " + access);
			  }
			  Bataille bataille = batailles.get(salle);
			  Camp<Monstre<?>> camp = bataille.getCampMonstres();
			  Monstre<?> monstre = camp.selectionner();
			  if (camp.nbCombattants() > 1) {
				  affichage.append("\n" + camp.nbCombattants() + " monstres de type ");
			  } else {
				  affichage.append("\nUn monstre de type ");
			  }
			  affichage.append(monstre.getNom() + " la protege.\n");
			  if (salle.getNumSalle() == numeroSalleDecisive) {
				  affichage.append("C'est dans cette salle que se trouve la pierre de sang.\n");
			  }
			  affichage.append("\n");
		  }
		  return affichage.toString();
	}
	
	private Salle trouverSalle(int numeroSalle) {
		Set<Salle> listeSalle = planGrotte.keySet();
		int indice = 0;
		
		for(Salle s : listeSalle) {
			indice++;
			if(indice == numeroSalle) {
				return s;
			}
		}
		return null;
	}
	
	public void configurerAcces(int numSalleOrgine, int... numSallesAcces) {
		Salle origine = trouverSalle(numSalleOrgine);
		List<Salle> sallesAccesibles = planGrotte.get(origine);
		for(int x : numSallesAcces) {
			sallesAccesibles.add(trouverSalle(x));
		}
	}

	public void setNumeroSalleDecisive(int numeroSalleDecisive) {
		this.numeroSalleDecisive = numeroSalleDecisive;
	}
	
	public boolean salleDecisive(Salle salle) {
		return salle.getNumSalle() == numeroSalleDecisive;
	}
	

	public Salle premiereSalle() {
		Salle salle = trouverSalle(1);
		sallesExplorees.add(salle);
		return salle;
	}
	
	
	public Salle salleSuivante(Salle salleAQuitter) {
		List<Salle> salleAccessible = new ArrayList<>(planGrotte.get(salleAQuitter));
		List<Salle> sallePossible = new ArrayList<>(salleAccessible);
		sallePossible.removeAll(sallesExplorees);
		if(sallePossible.isEmpty()) {
			sallePossible.addAll(salleAccessible);
		}
		Random rand = new Random();
		int val = rand.nextInt(sallePossible.size());
		Salle salleT = sallePossible.get(val);
		sallesExplorees.add(salleT);
		
		return salleT;
	}

}

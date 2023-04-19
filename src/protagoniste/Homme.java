package protagoniste;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import attaque.Arme;
import attaque.KeyArme;
import bataille.Bataille;

public class Homme extends EtreVivant{
	private Map<ZoneDeCombat, List<Arme>> armes = new EnumMap<>(ZoneDeCombat.class);
	private Arme armeChoisie;
	
	public Homme(String nom) {
		super(nom, 70);
	}
	
	@Override
	public void rejointBataille(Bataille bataille) {
		super.rejointBataille(bataille);
		this.bataille.ajouter(this);
	}
	
	@Override
	public void mourir() {
		this.bataille.eliminer(this);
	}

	@Override
	public String toString() {
		return "Homme [nom=" + nom + ", force de vie=" + forceDeVie + "]";
	}
	
	public void ajouterArmes(Arme... armes) {
		Set<ZoneDeCombat> zonesDeCombat;
		List<Arme> liste;
		
		for (Arme arme : armes) {
			zonesDeCombat = arme.getZonesDeCombat();
			for (ZoneDeCombat zoneDeCombat : zonesDeCombat) {
				liste = new ArrayList<>();
				if (this.armes.containsKey(zoneDeCombat)) {
					liste = this.armes.get(zoneDeCombat);
				} else {
					this.armes.put(zoneDeCombat, liste);
				}
				liste.add(arme);
			}
		}
	}
	
	public void supprimerArme(Arme arme) {
		Set<ZoneDeCombat> zonesDeCombat = arme.getZonesDeCombat();
		List<Arme> liste;
		
		for (ZoneDeCombat zoneDeCombat : zonesDeCombat) {
			liste = this.armes.get(zoneDeCombat);
			if (liste.contains(arme)) {
				liste.remove(arme);
			}
		}
	}

	public Arme getArmeChoisie() {
		return armeChoisie;
	}
	
	public Arme choisirArme(Monstre<?> monstre) {
		ZoneDeCombat zoneMonstre = monstre.getZoneDeCombat();
		if (armes.containsKey(zoneMonstre)) {
			List<Arme> armesAdequates = this.armes.get(zoneMonstre);
			NavigableSet<Arme> armesTriees = new TreeSet<>(armesAdequates);

			if (!armesTriees.isEmpty()) {

				int forceVie = monstre.getForceDeVie();
				NavigableSet<Arme> armesAdaptees = armesTriees.tailSet(new KeyArme(forceVie), true);

				if (!armesAdaptees.isEmpty()) {
					this.armeChoisie = armesAdaptees.pollFirst();
				} else {
					this.armeChoisie = armesTriees.pollLast();
				}
			}
		}
		return this.armeChoisie;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EtreVivant) {
			Homme homme = (Homme) obj;
			return nom.equals(homme.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31 * (this.forceDeVie + this.nom.hashCode() + this.bataille.hashCode()); 
	}
	
	@Override
	public int compareTo(EtreVivant hommeToCompare) {
		if (hommeToCompare instanceof Homme) {
			return this.getNom().compareTo(hommeToCompare.getNom());
		}
		return -99;
	}
	
	public int subitAttaque(int degat) {
		forceDeVie -= degat;
		if(forceDeVie < 1) {
			mourir();
		}
		return forceDeVie;
		
	}
	
	public boolean attaqueReussie() {
		Random rand = new Random();
		int randVal = rand.nextInt(21);
		return randVal < 10;
	}
	
	public boolean attaqueMonstre(Monstre<?> monstre) {
		boolean attaqueReussie = attaqueReussie();
		int pointDeDegat = armeChoisie.utiliser();
		
		if(attaqueReussie) {
			monstre.subitAttaque(pointDeDegat);
		}
		
		return attaqueReussie;
	}

	public boolean resteArmeUtile() {
		return armes.isEmpty();
	}
}

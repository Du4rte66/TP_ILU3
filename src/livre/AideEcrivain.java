package livre;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

import bataille.Bataille;
import bataille.Camp;
import protagoniste.Domaine;
import protagoniste.EtreVivant;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class AideEcrivain {
	Bataille bataille;
	NavigableSet<Monstre<?>> monstresDomaineSet = 
		new TreeSet<>(
			new Comparator<Monstre<?>>() {
				public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
					int comparaison = monstre1.getDomaine().compareTo(monstre2.getDomaine());
					if (comparaison == 0) {
						comparaison = monstre1.getNom().compareTo(monstre2.getNom());
					}
					return comparaison;
				}
			});
	
	private NavigableSet<Monstre<?>> monstresZoneSet = new TreeSet<>(
			new Comparator<Monstre<?>>() {
				public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
					int comparaison = monstre1.getZoneDeCombat().compareTo(monstre2.getZoneDeCombat());
					if(comparaison == 0) {
						Integer forceMonstre1 = monstre1.getForceDeVie();
						Integer forceMonstre2 = monstre2.getForceDeVie();
						comparaison = forceMonstre2.compareTo(forceMonstre1);
						if(comparaison == 0)
							comparaison = monstre1.getNom().compareTo(monstre2.getNom());
					}
					return comparaison;
				}
			}
		);
	
	public AideEcrivain(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public List<Homme> visualiserForcesHumaines() {
		Camp<Homme> campHumains = bataille.getCampHumains();
		List<Homme> listeTriee = new LinkedList<>();

		for(Homme homme : campHumains){
			if(listeTriee.isEmpty()) {
				listeTriee.add(homme);
			} else {
				extracted(listeTriee, homme);
			}
		}		
		return listeTriee;
	}

	private void extracted(List<Homme> listeTriee, Homme homme) {
		if(homme instanceof Heros) {
			boolean heroAjouter = false;
			for(ListIterator<Homme> listIterator = listeTriee.listIterator();
					!heroAjouter && listIterator.hasNext();) {
				EtreVivant hommeAComparer = listIterator.next();
				if(!(hommeAComparer instanceof Heros)) {
					listIterator.previous();
					listIterator.add(homme);
					heroAjouter = true;
				}
			}
		} else {
			listeTriee.add(homme);
		}
	}
	
	public NavigableSet<String> ordreNaturelMonstre() {
		Camp<Monstre<?>> campMonstres = bataille.getCampMonstres();
		NavigableSet<String> res = new TreeSet<>();
		
		for(Monstre<?> monstre : campMonstres) {
			res.add(monstre.getNom());
		}

		return res;
	} 
	
	public NavigableSet<Monstre<?>> updateMonstresDomaine() {
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresDomaineSet.add(monstre);
		}
		return monstresDomaineSet;
	}
	
	public String ordreMonstreDomaine() {
		String texte = "Ordre par domaine :";
		Domaine domaine = null;
		updateMonstresDomaine();
		
		if(monstresDomaineSet.isEmpty()) {
			domaine = monstresDomaineSet.pollFirst().getDomaine();
		}
		for(Monstre<?> monstre : monstresDomaineSet) {

			Domaine newDomaine = monstre.getDomaine();
			if(newDomaine != domaine) {
				domaine = newDomaine;
				texte += "\n" + domaine + " :\n";
			}
			if (monstresDomaineSet.pollLast().getNom().equals(monstre.getNom()) {
				texte += monstre.getNom() + ", ";
			}
		}
		return texte;
		
		public String ordreMonstreZone() {
			String texte = "Ordre par zone de combat :";
			ZoneDeCombat zoneDeCombat = null;
			updateMonstresDomaine();
			
			if(monstresDomaineSet.isEmpty()) {
				zoneDeCombat = monstresDomaineSet.pollFirst().getDomaine();
			}
			for(Monstre<?> monstre : monstresDomaineSet) {

				Domaine newDomaine = monstre.getDomaine();
				if(newDomaine != zoneDeCombat) {
					zoneDeCombat = newDomaine;
					texte += "\n" + zoneDeCombat + " :\n";
				}
				texte += monstre.getNom() + ", ";
			}
			return texte;
		}
	}
	

	

}
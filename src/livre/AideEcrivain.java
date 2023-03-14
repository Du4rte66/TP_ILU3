package livre;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.TreeSet;
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
	private NavigableSet<Monstre<?>> monstresDomaineSet = 
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
					if (comparaison == 0) {
						Integer forceMonstre1 = monstre1.getForceDeVie();
						Integer forceMonstre2 = monstre2.getForceDeVie();
						comparaison = forceMonstre2.compareTo(forceMonstre1);
						if (comparaison == 0)
							comparaison = monstre1.getNom().compareTo(monstre2.getNom());
					}
					return comparaison;
				}
			}
		);
	
	private NavigableSet<Monstre<?>> monstresDeFeu =
			new TreeSet<>(
				new Comparator<Monstre<?>>() {
					public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
						return monstre1.getNom().compareTo(monstre2.getNom());
					}
				});
	
	private NavigableSet<Monstre<?>> monstresDeGlace =
		new TreeSet<>(
			new Comparator<Monstre<?>>() {
				public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
					return monstre1.getNom().compareTo(monstre2.getNom());
				}
			});
	
	private NavigableSet<Monstre<?>> monstresTranchants =
		new TreeSet<>(
			new Comparator<Monstre<?>>() {
				public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
					return monstre1.getNom().compareTo(monstre2.getNom());
				}
			});
	
	public AideEcrivain(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public List<Homme> visualiserForcesHumaines() {
		Camp<Homme> campHumains = bataille.getCampHumains();
		List<Homme> listeTriee = new LinkedList<>();

		for (Homme homme : campHumains){
			if (listeTriee.isEmpty()) {
				listeTriee.add(homme);
			} else {
				extracted(listeTriee, homme);
			}
		}		
		return listeTriee;
	}

	private void extracted(List<Homme> listeTriee, Homme homme) {
		if (homme instanceof Heros) {
			boolean heroAjouter = false;
			for (ListIterator<Homme> listIterator = listeTriee.listIterator();
					!heroAjouter && listIterator.hasNext();) {
				EtreVivant hommeAComparer = listIterator.next();
				if (!(hommeAComparer instanceof Heros)) {
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
		
		for (Monstre<?> monstre : campMonstres) {
			res.add(monstre.getNom());
		}

		return res;
	} 
	
	public NavigableSet<Monstre<?>> updateMonstresDomaine() {
		for (Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresDomaineSet.add(monstre);
		}
		return monstresDomaineSet;
	}
	
	public NavigableSet<Monstre<?>> updateMonstresZone() {
		for (Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresZoneSet.add(monstre);
		}
		return monstresZoneSet;
	}
	
	public String ordreMonstreDomaine() {
		String texte = "";
		boolean tempMonstre;
		EnumSet<Domaine> domaines = EnumSet.allOf(Domaine.class);
		monstresDomaineSet = updateMonstresDomaine();
		
		for (Domaine domaine : domaines) {
			texte += domaine + " :\n";
			tempMonstre = false;
			for (Monstre<?> monstre : monstresDomaineSet) {
				if (monstre.getDomaine() == domaine) {
					if (tempMonstre == true) {
						texte +=  ", "; }
					texte += monstre.getNom();
					tempMonstre = true;
				}
			}
			texte += "\n";
		}
		return texte;
	}
	
	public String ordreMonstreZone() {
		String texte = "";
		boolean tempMonstre;
		EnumSet<ZoneDeCombat> zones = EnumSet.allOf(ZoneDeCombat.class);
		monstresZoneSet = updateMonstresZone();
		
		for (ZoneDeCombat zoneDeCombat : zones) {
			texte += zoneDeCombat + " :\n";
			tempMonstre = false;
			for (Monstre<?> monstre : monstresZoneSet) {
				if (monstre.getZoneDeCombat() == zoneDeCombat) {
					if (tempMonstre == true) {
						texte +=  ", "; } 
					texte += monstre.getNom() + " : " + monstre.getForceDeVie();
					tempMonstre = true;
				}
			}
			texte += "\n";
		}
		return texte;
	}
	
	public NavigableSet<Monstre<?>> getMonstresDeFeu() {
		return monstresDeFeu;
	}

	public NavigableSet<Monstre<?>> getMonstresDeGlace() {
		return monstresDeGlace;
	}

	public NavigableSet<Monstre<?>> getMonstresTranchants() {
		return monstresTranchants;
	}
	
	public Monstre<?> firstMonstreDomaine(Domaine domaine) {
		for (Monstre<?> monstre : monstresDomaineSet) {
			if (monstre.getDomaine() == domaine) {
				return monstre;
			}
		}
		return null;
	}
	
	public void initMonstresDeFeu() {
		monstresDomaineSet = updateMonstresDomaine();
		for (Monstre<?> monstre : monstresDomaineSet) {
			if (monstre.getDomaine() == Domaine.FEU) {
				monstresDeFeu.add(monstre);
			}
		}
	}
	
	public void initMonstresDeGlace() {
		monstresDomaineSet = updateMonstresDomaine();
		for (Monstre<?> monstre : monstresDomaineSet) {
			if (monstre.getDomaine() == Domaine.GLACE) {
				monstresDeGlace.add(monstre);
			}
		}
	}
	
	public void initMonstresTranchant() {
		monstresDomaineSet = updateMonstresDomaine();
		for (Monstre<?> monstre : monstresDomaineSet) {
			if (monstre.getDomaine() == Domaine.TRANCHANT) {
				monstresTranchants.add(monstre);
			}
		}
	}
	
//	public void initMonstresDeFeu() {
//		monstresDeFeu.add(firstMonstreDomaine(Domaine.FEU));
//	}
}
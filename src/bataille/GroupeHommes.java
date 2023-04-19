package bataille;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;
import attaque.Arme;
import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class GroupeHommes {
	private NavigableSet<Homme> groupe = new TreeSet<>();
	
//	private NavigableSet<Homme> groupe = new TreeSet<>(
//			(homme1, homme2) -> {
//			int comp = homme2.getForceDeVie() - homme1.getForceDeVie();
//            if (comp == 0) {
//                return homme1.getNom().compareTo(homme2.getNom());
//            }
//            return comp;});
	
	
	public void ajouterHommes(Homme... hommes) {
		Collections.addAll(groupe, hommes);
	}
	
	private static class ComparateurHommes implements Comparator<Homme>{
        public int compare(Homme homme1, Homme homme2) {
            int comp = homme2.getForceDeVie() - homme1.getForceDeVie();
            if (comp == 0) {
                return homme1.getNom().compareTo(homme2.getNom());
            }
            return comp;
        }
    }
	
	private static class ComparateurArmes implements Comparator<Arme>{
        Monstre<?> monstre;

        ComparateurArmes(Monstre<?> monstre){
            this.monstre = monstre;
        }

        public int compare(Arme arme1, Arme arme2) {
            if(arme1.getPointDeDegat() == arme2.getPointDeDegat()) {
                return arme1.getNom().compareTo(arme2.getNom());
            }
            else {
                TreeMap<Integer,Arme> classementForce = new TreeMap<>();
                classementForce.put(arme1.getPointDeDegat(), arme1);
                classementForce.put(arme2.getPointDeDegat(), arme2);
                Arme meilleurArme = classementForce.get(monstre.getForceDeVie());
                if (meilleurArme == null) {
                    meilleurArme = classementForce.get(monstre.getForceDeVie());
                }
                if (meilleurArme == arme1) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }
	
	public List<Homme> choixDuCamp(Bataille bataille) {
		Monstre<? extends Pouvoir> monstre = bataille.getCampMonstres().selectionner();
		NavigableMap<Arme, NavigableSet<Homme>> hommesArmes = new TreeMap<>(new ComparateurArmes(monstre));
		
		for (Homme homme : groupe) {
			Arme arme = homme.choisirArme(monstre);
			if (arme != null) {
				if (!hommesArmes.containsKey(arme)) {
					hommesArmes.put(arme, new TreeSet<Homme>(new ComparateurHommes()));
				}
				hommesArmes.get(arme).add(homme);
			}
		}
		return extracted(bataille, hommesArmes);
	}

	private List<Homme> extracted(Bataille bataille, NavigableMap<Arme, NavigableSet<Homme>> hommesArmes) {
		List<Homme> hommesChoisis = new ArrayList<>();
		
		while (hommesChoisis.size() < 3 && !hommesArmes.isEmpty()) {
			for (Homme homme : hommesArmes.get(hommesArmes.firstKey())) {
				if (hommesChoisis.size() < 3) {
					hommesChoisis.add(homme);
					homme.rejointBataille(bataille);
					bataille.ajouter(homme);
				}
			}
			hommesArmes.remove(hommesArmes.firstKey());
		}
		
		return hommesChoisis;
	}
	
	public void supprimerHomme(Homme homme) {
		groupe.remove(homme);
	}

	public boolean estVide() {
		return groupe.isEmpty();
	}

	public boolean resteCombattant() {
		return !groupe.isEmpty();
	}
}

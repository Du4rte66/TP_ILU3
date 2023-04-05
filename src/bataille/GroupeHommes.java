package bataille;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

import attaque.Arme;
import protagoniste.Homme;
import protagoniste.Monstre;

public class GroupeHommes {
	private NavigableSet<Homme> groupe = new TreeSet<>();
	
	public void ajouterHommes(Homme... hommes) {
		Collections.addAll(groupe, hommes);
	}
	
	private static class ComparateurHommes implements Comparator<Homme>{
        public ComparateurHommes() {
            super();
        }

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
		Monstre<?> monstre = bataille.getCampMonstres().selectionner();
	    List<Homme> liste = new ArrayList<>();
	    TreeMap <Arme, NavigableSet<Homme>> hommesArmes = new TreeMap<>(new ComparateurArmes(monstre));
	    
	    for(Homme homme : this.groupe) {
	    	homme.choisirArme(monstre);
	        if(homme.getArmeChoisie() != null && hommesArmes.containsKey(homme.getArmeChoisie())) {
	        	hommesArmes.get(homme.getArmeChoisie()).add(homme);
	        }
	        
	        else if(homme.getArmeChoisie() != null && !hommesArmes.containsKey(homme.getArmeChoisie())) {
	        	NavigableSet<Homme> ensemble = new TreeSet<>();
	            ensemble.add(homme);
	            hommesArmes.put(homme.getArmeChoisie(), ensemble);
	        }
	    }
	        
	    Iterator<Arme> iterateurArme = hommesArmes.keySet().iterator(); 
	    while(iterateurArme.hasNext() && liste.size() < 3) {
	    	Arme arme = iterateurArme.next();
	        Iterator<Homme> iterateurHomme = groupe.iterator();
	        while(iterateurHomme.hasNext() && liste.size()<3) {
	        	Homme homme = iterateurHomme.next();
	            if(homme.choisirArme(monstre).equals(arme)) {
	            	liste.add(homme);
	            }
	        }
	    }
	           
	    Collections.sort(liste, new ComparateurHommes());
	    return liste;
	}
	
}

package livre;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import bataille.Bataille;
import bataille.Camp;
import protagoniste.EtreVivant;
import protagoniste.Heros;
import protagoniste.Homme;

public class AideEcrivain {

	Bataille bataille;
	
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
}
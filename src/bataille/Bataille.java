package bataille;

import protagoniste.Monstre;

public class Bataille {
	private Camp campHumains;
	private Camp campMonstres;
	
	public void ajouter(Homme homme) {
		campHumains.ajouter(homme);
	}
	
	public void ajouter(Monstre<?> monstre) {
		campMonstres.ajouter(monstre);
	}
	
	public void eliminer(Homme homme) {
		campHumains.eliminer(homme);
	}
	
	public void eliminer(Monstre<?> monstre) {
		campMonstres.eliminer(monstre);
	}
}

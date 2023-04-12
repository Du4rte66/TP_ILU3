package protagoniste;

public class ZoneDeCombatNonCompatibleException extends Exception {
	private static final long serialVersionUID = 1L;

	public ZoneDeCombatNonCompatibleException() {
		super();
	}
	
	public ZoneDeCombatNonCompatibleException(String message) {
		super(message);
	}
	
	public ZoneDeCombatNonCompatibleException(ZoneDeCombat zone, ZoneDeCombat monstre) {
		super("La zone de combat de la salle est de type " + zone
						+ ", alors que celle du monstre est de type " + monstre);
	}
}

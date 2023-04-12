package protagoniste;

public class ZoneDeCombatNonCompatibleException extends Exception{
	
	public ZoneDeCombatNonCompatibleException(ZoneDeCombat monstre, ZoneDeCombat zone) {
	    super("La zone de combat de la salle est de type "+ zone +", alors que celle du monstre est " + monstre);
	 }
	
	
}
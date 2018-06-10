package exception;
/**
 * Classe décrivant l'exception lancée quand une photo n'a pas le format souhaité.
 */
public class UnhandledFormatException extends Exception{
	private String nom;
	
	/**
	* Mauvais format de la photo 
	* @param n	 	nom de l'exception.
	* @param message	affiche un message lors de l'ouverture de l'exception	 
	*/
	
	public UnhandledFormatException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	/**
	* Retourne l'exception sous la forme d'une chaîne de caractères.
	*/
	
	public String toString() {
		return super.toString()+" : " + this.nom;
	}
}

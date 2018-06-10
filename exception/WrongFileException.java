package exception;
/**
 * Classe décrivant l'exception lancée quand la photo ne se trouve pas dans le bon répertoire.
 */
public class WrongFileException extends Exception{
private String nom;
	
	/**
	* Mauvais fichier
	* @param n		nom de l'exception
	* @param message	affiche un message lors de l'ouverture de l'exception
	*/
	
	public WrongFileException(String n, String message) {
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


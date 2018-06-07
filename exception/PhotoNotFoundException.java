package exception;


public class PhotoNotFoundException extends Exception{
	private String nom;
	
	/**
	* Photo non trouvée
	* @param n		nom de l'exception
	* @param message	affiche un message lors de l'ouverture de l'exception
	*/
	
	public PhotoNotFoundException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	/**
	* Retourne l'exception sous la forme d'une chaîne de caractères.
	*/
	
	public String toString() {
		return super.toString()+" Photo non trouvée : " + this.nom;
	}
}

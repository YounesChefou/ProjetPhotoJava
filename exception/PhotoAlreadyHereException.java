package exception;

public class PhotoAlreadyHereException extends Exception {
	private String nom;
	
	/**
	* Indique une photo unique
	* @param n		nom de l'exception
	* @param message	
	*/
	
	public PhotoAlreadyHereException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	/**
	* Retourne l'exception sous la forme d'une chaîne de caractères.
	*/
	
	public String toString() {
		return super.toString()+" Photo déja contenue dans l'album : " + this.nom;
	}
}

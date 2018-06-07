package exception;


public class WrongFileException extends Exception{
private String nom;
	
	/**
	* 
	* @param n	
	* @param message	
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


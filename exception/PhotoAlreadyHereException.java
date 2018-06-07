package exception;

public class PhotoAlreadyHereException extends Exception {
	private String nom;
	
	public PhotoAlreadyHereException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	public String toString() {
		return super.toString()+" Photo déja contenue dans l'album : " + this.nom;
	}
}

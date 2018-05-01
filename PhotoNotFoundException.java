

public class PhotoNotFoundException extends Exception{
	private String nom;
	
	public PhotoNotFoundException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	public String toString() {
		return super.toString()+" Photo non trouv�e : " + this.nom;
	}
}

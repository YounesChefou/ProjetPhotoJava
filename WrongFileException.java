

public class WrongFileException extends Exception{
private String nom;
	
	public WrongFileException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	public String toString() {
		return super.toString()+" : " + this.nom;
	}
}


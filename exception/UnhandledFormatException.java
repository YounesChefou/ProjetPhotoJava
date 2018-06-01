package exception;


public class UnhandledFormatException extends Exception{
	private String nom;
	
	public UnhandledFormatException(String n, String message) {
		super(message);
		this.nom=n;
	}
	
	public String toString() {
		return super.toString()+" : " + this.nom;
	}
}

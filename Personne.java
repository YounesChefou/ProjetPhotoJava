

public class Personne {
	private String nom;
	private String mail;
	
	public Personne(String nom, String mail){
		this.nom = nom;
		this.mail = mail;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public String getMail(){
		return this.mail;
	}
	public String toString(){
		return new String("Nom : "+this.nom+"\nMail : "+this.mail);
	}
}

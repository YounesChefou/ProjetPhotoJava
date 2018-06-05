package modele;

/**
 * 
 * @author Cheffou Gang
 *
 */
public class Personne {
	private String nom;
	private String mail;
	
	/**
	 * Construit une instance de Personne
	 * @param nom 	Le nom de la personne
	 * @param mail 	Le mail de la personne
	 */
	public Personne(String nom, String mail){
		this.nom = nom;
		this.mail = mail;
	}
	
	/**
	 * Retourne le nom de la personne
	 * @return le nom dela personne
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Retourne le mail de la personne
	 * @return le mail de la personne
	 */
	public String getMail(){
		return this.mail;
	}
	public String toString(){
		return new String("Nom : "+this.nom+"\nMail : "+this.mail);
	}
}

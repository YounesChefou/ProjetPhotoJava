package modele;

/**
 * Classe de gestion de personnes
 * @author Younes Cheffou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class Personne {
	// Variables d'instance
	private String nom;		// Le nom de la personne
	private String mail;		// Le mail de la personne
	
	/**
	 * Construit une instance de Personne
	 * @param nom 	Le nom de la personne
	 * @param mail 	Le mail de la personne
	 */
	public Personne(String nom, String mail){
		this.nom = nom;		// Initialise la variable d'instance nom
		this.mail = mail;	// Initialise la variable d'instance mail
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
	
	/**
	 * Permet d'afficher la personne sous la forme d'une chaîne de caractères
	 * @return la chaîne de caractères
	*/
	public String toString(){
		return new String("Nom : "+this.nom+"\nMail : "+this.mail);
	}
}

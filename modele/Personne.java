package modele;


public class Personne {
	private String nom;
	private String mail;
	
	public Personne(String nom, String mail){
		this.nom = nom;
		this.mail = mail;
	}
	
	/**
	 * Permet de récupérer le nom de la personne.
	 * @return this.nom, le nom
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Permet de récupérer l'adresse mail de la personne
	 * @return this.mail, l'adresse mail.
	 */
	public String getMail(){
		return this.mail;
	}
	
	/**
	 * Verifie si deux instances de Personne ont le même nom et la même adresse mail.
	 * @return true si les deux personnes sont identiques, false sinon
	 */
	public boolean equals(Object o){
		Personne p = null;
		if(o instanceof Personne) p = (Personne)o;
		if(this.nom.equals(p.getNom())&&this.mail.equals(p.getMail())) return true;
		return false;
	}
	
	/**
	 * Renvoie une chaine de caractères décrivant la personne
	 */
	public String toString(){
		return new String(this.nom+"("+this.mail+")");
	}
}

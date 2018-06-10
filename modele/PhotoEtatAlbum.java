package modele;

/**
 * Classe 
 * @author Younes Cheffou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 * Classe dont l'instance permet de définir l'etat d'une photo dans un album(ex: si celle-ci vient d'être ajoutée/supprimée)
 */
public class PhotoEtatAlbum {
	// Variables d'instances
	private String monAlbum;			// Le nom de l'album
	private Photo maPhoto;				// La photo
	private String etat;				// L'état de la photo dans l'album
	
	/**
	 * Construit une instance de Photo
	 * @param al 	Le nom de l'album
	 * @param p 	La photo
	 * @param status 	L'état de la photo dans l'album
	 */
	public PhotoEtatAlbum(String al,Photo p,String status) {
		this.monAlbum = al;			// Initialise la variable d'instance monAlbum
		this.maPhoto=p;				// Initialise la variable d'instance maPhoto
		this.etat=status;			// Initialise la variable d'instance etat
	}
	
	/**
	 * Return le nom de la photo
	 * @return le nom de la photo
	 */
	public String getNomPhoto() {
		return this.maPhoto.getNom();
	}
	
	/**
	 * Return l'état de la photo dans l'album
	 * @return l'état de la photo dans l'album
	 */
	public String getEtat() {
		return this.etat;
	}
	
	/**
	 * Change l'état de la photo dans l'album
	 * @param nom 	Le nouvel état de la photo dans l'album
	 */
	public void setEtat(String newState) {
		this.etat=newState;
	}
	
	/**
	 * Permet d'afficher le nom, l'état, etl'album de la photo sous la forme d'une chaîne de caractères
	 * @return la chaîne de caractères
	*/
	public String toString() {
		String s = new String(this.maPhoto.getNom()+ " dans l'album "+this.monAlbum+" à pour état"+this.etat);
		return s;
	}
}

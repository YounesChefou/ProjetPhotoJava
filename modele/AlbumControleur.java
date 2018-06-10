package modele;
import java.io.File;
/**
* Classe décrivant le contrôleur entre un AlbumPhoto et ses vues
*/
public class AlbumControleur {
	private AlbumPhoto modele;
	/**
 	* @param al modèle controlé par le controleur
 	*/
	public AlbumControleur(AlbumPhoto modele){
		this.modele = modele;
	}
	/**
 	* Notifie au modèle qu'il à été modifier (ajout d'une photo) en appelant ça méthode ajouterPhotoFile
 	* @param files fichier 
 	* @throws PhotoAlreadyHereException exception lancée lorsque la photo est déja dans l'album
 	*/
	public void notificationAjoutModele(File[] files) throws PhotoAlreadyHereException{
		this.modele.ajouterPhotosFile(files);
	}
/**
 * 	Notifie au modèle qu'il à été modifier (suppression d'une photo) en appelant ça méthode supprimerPhoto
 * @param p Photo du modèle
 */
	public void notificationDelModele(Photo p) {
		this.modele.supprimerPhoto(p);
		}
	
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
}

package modele;
import java.io.File;
import exception.PhotoAlreadyHereException;
/**
* Classe décrivant le contrôleur entre un AlbumPhotoEvent et ses vues.
*/
public class AlbumControleurEvent {
private AlbumPhotoEvent modele;
	/**
 	* @param al modèle controlé par le controleur
 	*/
	public AlbumControleurEvent(AlbumPhotoEvent al) {
		this.modele=al;
	}
	/**
 	* Notifie au modèle qu'il à été modifié (ajout d'une photo) en appelant ça méthode ajouterPhotoFile
 	* @param files fichier 
 	* @throws PhotoAlreadyHereException exception lancée lorsque la photo est déja dans l'album
 	*/
	public void notificationAjoutModele(File[] files) throws PhotoAlreadyHereException {
		this.modele.ajouterPhotosFile(files);
	}
	/**
	*Notifie au modèle qu'il à été modifié (Ajout d'une personne) en appellant ça méthode ajouterPersonne
	*@param nom Nom de la personne ajoutée
	*@param mail Mail de la personne ajoutée
	*/
	public void notificationAjoutPersonne(String nom, String mail){
		this.modele.ajouterPersonne(nom,mail);
	}
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
	/**
	*Notifie au modèle qu'il à été modifié (Suppression d'une photo) en appellant ça méthode supprimerPhoto
	*@param photo Photo supprimée de l'album
	*/
	public void notificationDelModele(Photo photo) {
		this.modele.supprimerPhoto(photo);
	}
	/**
	*Notifie au modèle qu'il à été modifié (Suppression d'une personne) en appellant ça méthode supprimerPersonne
	*@param p Personne supprimée de l'album
	*/
	public void notificationDelPers(Personne p) {
		this.modele.supprimerPersonne(p);
	}
}

package modele;
import java.io.File;
/**
* Classe décrivant le contrôleur entre un AlbumPhoto et ses vues
*/
public class AlbumControleur {
	private AlbumPhoto modele;
	
	public AlbumControleur(AlbumPhoto modele){
		this.modele = modele;
	}
	
	public void notificationAjoutModele(File[] files) throws PhotoAlreadyHereException{
		this.modele.ajouterPhotosFile(files);
	}
	
	public void notificationDelModele(Photo p) {
		this.modele.supprimerPhoto(p);
		}
	
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
}

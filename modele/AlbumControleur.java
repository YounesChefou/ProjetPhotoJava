package modele;

import java.io.File;

public class AlbumControleur {
	private AlbumPhoto modele;
	
	public AlbumControleur(AlbumPhoto modele){
		this.modele = modele;
	}
	
	public void notificationAjoutModele(File[] files) throws PhotoAlreadyHereException{
		this.modele.ajouterPhotosFile(files);
	}
	
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
}

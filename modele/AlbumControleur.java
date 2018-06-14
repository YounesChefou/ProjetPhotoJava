package modele;

import java.io.File;

import exception.PhotoAlreadyHereException;

public class AlbumControleur {
	private AlbumPhoto modele;
	
	public AlbumControleur(AlbumPhoto modele){
		this.modele = modele;
	}
	
	public void notificationAjoutModele(File[] files)throws PhotoAlreadyHereException{
		this.modele.ajouterPhotosFile(files);
	}
	
	public void notificationDelModele(Photo p) {
		this.modele.supprimerPhoto(p);
	}
	
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
	
	public void notificationTriModele(){
		this.modele.trierParDate();
	}
	
}
package modele;

import java.io.File;

import exception.PhotoAlreadyHereException;

public class AlbumControleurEvent {
private AlbumPhotoEvent modele;
	
	public AlbumControleurEvent(AlbumPhotoEvent al) {
		this.modele=al;
	}

	public void notificationAjoutModele(File[] files) throws PhotoAlreadyHereException {
		this.modele.ajouterPhotosFile(files);
	}
	
	public void notificationAjoutPersonne(String nom, String mail){
		this.modele.ajouterPersonne(nom,mail);
	}
	public void notificationSauvModele(String nomFichier){
		this.modele.sauv(nomFichier);
	}
	
	public void notificationDelModele(Photo photo) {
		this.modele.supprimerPhoto(photo);
	}
	
	public void notificationDelPers(Personne p) {
		this.modele.supprimerPersonne(p);
	}
}

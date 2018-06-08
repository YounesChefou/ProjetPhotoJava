package modele;

import java.io.File;

import exception.PhotoAlreadyHereException;

public class AlbumControleurEvent {
private AlbumPhotoEvent modele;
	
	public AlbumControleurEvent(AlbumPhotoEvent al) {
		this.modele=al;
	}

	public void modifieAlbumAjoute(File[] files) throws PhotoAlreadyHereException {
		this.modele.ajouterPhotosFile(files);
	}
	
	
	
	public void modifieAlbumDel(Photo photo) {
		this.modele.supprimerPhoto(photo);
		}

}

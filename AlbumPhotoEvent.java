package photos;

import java.util.ArrayList;

public class AlbumPhotoEvent {
		private String nom;
		private Event evenement;
		private ArrayList<Photo> album;
		
		public AlbumPhotoEvent(Event E){
			this.nom = E.getNomEvent();
			this.evenement = E;
			this.album = new ArrayList<Photo>();
		}
}

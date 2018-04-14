import java.util.ArrayList;

public class AlbumPhotoEvent {
		private String nom;
		private Event evenement;
		private ArrayList<PhotoEvent> album;
		
		public AlbumPhotoEvent(Event E){
			this.nom = E.getNomEvent();
			this.evenement = E;
			this.album = new ArrayList<PhotoEvent>();
		}
		

		public String getNom(){
			return this.nom;
		}
		
		public ArrayList<PhotoEvent> getAlbum(){
			return this.album;
		}
		
		public Event getEvent(){
			return this.evenement;
		}
		public void setNom(String nom){
			this.nom = nom;
		}
		
		public void ajouterPhoto(PhotoEvent p){ //à compléter ou pas
			this.getAlbum().add(p);
		}
		
		public String toString(){
			return new String("Album "+this.getNom()+" de l'evenement "+this.getEvent().getNomEvent()+this.getAlbum());
		}
}

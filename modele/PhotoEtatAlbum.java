package modele;

public class PhotoEtatAlbum {
	private String monAlbum;
	private Photo maPhoto;
	private String etat;
	
	public PhotoEtatAlbum(String al,Photo p,String status) {
		this.monAlbum = al;
		this.maPhoto=p;
		this.etat=status;
	}
	
	public String getNomPhoto() {
		return this.maPhoto.getNom();
	}
	
	public String getEtat() {
		return this.etat;
	}
	
	public void setEtat(String newState) {
		this.etat=newState;
	}
	
	
	public String toString() {
		String s = new String(this.maPhoto.getNom()+ " dans l'album "+this.monAlbum+" à pour état"+this.etat);
		return s;
	}
}

import java.util.*;

public class AlbumPhoto {
	private String nom;
	private ArrayList<Photo> album;
	
	public AlbumPhoto(String nom){
		this.nom = nom;
		this.album = new ArrayList<Photo>();
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public ArrayList<Photo> getAlbum(){
		return this.album;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void ajouterPhoto(Photo p){
		this.getAlbum().add(p);
	}
	
	public String toString(){
		return new String("Album "+this.getNom()+" : "+this.getAlbum());
	}
	
}

package modele;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import exception.*;

public class AlbumPhoto extends Observable{
	private String nom;
	private ArrayList<Photo> album;
	
	public AlbumPhoto(String nom){
		this.nom = nom;
		this.album = new ArrayList<Photo>();
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public Photo getPhotoAt(int i){
		return this.album.get(i);
	}
	
	public Photo getPhotoByName(String nom) throws PhotoNotFoundException{
		for(Photo p : this.album){
			if(p.getNom().equals(nom))
				return p;
		}
		throw new PhotoNotFoundException(nom, "Photo non présente dans l'album.");
	}
	
	public int getPhotoIndex(Photo p){
		return this.album.indexOf(p);
	}
	
	public boolean photoExist(Photo p){
		return this.album.contains(p);
	}
	
	public Photo[] toArray(){
		return (Photo[])this.album.toArray();
	}
	public ArrayList<Photo> getAlbum(){
		return this.album;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void setAlbum(ArrayList<Photo> album){
		this.album = album;
	}
	public void ajouterPhoto(Photo p) throws PhotoAlreadyHereException{
		 if(this.album.contains(p)){
			 PhotoAlreadyHereException r = new PhotoAlreadyHereException(p.getNom(),"Photo Déjà existante"); 
			 throw r;
		 }
		this.getAlbum().add(p);
		PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo ajoutée");
		this.setChanged();
		this.notifyObservers(photoEtat);
	}
	
	public void ajouterPhotosListe(ArrayList<Photo> listePhotos){
		this.album.addAll(listePhotos);
//		this.setChanged();
//		this.notifyObservers();
	}
	
	public void ajouterPhotosAlbum(AlbumPhoto a){
		this.album.addAll(a.getAlbum());
//		this.setChanged();
//		this.notifyObservers();
	}
	
	public void ajouterPhotosFile(File[] files) throws PhotoAlreadyHereException{
		Photo p;
		ArrayList<Photo> liste = new ArrayList<Photo>();
		for (File f : files){
				try{
					p = new Photo("images/"+f.getName());
					if(this.album.contains(p)) {
						PhotoAlreadyHereException r = new PhotoAlreadyHereException(p.getNom(),"Photo déjà existante");
						throw r;
						}
					liste.add(p);
					PhotoEtatAlbum etatPhoto = new PhotoEtatAlbum(this.nom,p,"photo ajoutée");
					this.setChanged();
					this.notifyObservers(etatPhoto);
				}
				catch(UnhandledFormatException e){
					System.out.println(e);
				}
				
				catch(PhotoNotFoundException ex){
					System.out.println(ex);
				}
				catch(PhotoAlreadyHereException ete){
					System.out.println(ete);
				}
				catch(WrongFileException exc){
					System.out.println(exc);
				}
		}
		this.ajouterPhotosListe(liste);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void supprimerPhoto(Photo p) {
  		this.album.remove(p);
 		PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo supprimée");
 		this.setChanged();
 		this.notifyObservers(photoEtat);
 	}
 	
 	public void supprimerPhotoIndex(int i) {
 		Photo p = this.album.get(i);
 		this.album.remove(i);
 		PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo supprimée");
 		this.setChanged();
 		this.notifyObservers(photoEtat);
 	}
	public int getTaille(){
		return this.album.size();
	}
	public void charge(String fichier){
		
		BufferedReader bIn = null;
		String ligne = null;
		String nom = null;
		Photo p = null;
		ArrayList<Photo> album = new ArrayList<Photo>();
		try{
			File inputFile = new File(fichier);
			bIn = new BufferedReader(new FileReader(inputFile));
			ligne = bIn.readLine();
			StringTokenizer st = new StringTokenizer(ligne, ": ");
			while(st.hasMoreTokens()){
				nom = st.nextToken();
			}
			this.setNom(nom);
			ligne = bIn.readLine();
			while (ligne != null){
				try{
					StringTokenizer str = new StringTokenizer(ligne, " ");
					nom = str.nextToken();
					p = new Photo(nom);
					album.add(p);
				}
				catch(UnhandledFormatException e){
					System.out.println(e);
				}
				
				catch(PhotoNotFoundException ex){
					System.out.println(ex);
				}
				
				catch(WrongFileException exc){
					System.out.println(exc);
				}
				
//				catch(Exception exce){
//					System.out.println(exce);
//				}
				ligne = bIn.readLine();
			}
		}
		catch(FileNotFoundException e) { 
			System.out.println(e) ;
      }    
		catch(IOException e) { 
			System.out.println(e) ;
	                }
		finally	{
			this.setAlbum(album);
			if (bIn != null) {
					try {
						bIn.close();
							}
					catch(IOException ec) { 
						System.out.println(ec) ;
	                	}
					}
			}
		
		
	}
	
	/**
	 * Permet de sauvegarder dans un fichier, le nom de l'album et le chemin des photos qui le composent.
	 * @param fichier, nom du fichier de sauvegarde.
	 */
	
	public void sauv(String fichier){
		BufferedWriter bOut = null;
		FileWriter fOut = null;
		try{
			File outputFile = new File(fichier);
			bOut = new BufferedWriter(new FileWriter(outputFile));
			bOut.write("Album: " + this.getNom());		
			bOut.newLine();
			
			for(Photo ab : album){
					bOut.write("images/" + ab.getNom());
					bOut.newLine();
				}
			}
				 
		catch(IOException e) { 
			System.out.println(e) ;
	                }
		finally	{
			if (bOut != null) {
					try {
						bOut.close();
							}
					catch(IOException ec) { 
						System.out.println(ec) ;
	                	}
					}
			}
	}
	
	public String toString(){
		String s = new String("Album : "+this.getNom()+"\n\n");
		for(Photo p : this.getAlbum()){
			s += p+"\n";
		}
		return s;
	}
}

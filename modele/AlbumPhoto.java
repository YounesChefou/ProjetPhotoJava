package modele;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import exception.*;

/**
 * Classe de gestion d'un album photo
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class AlbumPhoto extends Observable{
	private String nom;
	private ArrayList<Photo> album;
	
	/**
	  * Construit une instance de AlbumPhoto
	  * @param nom	Le nom de l'album photo
	  */
	
	public AlbumPhoto(String nom){
		this.nom = nom; // Initialise la variable d'instance nom
		this.album = new ArrayList<Photo>(); // Initialise la varible d'instance album
	}
	
	/**
	  * Retourne le nom de l'album photo
	  * @return le nom de l'album photo
	  */
	
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Trie les les photos de l'album par date
	 */
	public void trierParDate() {
		Collections.sort(this.album);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	  * Retourne une photo de l'album photo par son indice dans ce dernier
	  * @param i  l'indice de la photo
	  * @return La photo d'indice i
	  */
	
	public Photo getPhotoAt(int i){
		return this.album.get(i);
	}
	
	/**
	  * Retourne une photo de l'album photo par son nom
	  * @param nom 	Le nom de la photo à rechercher
	  * @return la photo p du nom recherché
	  * @throws PhotoNotFoundException  si la photo n'a pas été trouvée
	  */
	
	public Photo getPhotoByName(String nom) throws PhotoNotFoundException{
		for(Photo p : this.album){ // Boucle qui parcours l'ensemble des photos d'album
			if(p.getNom().equals(nom)) // Test si nom correspond au nom d'une des photos de album
				return p; // Retourne la photo si la condition du if est vérifiée
		}
		throw new PhotoNotFoundException(nom, "Photo non présente dans l'album."); // lance l'exception PhotoNotFoundException si aucune des photos d'album ne correspond à nom
	}
	
	/**
	  * Retourne l'index d'une photo dans l'album photo
	  * @param p 	La photo p en question
	  * @return l'index de la photo p dans l'album
	  */
	
	public int getPhotoIndex(Photo p){
		return this.album.indexOf(p);
	}
	
	/**
	  * Cherche si une photo existe dans l'album photo
	  * @param p 	La photo à chercher
	  * @return true si la photo p est dans l'album photo ou false si la photo p n'est pas dans l'album photo
	  */
	
	public boolean photoExist(Photo p){
		return this.album.contains(p);
	}
	
	/**
	  * Retourne un tableau contenant toutes les photos de l'album photo
	  * @return le tableau contenant toutes les photos de l'album photo
	  */
	
	public Photo[] toArray(){
		return (Photo[])this.album.toArray();
	}
	
	/**
	  * Retourne l'album photo
	  * @return l'album photo
	  */
	
	public ArrayList<Photo> getAlbum(){
		return this.album;
	}
	
	/**
	  * Change le nom de l'album photo
	  * @param nom	 Le nouveau nom de l'album photo
	  */
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	/**
	  * Change les photos de l'album photo
	  * @param album Les nouvelles photos de l'album photo
	  */
	
	public void setAlbum(ArrayList<Photo> album){
		this.album = album;
	}
	
	/**
	  * Ajout d'une photo dans l'album photo
	  * @param p 	La photo à ajouter dans l'album photo
	  */
	
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
	
	/**
	  * Ajout de photos à partird'une liste de photos
	  * @param listePhotos 	La liste de photos à ajouter
	  */
	
	public void ajouterPhotosListe(ArrayList<Photo> listePhotos){
		this.album.addAll(listePhotos);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	  * Ajout de photos à partir d'un album photo
	  * @param a 	L'album photo contenant les photos à ajouter
	  */
	
	public void ajouterPhotosAlbum(AlbumPhoto a){
		this.album.addAll(a.getAlbum());
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	  * Ajout de photos à partir d'un tableau de fichiers
	  * @param files le tableau de fichiers
	  */
	
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
	
	/**
	 * Retire de l'album la photo donnée en instance.
	 * @param p la photo à enlever
	 */
	public void supprimerPhoto(Photo p) {
  		this.album.remove(p);
 		PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo supprimée");
 		this.setChanged();
 		this.notifyObservers(photoEtat);
 	}
 	
	/**
	 * Retire la photo à l'indice i de l'album
	 * @param i l'indice de la photo
	 */
 	public void supprimerPhotoIndex(int i) {
 		Photo p = this.album.get(i);
 		this.album.remove(i);
 		PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo supprimée");
 		this.setChanged();
 		this.notifyObservers(photoEtat);
 	}
 	
 	/**
 	  * Retourne la taille de l'album photo (le nombre de photo)
 	  * @return la taille de l'album photo
 	  */
 	
	public int getTaille(){
		return this.album.size();
	}
	
	/**
	  * Permet de remplir un album photo à partir d'un fichier texte
	  * @param fichier 	Le nom du fichier pour remplir l'album photo
	  */
	
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
	
	/**
	  * Permet d'afficher l'album photo sous la forme d'une chaîne de caractères
	  */
	public String toString(){
		String s = new String("Album : "+this.getNom()+"\n\n");
		for(Photo p : this.getAlbum()){
			s += p+"\n";
		}
		return s;
	}
}

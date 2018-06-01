package modele;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import exception.*;

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
	public void ajouterPhoto(Photo p){
		this.getAlbum().add(p);
	}
	
	public void ajouterPhotosListe(ArrayList<Photo> listePhotos){
		this.album.addAll(listePhotos);
	}
	
	public void ajouterPhotosAlbum(AlbumPhoto a){
		this.album.addAll(a.getAlbum());
	}
	
	public void ajouterPhotosFileChooser() throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
		Photo p;
		ArrayList<Photo> liste = new ArrayList<Photo>();
		JFileChooser fc = new JFileChooser("./images");
		// Pour ne choisir que les fichiers qui nous intéressent graĉe à leurs suffixes
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","jpeg");
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(true) ;			
		int returnVal = fc.showOpenDialog(null);
		/* retourne un des 3 mnémoniques : JFileChooser.CANCEL_OPTION,
		 JFileChooser.APPROVE_OPTION ou JFileCHooser.ERROR_OPTION suivant la manière dont 
		l'utilisateur est sorti de la boite de dialogue
		*/	 

	    if (returnVal == JFileChooser.APPROVE_OPTION)	{ 
	   			//on récupère alors les fichiers sélectionnés
				File[] files = fc.getSelectedFiles();
				for (File f : files)	{
				p = new Photo("images/"+f.getName());
				liste.add(p);
				}
		this.ajouterPhotosListe(liste);		
		}
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
				
				catch(Exception exce){
					System.out.println(exce);
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
	
	public void sauv(String fichier){
		BufferedWriter bOut = null;
		FileWriter fOut = null;
		try{
			bOut = new BufferedWriter(new FileWriter(fichier));
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

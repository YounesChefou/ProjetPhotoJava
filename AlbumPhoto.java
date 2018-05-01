import java.util.*;
import java.io.*;

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
	
	public void setAlbum(ArrayList<Photo> album){
		this.album = album;
	}
	public void ajouterPhoto(Photo p){
		this.getAlbum().add(p);
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
		return new String("Album "+this.getNom()+" : "+this.getAlbum());
	}
	
}

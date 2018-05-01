import java.util.*;
import java.io.*;

public class AlbumPhoto {
	private String nom;
	private ArrayList<Photo> album;
	
	public AlbumPhoto(String nom){
		this.nom = nom;
		this.album = new ArrayList<Photo>();
	}
	
	/*
	public AlbumPhoto(String ligne){
		StringTokenizer st = new StringTokenizer(ligne, ": ");
		if(st.nextToken().equals("Album")){
			this.nom = st.nextToken();
			this.album = new ArrayList<Photo>();
		}
		
	}
	*/
	
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
	
	public void charge(String fichier){
		
		BufferedReader bIn = null;
		String ligne = null;
		String nom = null;
		Photo p = null;
		AlbumPhoto album = null;
		try{
			File inputFile = new File(fichier);
			bIn = new BufferedReader(new FileReader(inputFile));
			ligne = bIn.readLine();
			StringTokenizer st = new StringTokenizer(ligne, ": ");
			while(st.hasMoreTokens()){
				nom = st.nextToken();
			}
			album = new AlbumPhoto(nom);
			ligne = bIn.readLine();
			while (ligne != null){
				try{
					StringTokenizer str = new StringTokenizer(ligne, " ");
					nom = str.nextToken();
					p = new Photo(nom);
					album.ajouterPhoto(p);
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
				finally{
					System.out.println(nom);
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
			System.out.println(album);
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
			bOut.write("Album: " + getNom());		
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

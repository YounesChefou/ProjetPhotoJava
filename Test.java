
import java.util.*;
import java.io.*;
import java.text.*;

public class Test{
	public static void main(String[] args){
		Event e = new Event("mariageToto");
		System.out.println(e.getNomEvent());
		File f = new File("mariageToto/bouquet.jpeg");
		String prt = f.getParent();
		if(prt.equals(e.getNomEvent()) == false) {
			System.out.println("H");
		}
		else{
			System.out.println("Y");
		}
		
		/*
		Photo p = new Photo("images/Saturne.gif");
		System.out.println(p);
		AlbumPhoto album = new AlbumPhoto("Nation");
		album.ajouterPhoto(p);
		System.out.println(album);
		
		Personne per = new Personne("dubacq","dubacq@orange.fr");
		Event e = new Event("Mariage");
		e.ajouterPersonne(per);
		AlbumPhotoEvent albumE = new AlbumPhotoEvent(e);
		PhotoEvent pE = new PhotoEvent("mariageToto/bouquet.jpeg",e);
		albumE.ajouterPhoto(pE);
		System.out.println(albumE);
		System.out.println(per);
		

		
		BufferedReader bIn = null;
		String ligne = null;
		String nom = null;
		Photo p = null;
		AlbumPhoto album = null;
		try{
			File inputFile = new File("FichierTest.txt");
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
				
		Event e = null;
		try{
			e = new Event("mariageToto");
			PhotoEvent p = new PhotoEvent("mariageToto/bouquet.jpeg", e);
		}
		catch(UnhandledFormatException et){
			System.out.println(et);
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
		*/
		}
}
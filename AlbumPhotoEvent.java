import java.util.*;
import java.io.*;

public class AlbumPhotoEvent {
		private String nom;
		private Event evenement;
		private ArrayList<PhotoEvent> album;
		
		public AlbumPhotoEvent(Event E){
			this.nom = E.getNomEvent();
			this.evenement = E;
			this.album = new ArrayList<PhotoEvent>();
		}
		
		public AlbumPhotoEvent(String nom){
			Event E = new Event(nom);
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
		
		public void charge(String fichier){
			
			BufferedReader bIn = null;
			String ligne = null;
			String nom = null;
			PhotoEvent p = null;
			AlbumPhotoEvent album = null;
			try{
				File inputFile = new File(fichier);
				bIn = new BufferedReader(new FileReader(inputFile));
				ligne = bIn.readLine();
				StringTokenizer st = new StringTokenizer(ligne, ": ");
				while(st.hasMoreTokens()){
					nom = st.nextToken();
				}
				album = new AlbumPhotoEvent(nom);
				ligne = bIn.readLine();
				while (ligne != null){
					try{
						StringTokenizer str = new StringTokenizer(ligne, " ");
						nom = str.nextToken();
						p = new PhotoEvent(nom);
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
				bOut.write("AlbumEvent: " + getEvent());		
				bOut.newLine();
				
				for(PhotoEvent ab : album){
						bOut.write(ab.getEvent().getNomEvent() + "/" + ab.getNom());
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
			return new String("Album de l'evenement "+this.getEvent().getNomEvent()+this.getAlbum());
		}
}

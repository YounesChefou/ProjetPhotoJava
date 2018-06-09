package modele;
import java.util.*;
import java.io.*;
import exception.*;

public class AlbumPhotoEvent extends Observable{
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
		
		public int getTaille() {
			return this.album.size();
		}
			
		public Photo getPhotoAt(int i) {
			return this.album.get(i);
		}
		
		public void setEvent(Event ev){
			    this.evenement=ev;
				this.setChanged();
				this.notifyObservers(ev);
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
		
		public void setAlbum(ArrayList<PhotoEvent> album){
			this.album = album;
		}
		
		public void ajouterPhoto(PhotoEvent p) throws PhotoAlreadyHereException,WrongEventException{
 			String nomAlbum = this.evenement.getNomEvent();		  
 			String evenementPhoto = p.getEvent().getNomEvent();
			if(this.album.contains(p)) {
				PhotoAlreadyHereException r = new PhotoAlreadyHereException(p.getNom(),"Photo Déjà existante"); 
				throw r;
			}
			if(!nomAlbum.equals(evenementPhoto)) {
 				WrongEventException we = new WrongEventException(p.getEvent(), "Evenement non correspondant à l'album");
 				throw we;					// Lance l'exception WrongEventException si la condition du if n'est pas vérifiée
 			}
			PhotoEvent g = p;
			this.getAlbum().add(p);
			PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo ajoutée");
			this.setChanged();
			this.notifyObservers(photoEtat);
		}
	
		public void ajouterPhotosListe(ArrayList<PhotoEvent> listePhotos){
			this.album.addAll(listePhotos);			
		//this.setChanged();
		//this.notifyObservers();
 		}
		
		public void ajouterPhotosFile(File[] files) throws PhotoAlreadyHereException{
			PhotoEvent p;
			String nomDossier = this.evenement.getNomEvent();
			ArrayList<PhotoEvent> liste = new ArrayList<PhotoEvent>();
			for (File f : files){
				try{
					p = new PhotoEvent("images/"+nomDossier+"/"+f.getName(),this.evenement);
					if(this.album.contains(p)) {
						PhotoAlreadyHereException r = new PhotoAlreadyHereException(p.getNom(),"Photo Déjà existante"); 
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
				catch(WrongFileException exc){
					System.out.println(exc);
				}
				catch(PhotoAlreadyHereException et){
					System.out.println(et);
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
			PhotoEvent p = this.album.get(i);
			this.album.remove(i);
			PhotoEtatAlbum photoEtat = new PhotoEtatAlbum(this.nom,p,"photo supprimée");
			this.setChanged();
			this.notifyObservers(photoEtat);
		}
		
		public void charge(String fichier){
			
			BufferedReader bIn = null;
			String ligne = null;
			String nom = null;
			String fichierEvent = null;
			PhotoEvent p = null;
			ArrayList<PhotoEvent> liste = new ArrayList<PhotoEvent>();
			try{
				File inputFile = new File(fichier);
				bIn = new BufferedReader(new FileReader(inputFile));
				ligne = bIn.readLine();
				StringTokenizer st = new StringTokenizer(ligne, ": ");
				while(st.hasMoreTokens()){
					fichierEvent = st.nextToken();
				}
				this.evenement.charge(fichierEvent);
				ligne = bIn.readLine();
				StringTokenizer st1 = new StringTokenizer(ligne, ": ");
				while(st1.hasMoreTokens()){
					nom = st1.nextToken();
				}
				this.setNom(nom);
				ligne = bIn.readLine();
				while (ligne != null){
					try{
						StringTokenizer str2 = new StringTokenizer(ligne, " ");
						nom = str2.nextToken();
						p = new PhotoEvent(nom, this.evenement);
						liste.add(p);
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
				this.setAlbum(liste);
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
			String nomEvent = this.getEvent().getNomEvent();
			String fichierEvent = nomEvent+"Event.txt";
			BufferedWriter bOut = null;
			FileWriter fOut = null;
			this.getEvent().sauv(fichierEvent);
			try{
				bOut = new BufferedWriter(new FileWriter(fichier));
				bOut.write("Event: " + fichierEvent);		
				bOut.newLine();
				bOut.write("AlbumEvent: " + this.getNom());		
				bOut.newLine();
				
				for(PhotoEvent ab : album){
						bOut.write("images/"+ab.getEvent().getNomEvent() + "/" + ab.getNom());
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
			String s = new String("Album de l'evenement : "+this.getEvent().getNomEvent()+"\n\n");
			for(PhotoEvent p : this.getAlbum()){
				s += p+"\n\n";
			}
			return s;
		}
}

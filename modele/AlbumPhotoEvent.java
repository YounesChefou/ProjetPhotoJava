package modele;
import java.util.*;
import java.io.*;
import exception.*;

/**
 * Classe de gestion d'un album photo évènement
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class AlbumPhotoEvent {
		//Variables d'instance
		private String nom;			// Le nom de l'album photo évènement
		private Event evenement;		// L'évènement de l'album photo évènement
		private ArrayList<PhotoEvent> album;
		
		/**
		 * Construit une instance d'AlbumPhotoEvent avec un évènement
		 * @param E 	L'évènement de l'album
		 */
		public AlbumPhotoEvent(Event E){
			this.nom = E.getNomEvent();			// Initialise la variable d'instance nom
			this.evenement = E;				// Initialise la variable d'instance evenement
			this.album = new ArrayList<PhotoEvent>();	// Initialise la variable d'instance album
		}
		
		/**
		 * Construit une instance d'AlbumPhotoEvent avec un nom
		 * @param nom 	Le nom de l'évènement de l'album
		 */
		public AlbumPhotoEvent(String nom){
			Event E = new Event(nom);			// Créer un évènement du nom passé en paramètre
			this.nom = E.getNomEvent();			// Initialise la variable d'instance nom
			this.evenement = E;				// Initialise la variable d'instance evenement
			this.album = new ArrayList<PhotoEvent>();	// Initialise la variable d'instance album
		}
		
		/**
		 * Retourne le nom de l'album photo évènement
		 * @return Le nom de l'album photo évènement
		 */
		public String getNom(){
			return this.nom;
		}
		
		/**
		 * Retourne l'album photo évènement
		 * @return l'album photo évènement
		 */
		public ArrayList<PhotoEvent> getAlbum(){
			return this.album;
		}
		
		/**
		 * Retourne l'évènement de l'album photo évènement
		 * @return Le l'évènement de l'album photo évènement
		 */
		public Event getEvent(){
			return this.evenement;
		}
		
		/**
		 * Change le nom de l'album photo évènement
		 * @param nom 	Le nouveau nom de l'album photo évènement
		 */
		public void setNom(String nom){
			this.nom = nom;
		}
		
		/**
		 * Change les photos évènement de l'album photo évènement 
		 * @param album 	Les nouvelles photos évènement
		 */
		public void setAlbum(ArrayList<PhotoEvent> album){
			this.album = album;
		}
		
		/**
		 * Ajout d'une photo évènement dans l'album photo évènement
		 * @param p 	La photo évènement à ajouter
		 * @throws WrongEventException Si l'évènement ne correspond pas à l'album évènement
		 */
		public void ajouterPhoto(PhotoEvent p) throws WrongEventException{
			String nomAlbum = this.evenement.getNomEvent();		  
			String evenementPhoto = p.getEvent().getNomEvent();
			if(nomAlbum.equals(evenementPhoto))			// Test si le nom de l'album correspond au nom de l'évènement de la photo
				this.getAlbum().add(p);				// Si la condition du if est vérifiée, on ajoute l'évènement à l'album
			else{
				WrongEventException we = new WrongEventException(p.getEvent(), "Evenement non correspondant à l'album");
				throw we;					// Lance l'exception WrongEventException si la condition du if n'est pas vérifiée
			}
		}
		
		/**
		 * Permet de remplir un album photo évènement à partir d'un fichier texte
		 * @param fichier	Le nom du fichier pour remplir l'album photo évènement
		*/
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
				while(st.hasMoreTokens()){
					nom = st.nextToken();
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
		
		/**
		 * Permet de sauvegarder dans un fichier, le nom de l'album photo évènement et le chemin des photos qui le composent.
		 * @param fichier 	nom du fichier de sauvegarde.
		 */
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
	
		/**
		 * Permet d'afficher l'album photo évènement sous la forme d'une chaîne de caractères
		 * @return la chaîne de caractère
		*/
		public String toString(){
			String s = new String("Album de l'evenement : "+this.getEvent().getNomEvent()+"\n\n");
			for(PhotoEvent p : this.getAlbum()){
				s += p+"\n\n";
			}
			return s;
		}
}

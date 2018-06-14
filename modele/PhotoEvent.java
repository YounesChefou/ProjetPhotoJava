package modele;
import java.io.File;
import exception.*;
import java.util.*;


/**
 * Classe d'une photo de type evenement.
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 */
public class PhotoEvent extends Photo {
		private Event evenement;
		
		/**
	      * Construit une instance de PhotoEvenement
		  * @param nom 	Le nom de la photo évènement
		  * @param evenement 	L'évènement de la photo évènement
		  * @throws PhotoNotFoundException Si la photo évènement n'existe pas
		  * @throws UnhandledFormatException Si la photo évènement a le mauvais format
		  * @throws WrongFileException Si la photo évènement ne se trouve pas au bon endroit
		*/
		public PhotoEvent(String nom, Event evenement) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
			super(nom);
			String ev = evenement.getNomEvent();
			this.BonNomRepertoire(nom, ev);
			this.evenement = evenement;
		}
		
		
		/**
		 * Verifie si le fichier est dans le dossier correspondant à l'evenement.
		 * @param nom le chemin de la photo
		 * @param dossier le nom du dossier
		 * @throws WrongFileException, si la photo n'est pas au bon endroit.
		 */
		public void BonNomRepertoire(String nom, String dossier) throws WrongFileException{
			File f = new File(nom);
			String prt = f.getParent();
			StringTokenizer st = new StringTokenizer(prt, "/");
			st.nextToken();
			if(st.nextToken().equals(dossier) == false) { //On inspecte le deuxième élément du chemin, le premier étant images.
				WrongFileException ew = new WrongFileException(prt,"Mauvais répertoire evenement");
				throw ew;
			}
		}
		
		/**
		 * Renvoie le chemin de la photo.
		 */
		public String getPath(){
			return new String("images/"+this.evenement.getNomEvent()+"/"+this.getNom());
		}
		
		/**
		 * Renvoie l'evenement correspondant à l'evenement.
		 * @return this.evenement, l'evenement.
		 */
		public Event getEvent(){
			return this.evenement;
		}
		
		/**
		 * Renvoie une chaine de caractères décrivant la photo evenement.
		 */
		public String toString(){
			String s = new String(super.toString()+" Evenement : "+this.evenement.getNomEvent());
			return s;
		}
	}

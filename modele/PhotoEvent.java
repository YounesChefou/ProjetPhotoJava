package modele;
import java.io.File;
import exception.*;
import java.util.*;

/**
 * Classe de gestion de photos évènements
 * @author Cheffou Gang
 *
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
		 * Vérifie si la photo évènement est dans le bon répertoire évènement
		 * @param nom 	Le nom de la photo évènement
		 * @param dossier	Le dossier où se trouve la photo évènement
		 * @throws WrongFileException si la photo est dans le mauvais répertoire évènement
		 */
		public void BonNomRepertoire(String nom, String dossier) throws WrongFileException{
			File f = new File(nom);
			String prt = f.getParent();
			if(prt.equals(dossier) == false) {
				WrongFileException ew = new WrongFileException(prt,"Mauvais répertoire evenement");
				throw ew;
			}
			else{
				System.out.println("Bon nom.");
			}
		}
		
		/**
		 * Retourne l'évènement de la photo évènement
		 * @return l'évènement de la photo évènement
		 */
		public Event getEvent(){
			return this.evenement;
		}
		
		public String toString(){
			String s = new String(super.toString()+" Evenement : "+this.evenement.getNomEvent());
			return s;
		}
	}

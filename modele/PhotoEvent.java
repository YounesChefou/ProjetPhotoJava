package modele;
import java.io.File;
import exception.*;
import java.util.*;

/**
 * Classe de gestion de photos évènements
 * @author Younes Cheffou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
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
			super(nom);				// Appelle le constructeur de la classe mère
			String ev = evenement.getNomEvent();	
			this.BonNomRepertoire(nom, ev);		// Vérifie si la photo évènement est dans le bon répertoire en appelant la méthode BonNomRepertoire
			this.evenement = evenement;		// Initialise la variable d'instance
		}
		
		/**
		 * Vérifie si la photo évènement est dans le bon répertoire évènement
		 * @param nom 	Le nom de la photo évènement
		 * @param dossier	Le dossier où se trouve la photo évènement
		 * @throws WrongFileException si la photo est dans le mauvais répertoire évènement
		 */
		public void BonNomRepertoire(String nom, String dossier) throws WrongFileException{
			File f = new File(nom);					// Initialise f avce le nom de la photo évènement
			String prt = f.getParent();				// Initialise ptr avce le parent de f
			if(prt.equals(dossier) == false) {			// Test si ptr correspond au dossier où est la photo évènement
				WrongFileException ew = new WrongFileException(prt,"Mauvais répertoire evenement");
				throw ew; 					// Lance une exception si la condition du if est respectée
			}
			else{
				System.out.println("Bon nom.");			// Affiche "Bon nom" s la condition du if n'est pas respectée
			}
		}
		
		/**
		 * Retourne l'évènement de la photo évènement
		 * @return l'évènement de la photo évènement
		 */
		public Event getEvent(){
			return this.evenement;
		}
		
		/**
		 * Permet d'afficher la photo évènement sous la forme d'une chaîne de caractères
		 * @return la chaîne de caractères
		 */
		public String toString(){
			String s = new String(super.toString()+" Evenement : "+this.evenement.getNomEvent());
			return s;
		}
	}

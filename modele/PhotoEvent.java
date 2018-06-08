package modele;
import java.io.File;
import exception.*;
import java.util.*;

public class PhotoEvent extends Photo {
		private Event evenement;
		
		public PhotoEvent(String nom, Event evenement) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
			super(nom);
			String ev = evenement.getNomEvent();
			this.BonNomRepertoire(nom, ev);
			this.evenement = evenement;
		}
		
		
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
		
		public String getPath(){
			return new String("images/"+this.evenement.getNomEvent()+"/"+this.getNom());
		}
		
		public Event getEvent(){
			return this.evenement;
		}
		
		public String toString(){
			String s = new String(super.toString()+" Evenement : "+this.evenement.getNomEvent());
			return s;
		}
	}

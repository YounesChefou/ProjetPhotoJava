package modele;
/**
 * Classe de test de choix de fichiers sur le disque
 * Pour le projet photo
 * @ version 1.0
 * @author Françoise Gayral
 */

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TestChoixFichier {

	public static void main(String[] args) {
		// Ouvre une boîte de dialogue pour choisir un fichier image sur le disque
		// mettre en paramètre du constructeur le chemin vers le répertoire images
//		JFileChooser fc = new JFileChooser("../../images");
		JFileChooser fc = new JFileChooser("./images");
		// Pour ne choisir que les fichiers qui nous intéressent graĉe à leurs suffixes
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","jpeg");
		fc.setFileFilter(filter);	   
		
		fc.setMultiSelectionEnabled(true) ;			
		
		int returnVal = fc.showOpenDialog(null);
		/* retourne un des 3 mnémoniques : JFileChooser.CANCEL_OPTION,
		 JFileChooser.APPROVE_OPTION ou JFileCHooser.ERROR_OPTION suivant la manière dont 
		l'utilisateur est sorti de la boite de dialogue
		*/	 
		
	    if (returnVal == JFileChooser.APPROVE_OPTION)	{ 
	   			//on récupère alors les fichiers sélectionnés
				File[] files = fc.getSelectedFiles();
				for (File f : files)	{
				String nomFichierPhoto= f.getName();
				System.out.println(nomFichierPhoto);
				}
					
		}	// fin du if
	  /* pour autoriser le choix multiple : même début
	    // voir http://imss-www.upmf-grenoble.fr/prevert/Prog/Java/swing/JFileChooser.html
	    JFileChooser fc = new JFileChooser("../../images");
		// Pour ne choisir que les fichiers qui nous intéressent graĉe à leurs suffixes
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","jpeg");
		fc.setFileFilter(filter);
		// on ajoute cette ligne
		fc.setMultiSelectionEnabled(true) ;	   				
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)	{ 
	   			//on récupère alors les fichiers sélectionnés
				File[] files = fc.getSelectedFiles();
				// afficher le noms des fichiers choisis : à compléter 
		}
*/
}
	}

package modele;

import java.util.*;
import java.text.*;
import java.io.*;
import exception.*;

/**
 * Classe de gestionn d'une photo
 * @author Younes Cheffou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class Photo {
	public static SimpleDateFormat DATEFORMAT;
	
	static{
		DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");	// fixe le format d'une date à "dd/MM/yyyy"
	}
	
	//Variables d'instance
	private String nom;				// Nom de la photo
	private GregorianCalendar date;			// Date de la dernière modification de la photo
	
	/**
	 * Construit une instance de Photo
	 * @param nom 	Le nom de la photo
	 * @throws PhotoNotFoundException Si la photo n'existe pas
	 * @throws UnhandledFormatException Si la photo évènement a le mauvais format
	 * @throws WrongFileException Si la photo ne se trouve pas au bon endroit
	 */
	public Photo(String nom) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
		File f;
		f = new File(nom);
		this.verificationFichier(f);
		Date dt = new Date(f.lastModified());
		String date = Photo.DATEFORMAT.format(dt);
		StringTokenizer st = new StringTokenizer(date, "/");
		int jour = 0;
		int mois = 0;
		int annee = 0;
		while(st.hasMoreTokens()){
			jour = Integer.parseInt(st.nextToken());
			mois = Integer.parseInt(st.nextToken())-1;
			annee = Integer.parseInt(st.nextToken());
		}
		this.nom = f.getName();					// Initialise la variable d'instance nom
		this.date = new GregorianCalendar(annee, mois, jour);	// Initialise la variable d'instance date
	}
	
	/**
	 * Retourne le nom de la photo
	 * @return le nom de la photo
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Retourne le chemin de la photo
	 * @return images/ et le nom de la photo
	 */
	public String getPath(){
		return new String("images/"+this.nom);
	}
	
	/**
	 * Retourne la date de prise de la photo
	 * @return this.date, date de la photo
	 */
	public GregorianCalendar getDate(){
		return this.date;
	}
	
	/**
	 * Verifie que le fichier donné en paramètre correspond bien aux critères établis pour la classe Photo.
	 * @param f le fichier à verifier
	 * @throws PhotoNotFoundException, si le fichier est inexistant
	 * @throws UnhandledFormatException, si le format du fichier ne correspond aux formats pris en charge
	 * @throws WrongFileException, si le fichier ne se trouve pas au bon endroit
	 */
	public void verificationFichier(File f) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
		if(!f.exists()) {
			PhotoNotFoundException e = new PhotoNotFoundException(nom, "Photo inexistante");
			throw e;
		}
		
		StringTokenizer nt = new StringTokenizer(f.getName(), ".");
		String Ft = null;
		while(nt.hasMoreTokens()){
			Ft = nt.nextToken();
		}
		if(!(Ft.equals("jpg") || Ft.equals("jpeg") || Ft.equals("gif")) ) {
			UnhandledFormatException eu = new UnhandledFormatException(Ft,"Mauvais format");
			throw eu;
		}
		StringTokenizer st = new StringTokenizer(f.getPath(), "/");
		String dossier = st.nextToken();
		if(!dossier.equals("images")){
			WrongFileException ew = new WrongFileException(dossier ,"Mauvais répertoire");
			throw ew;
		}
	}
	
	/**
	 * Change le nom de la photo
	 * @param nom 	Le nouveau nom de la photo
	 */
	public void setNom(String nom){
		this.nom = nom;
	}
	
	/**
	 * Permet d'afficher la photo sous la forme d'une chaîne de caractères
	 */
	public String toString(){
		String s = new String("Nom de la photo : "+this.nom+" prise le "+Photo.DATEFORMAT.format(this.date.getTime()));
		return s;
	}
}

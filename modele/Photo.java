package modele;

import java.util.*;
import java.text.*;
import java.io.*;
import exception.*;

public class Photo {
	public static SimpleDateFormat DATEFORMAT;
	
	static{
		DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	private String nom;
	private GregorianCalendar date;
	
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
		this.nom = f.getName();
		this.date = new GregorianCalendar(annee, mois, jour);
	}
	
	public String getNom(){
		return this.nom;
	}
	
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
			PhotoNotFoundException e = new PhotoNotFoundException(f.getName(), "Photo inexistante");
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
	* Permet de determiner si deux instances Photo sont identiques
	* @return true si oui, false sinon
	*/
	public boolean equals(Object o){
		Photo p = null;
		if(o instanceof Photo){
			p = (Photo)o;
		}
		if(this.nom.equals(p.nom) && this.date.equals(p.date))
			return true;
		
	return false;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	public String toString(){
		String s = new String("Nom de la photo : "+this.nom+" prise le "+Photo.DATEFORMAT.format(this.date.getTime()));
		return s;
	}
}

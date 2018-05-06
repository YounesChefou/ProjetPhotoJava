
import java.util.*;
import java.text.*;
import java.io.*;

public class Photo {
	public static SimpleDateFormat DATEFORMAT;
	
	static{
		DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	private String nom;
	private GregorianCalendar date;
	
	public Photo(String nom) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException{
		
		File f;
		String s = "";
		f = new File(nom);
		
		if(!f.exists()) {
			PhotoNotFoundException e = new PhotoNotFoundException(nom, "Photo inexistante");
			throw e;
		}
		
		StringTokenizer nt = new StringTokenizer(nom, ".");
		String Ft = null;
		while(nt.hasMoreTokens()){
			Ft = nt.nextToken();
		}
		
		if(!(Ft.equals("jpg") || Ft.equals("jpeg") || Ft.equals("gif")) ) {
			UnhandledFormatException eu = new UnhandledFormatException(Ft,"Mauvais format");
			throw eu;
		}
		
		String prt = f.getParent();
		if(this instanceof PhotoEvent == false){
				this.BonNomRepertoire(nom,s);
		}
		
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
	
	public GregorianCalendar getDate(){
		return this.date;
	}
	
	public void BonNomRepertoire(String nom, String dossier) throws WrongFileException{
		dossier = "images";
		File f = new File(nom);
		String prt = f.getParent();
		if(prt.equals(dossier) == false) {
			WrongFileException ew = new WrongFileException(prt ,"Mauvais répertoire");
			throw ew;
		}
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	public String toString(){
		String s = new String("Nom de la photo : "+this.nom+" prise le "+Photo.DATEFORMAT.format(this.date.getTime()));
		return s;
	}
}

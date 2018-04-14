
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
	
	public Photo(String nom){
		File f;
		f = new File(nom);
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
	
	public void setNom(String nom){
		this.nom = nom;
	}
	public String toString(){
		String s = new String("Nom de la photo : "+this.nom+" prise le "+Photo.DATEFORMAT.format(this.date.getTime()));
		return s;
	}
}

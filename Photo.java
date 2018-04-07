package photos;
import java.util.*;
import java.text.*;
import java.io.*;

public class Photo {
	public static String PARENT;
	static{
		PARENT="/home/lephoquebleu/Documents/Java/photos/src/photos/images";
	}
	private String nom;
	private GregorianCalendar date;
	
	/*
	public Photo(File fichier){
		String s = fichier.getName();
		StringTokenizer t = new StringTokenizer(s, ".");
		this.nom = t.nextToken();
		Date dt = new Date(fichier.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:m");
		this.date = sdf.format(dt);
	}
	*/
	public Photo(String nom){
		File f;
		f = new File(Photo.PARENT,nom);
		Date dt = new Date(f.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(dt);
		StringTokenizer st = new StringTokenizer(date, "/");
		int jour = 0;
		int mois = 0;
		int annee = 0;
		while(st.hasMoreTokens()){
			jour = Integer.parseInt(st.nextToken());
			mois = Integer.parseInt(st.nextToken())-1;
			annee = Integer.parseInt(st.nextToken());
		}
		this.nom = nom;
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
		String s = new String("Nom de la photo : "+this.nom+" prise le "+this.date);
		return s;
	}
}

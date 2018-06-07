package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Classe de gestion d'évènement
 * @author Younes Cheffou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class Event {
	private String nom;
	private ArrayList<Personne> listePersonne;
	
	/**
	 * Construit une instance d'event
	 * @param nom	 Le nom de l'évènement
	 */
	public Event(String nom){
		this.nom = nom;
		this.listePersonne = new ArrayList<Personne>();
	}
	
	/**
	 * Retourne le nom de l'évènement
	 * @return le nom de l'évènement
	 */
	public String getNomEvent(){
		return this.nom;
	}
	
	/**
	 * Retourne la liste des personnes associées à l'évènement
	 * @return une ArrayList des personnes associées à l'évènement
	 */
	public ArrayList<Personne> getListe(){
		return this.listePersonne;
	}
	
	/**
	 * Change le nom de l'évènement
	 * @param nom 	Le nouveau nom de l'évènement
	 */
	public void setNomEvent(String nom){
		this.nom = nom;
	}
	
	/**
	 * Change la liste des personnes associées à l'évènement
	 * @param liste 	La nouvelle liste de personnes
	 */
	public void setListe(ArrayList<Personne> liste){
		this.listePersonne = liste;
	}
	
	/**
	 * Ajoute une personne à la liste de personnes de l'évènement
	 * @param p 	La personne à ajouter
	 */
	public void ajouterPersonne(Personne p){
		this.listePersonne.add(p);
	}
	
	/**
	 * Permet de remplir un évènement à partir d'un fichier texte
	 * @param fichier 	Le nom du fichier pour remplir l'évènement
	*/
	public void charge(String fichier){
		BufferedReader bIn = null;
		String ligne = null;
		String nom = null;
		Personne p = null;
		String mail = null;
		ArrayList<Personne> liste = new ArrayList<Personne>();
		try{
			File inputFile = new File(fichier);
			bIn = new BufferedReader(new FileReader(inputFile));
			ligne = bIn.readLine();
			StringTokenizer st = new StringTokenizer(ligne, ": ");
			while(st.hasMoreTokens()){
				nom = st.nextToken();
			}
			this.setNomEvent(nom);
			ligne = bIn.readLine();
			while (ligne != null){
				StringTokenizer st2 = new StringTokenizer(ligne, "/");
				nom = st2.nextToken();
				mail = st2.nextToken();
				p = new Personne(nom,mail);
				liste.add(p);
				ligne = bIn.readLine();
			}
		}
		catch(FileNotFoundException e) { 
			System.out.println(e) ;
      }    
		catch(IOException e) { 
			System.out.println(e) ;
	                }
		finally	{
			this.setListe(liste);
			if (bIn != null) {
					try {
						bIn.close();
							}
					catch(IOException ec) { 
						System.out.println(ec) ;
	                	}
					}
			}
		
		
	}
	
	/**
	 * Permet de sauvegarder dans un fichier, le nom de l'évènement et les personnes concernées
	 * @param fichier 	nom du fichier de sauvegarde.
	 */
	public void sauv(String fichier){
		BufferedWriter bOut = null;
		FileWriter fOut = null;
		try{
			bOut = new BufferedWriter(new FileWriter(fichier));
			bOut.write("Event: " + this.getNomEvent());
			bOut.newLine();
			for(Personne p : this.getListe()){
					bOut.write(p.getNom() + "/" + p.getMail());
					bOut.newLine();
				}
			}
				 
		catch(IOException e) { 
			System.out.println(e) ;
	                }
		finally	{
			if (bOut != null) {
					try {
						bOut.close();
							}
					catch(IOException ec) { 
						System.out.println(ec) ;
	                	}
					}
			}
		
	}
	
	/**
	 * Permet d'afficher un évènement sous la forme d'une chaîne de caractères
	*/
	public String toString(){
		String s = new String("Evenement : "+ this.nom+"\n\n");
		for(Personne p : this.getListe()){
			s += p+"\n\n";
		}
		return s;
	}
}

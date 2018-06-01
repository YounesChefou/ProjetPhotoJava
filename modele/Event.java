package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Event {
	private String nom;
	private ArrayList<Personne> listePersonne;
	
	public Event(String nom){
		this.nom = nom;
		this.listePersonne = new ArrayList<Personne>();
	}
	
	public String getNomEvent(){
		return this.nom;
	}
	
	public ArrayList<Personne> getListe(){
		return this.listePersonne;
	}

	public void setNomEvent(String nom){
		this.nom = nom;
	}
	
	public void setListe(ArrayList<Personne> liste){
		this.listePersonne = liste;
	}
	public void ajouterPersonne(Personne p){
		this.listePersonne.add(p);
	}
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
	
	public String toString(){
		String s = new String("Evenement : "+ this.nom+"\n\n");
		for(Personne p : this.getListe()){
			s += p+"\n\n";
		}
		return s;
	}
}

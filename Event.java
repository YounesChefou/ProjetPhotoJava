package photos;
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
	public void ajouterPersonne(Personne p){
		this.getListe().add(p);
	}
	
	
}

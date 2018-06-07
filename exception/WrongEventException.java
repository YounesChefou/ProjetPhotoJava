package exception;
import modele.*;

public class WrongEventException extends Exception{
	private String evenement;
	
	/**
	*
	* @param e
	* @param message	
	*/
	
	public WrongEventException(Event e, String message){
		super(message);
		this.evenement = e.getNomEvent();
	}
	
	/**
	* Retourne l'exception sous la forme d'une chaîne de caractères.
	*/
	
	public String toString(){
		return super.toString()+", Evenement de la photo : " + this.evenement;
	}
}

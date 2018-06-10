package exception;
import modele.*;
/**
 * Classe décrivant l'exception lancée quand l'évènement d'une photoEvent ne correpond pas à l'événement de l'albumPhotoEvent dans lequel on veut l'ajouter.
 */
public class WrongEventException extends Exception{
	private String evenement;
	
	/**
	* L'évenement ne correspond pas a l'album évenement
	* @param e		nom de l'évenement
	* @param message	affiche un message lors de l'ouverture de l'exception
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

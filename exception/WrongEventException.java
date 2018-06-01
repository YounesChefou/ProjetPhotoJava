package exception;
import modele.*;

public class WrongEventException extends Exception{
	private String evenement;
	
	public WrongEventException(Event e, String message){
		super(message);
		this.evenement = e.getNomEvent();
	}
	
	public String toString(){
		return super.toString()+", Evenement de la photo : " + this.evenement;
	}
}

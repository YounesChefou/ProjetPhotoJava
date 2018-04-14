
public class PhotoEvent extends Photo {
		private Event evenement;
		
		public PhotoEvent(String nom, Event evenement){
			super(nom);
			this.evenement = evenement;
		}
		
		public String toString(){
			String s = new String(super.toString()+" Evenement : "+this.evenement.getNomEvent());
			return s;
		}
	}

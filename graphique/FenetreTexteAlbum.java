package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import graphique.FenetreAlbum.MenuListener;
import modele.*;

/**
 * Classe d'interface graphique pour un album photo (sous forme de texte).
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 */
public class FenetreTexteAlbum extends JFrame implements Observer{
		private AlbumPhoto album;
		private JTextArea  liste;
		
		public FenetreTexteAlbum(int x, int y, int w, int h, AlbumPhoto album){
			super(album.getNom());
			this.album = album;
			album.addObserver(this);
			this.initialiseComposants();
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //Pour empêcher la fenêtre de se fermer
			this.setBounds(x,y,w,h);
			this.setVisible(true);
		}
		
		/**
		* Initialise les différents composants de la fenêtre text sous forme de liste.
	 	*/
		
		private void initialiseComposants(){
			this.liste = new JTextArea(this.album.toString());
			this.add(liste, BorderLayout.CENTER);
		}
		
		
		public void update(Observable o, Object arg){
			this.liste.setText(this.album.toString());
		}
		
		public AlbumPhoto getAlbum(){
			return this.album;
		}
}

package graphique;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import modele.*;

public class FenetreTexteAlbumEvent extends JFrame implements Observer{
	private AlbumPhotoEvent album;
	private JTextArea  liste;
	
	public FenetreTexteAlbumEvent(int x, int y, int w, int h, AlbumPhotoEvent al){
		super(al.getNom());
		this.album = al;
		al.addObserver(this);
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

	/**
 	* Met a jour la liste des photos dans le fenetre text.
 	* @param o, album photo .
	* @param arg, .
 	*/
	
	public void update(Observable o, Object arg) {
		this.liste.setText(this.album.toString());
	}
}

package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import modele.AlbumControleurEvent;
import modele.AlbumPhoto;
import modele.AlbumPhotoEvent;
import modele.PhotoEtatAlbum;

public class FenetreTexteAlbumEvent extends JFrame implements Observer{
	private AlbumPhotoEvent album;
	private AlbumControleurEvent controleur;
	private JTextArea  liste;
	
	public FenetreTexteAlbumEvent(int x, int y, int w, int h, AlbumPhotoEvent al){
		super(al.getNom());
		this.album = al;
		al.addObserver(this);
		this.controleur = new AlbumControleurEvent(al);
		this.initialiseComposants();
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
 	* Met ajour la liste des photos dans le fenetre text.
 	* @param o, album photo .
	* @param arg, .
 	*/
	
	public void update(Observable o, Object arg) {
	if(arg instanceof PhotoEtatAlbum) {
		PhotoEtatAlbum maPhotoEtat = (PhotoEtatAlbum) arg;
		if(maPhotoEtat.getEtat().equals("photo ajoutée")) {
			this.liste.append(maPhotoEtat.getNomPhoto()+"\n");
		}
		else {
			String nouveauAlbum = this.album.toString();
			System.out.println(maPhotoEtat.getNomPhoto());
			nouveauAlbum = nouveauAlbum.replace(maPhotoEtat.getNomPhoto(),"");
			this.liste.setText(nouveauAlbum);
		}
	}
}
}

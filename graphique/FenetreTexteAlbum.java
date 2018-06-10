package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import graphique.FenetreAlbum.MenuListener;
import modele.*;

public class FenetreTexteAlbum extends JFrame implements Observer{
		private AlbumPhoto album;
		private AlbumControleur controleur;
		private JTextArea  liste;
		
		public FenetreTexteAlbum(int x, int y, int w, int h, AlbumPhoto al){
			super(al.getNom());
			this.album = al;
			al.addObserver(this);
			this.controleur = new AlbumControleur(al);
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
				String nouveauAlbum = this.al.toString();
				System.out.println(maPhotoEtat.getNomPhoto());
				nouveauAlbum = nouveauAlbum.replace(maPhotoEtat.getNomPhoto(),"");
				this.liste.setText(nouveauAlbum);
			}
		}
		
	}
}

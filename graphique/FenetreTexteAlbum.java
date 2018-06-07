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
		
		public FenetreTexteAlbum(int x, int y, int w, int h, AlbumPhoto album){
			super(album.getNom());
			this.initialiseMenu();
			this.album = album;
			album.addObserver(this);
			this.controleur = new AlbumControleur(album);
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
		* Initialise les différents composants du Menu.
	 	*/
		private void initialiseMenu(){
			JMenuBar menuBar = new JMenuBar();
			this.setJMenuBar(menuBar);
			JMenu mdef = new JMenu("Photos");
			menuBar.add(mdef);
			JMenuItem mdefAjouter = new JMenuItem("Ajouter une photo");
			JMenuItem mdefEnlever = new JMenuItem("Enlever une photo");
			mdef.add(mdefAjouter);
			mdef.add(mdefEnlever);
			mdefAjouter.addActionListener(new MenuListener("Ajouter une photo"));
			mdefAjouter.addActionListener(new MenuListener("Enlever une photo"));
		}
		/**
	 	* Met ajour la liste des photos.
	 	* @param o, .
		* @param arg, .
	 	*/
		
		public void update(Observable o, Object arg){
			this.liste.setText(this.album.toString());
		}
		
		public AlbumPhoto getAlbum(){
			return this.album;
		}
		
		public void menuFrame(){
			this.liste.setText(this.album.toString());
		}
		
		class MenuListener implements ActionListener{
			private String menubar;
			
			public MenuListener(String mb){
				this.menubar = mb;
			}
			
			public void actionPerformed(ActionEvent e){
				Photo p = null;
				switch(this.menubar){
				case "Ajouter une photo":
					try {
						p = new Photo("images/franku.jpg");
						} 
					catch (Exception e1) {
						System.out.println(e1);
						}
					FenetreTexteAlbum.this.album.ajouterPhoto(p);
					break;
				case "Enlever une photo":
					break;
				}
			FenetreTexteAlbum.this.menuFrame();
			}
		}
}

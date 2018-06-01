package graphique;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.AlbumPhoto;
import modele.Photo;

public class FenetreListeAlbum extends JFrame {
	private AlbumPhoto album;

	public FenetreListeAlbum(int x, int y, int w, int h, AlbumPhoto album){
		super(album.getNom());
		this.initialiseMenu();
		this.album = album;
		this.initialiseComposants();
		this.setBounds(x,y,w,h);
		this.setVisible(true);
	}
	
	private void initialiseComposants(){
		DefaultListModel<Photo> def = new DefaultListModel<Photo>();
		for(Photo p : this.album.getAlbum())
			def.addElement(p);
		JList<Photo>liste = new JList<Photo>(def);
		this.add(liste, BorderLayout.CENTER);
	}
	private void initialiseMenu(){
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu mdef = new JMenu("Photos");
		menuBar.add(mdef);
		mdef.add(new JMenuItem("Ajouter une photo"));
		mdef.add(new JMenuItem("Enlever une photo"));
	}
	
}

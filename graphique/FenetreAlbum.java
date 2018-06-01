package graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.PhotoNotFoundException;
import exception.UnhandledFormatException;
import exception.WrongFileException;
import modele.*;

public class FenetreAlbum extends JFrame{
	private AlbumPhoto album;
	private JLabel labNomPhoto;
	private JLabel labPhotoIcon;
	private int positionAlbum;
	private JLabel labPositionAlbum;
	private JButton Bprec;
	private JButton Bsuiv;
	
	
	public FenetreAlbum(int x, int y, int w, int h, AlbumPhoto album){
		super(album.getNom());
		this.initialiseMenu();
		this.album = album;
		this.initComposants(album.getPhotoAt(0));
		this.setBounds(x,y,w,h);
		this.setVisible(true);
	}
	
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
	public void initComposants(Photo p){
		this.labNomPhoto = new JLabel(p.getNom(), SwingConstants.CENTER);
		this.add(this.labNomPhoto, BorderLayout.NORTH);
		this.labPhotoIcon = new JLabel(new ImageIcon(p.getPath()), SwingConstants.CENTER);
		this.add(this.labPhotoIcon, BorderLayout.CENTER);
		JPanel boutons = new JPanel();
		this.Bprec = new JButton("<<");
		this.Bsuiv = new JButton(">>");
		this.labPositionAlbum = new JLabel(1+"/"+this.getTaille());
		Bprec.addActionListener(new ChangeListener("<<"));
		Bsuiv.addActionListener(new ChangeListener(">>"));
		Bprec.setEnabled(false);
		boutons.add(this.Bprec);
		boutons.add(this.Bsuiv);
		boutons.add(labPositionAlbum);
		this.add(boutons, BorderLayout.SOUTH);
	}
	
	public int getTaille(){
		return this.album.getTaille();
	}
	
	public void changeFrame(){
		Photo ph = this.album.getPhotoAt(this.positionAlbum-1);
		this.labPhotoIcon.setIcon(new ImageIcon(ph.getPath()));
		this.labNomPhoto.setText(ph.getNom());
		this.labPositionAlbum.setText(this.positionAlbum+"/"+this.getTaille());
		if(this.positionAlbum==this.getTaille()){
			this.Bsuiv.setEnabled(false);
		}
		else if(this.positionAlbum==1){
			this.Bprec.setEnabled(false);
		}
		else{
			this.Bprec.setEnabled(true);
			this.Bsuiv.setEnabled(true);
		}
	}
	
	public void menuFrame(){
		this.labPositionAlbum.setText(this.positionAlbum+"/"+this.getTaille());
		if(this.positionAlbum==0)this.positionAlbum=1;
		if(this.positionAlbum<this.getTaille()){
			this.Bsuiv.setEnabled(true);
		}
	}
	
	class ChangeListener implements ActionListener{
		private String bouton;
		
		public ChangeListener(String b){
			this.bouton = b;
		}
		public void actionPerformed(ActionEvent e){
			switch(this.bouton){
			case ">>":
				FenetreAlbum.this.positionAlbum++;
				break;
			case "<<":
				FenetreAlbum.this.positionAlbum--;
				break;
			}
			FenetreAlbum.this.changeFrame();
		}
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
				try{
				FenetreAlbum.this.album.ajouterPhotosFileChooser();
				}
				catch(PhotoNotFoundException ex){
					System.out.println(ex);
				}
				catch(UnhandledFormatException exc){
					System.out.println(exc);
				}
				catch(WrongFileException exce){
					System.out.println(exce);
				}
				break;
			case "Enlever une photo":
				break;
			}
		FenetreAlbum.this.menuFrame();
			
		}
	}
}

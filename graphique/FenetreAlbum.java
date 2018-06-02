package graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import exception.PhotoNotFoundException;
import exception.UnhandledFormatException;
import exception.WrongFileException;
import modele.*;

public class FenetreAlbum extends JFrame implements Observer{
	private AlbumPhoto album;
	private AlbumControleur controleur;
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
		this.controleur = new AlbumControleur(album);
		album.addObserver(this);
		this.initComposants(album.getPhotoAt(0));
		this.setBounds(x,y,w,h);
		this.setVisible(true);
	}
	
	/**
	 * Initialise les différents composants de la barre de menu.
	 * 
	 */
	private void initialiseMenu(){
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu mdef = new JMenu("Photos");
		menuBar.add(mdef);
		JMenuItem mdefAjouter = new JMenuItem("Ajouter une photo");
		JMenuItem mdefEnlever = new JMenuItem("Enlever une photo");
		JMenuItem mdefSauv = new JMenuItem("Sauvegarder l'album");
		mdef.add(mdefAjouter);
		mdef.add(mdefEnlever);
		mdef.add(mdefSauv);
		mdefAjouter.addActionListener(new MenuListener("Ajouter une photo"));
		mdefEnlever.addActionListener(new MenuListener("Enlever une photo"));
		mdefSauv.addActionListener(new MenuListener("Sauvegarder l'album"));
	}
	/**
	 * Initialise les différents composants de la fenêtre.
	 * @param p, la Photo qui sera affichée au lancement.
	 */
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
	
	/**
	 * Permet de choisir une ou plusieurs images, et renvoie un tableau avec les fichiers correspondants.
	 * @return files, le tableau de fichiers.
	 */
	public File[] chooseFiles(){
		File[] files = null;
		JFileChooser fc = new JFileChooser("./images");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","jpeg");
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(true) ;			
		int returnVal = fc.showOpenDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION){
				files = fc.getSelectedFiles();		
	    }
	    return files;
	}
	
	/**
	 * Ouvre une fenêtre qui demande confirmation de la sauvegarde à l'utilisateur.
	 */
	public void confirmationSauv(){
		int choix = JOptionPane.showConfirmDialog(null,"Sauvegarder l'album dans son état actuel ?", this.album.getNom(), JOptionPane.YES_NO_OPTION);
		if(choix==JOptionPane.YES_OPTION){
			String nomFichier = JOptionPane.showInputDialog("Entrez le nom du fichier.");
			this.controleur.notificationSauvModele(nomFichier);
		}
	}
	
	/**
	 * Met à jour la fenêtre à chaque changement.
	 */
	public void miseAjour(){
		if(this.positionAlbum==0)this.positionAlbum=1;
		Photo ph = this.album.getPhotoAt(this.positionAlbum-1);
		this.labPhotoIcon.setIcon(new ImageIcon(ph.getPath()));
		this.labNomPhoto.setText(ph.getNom());
		this.labPositionAlbum.setText(this.positionAlbum+"/"+this.getTaille());
		this.activeBoutons();
	}
	
	/**
	 * Active ou désactive les boutons de la fenêtre en fonction de la position de l'utilisateur dans l'album.
	 */
	
	public void activeBoutons(){
		if(this.positionAlbum==0)this.positionAlbum=1;
		
		if(this.positionAlbum<this.getTaille()&&this.positionAlbum>1){
			this.Bprec.setEnabled(true);
			this.Bsuiv.setEnabled(true);
		}
		else if(this.positionAlbum==this.getTaille()){
			this.Bsuiv.setEnabled(false);
		}
		else if(this.positionAlbum==1){
			this.Bprec.setEnabled(false);
		}
	}
	
	/**
	 * Méthode appelée à chaque fois que le modèle change. Obligatoire car la classe implémente l'interface Observer.
	 *  @param o : l'Observer qui a  notifié un changement
	 *  @param arg : l'objet (pour être le plus général) qui représente le changement de l'Oberver : ici le nom du nouveau film (en fait une String)
	 */
	
	public void update(Observable o, Object arg) {
		this.miseAjour();
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
			FenetreAlbum.this.miseAjour();
		}
	}
	
	class MenuListener implements ActionListener{
		private String option;
		
		public MenuListener(String mb){
			this.option = mb;
		}
		
		public void actionPerformed(ActionEvent e){
			Photo p = null;
			
			switch(this.option){
			case "Ajouter une photo":
				File[]files = FenetreAlbum.this.chooseFiles();
				FenetreAlbum.this.controleur.notificationAjoutModele(files);
				break;
			case "Enlever une photo":
				break;
			case "Sauvegarder l'album":
				FenetreAlbum.this.confirmationSauv();
				break;
			}	
		}
	}
}

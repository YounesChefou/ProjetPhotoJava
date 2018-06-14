package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import exception.PhotoAlreadyHereException;
import graphique.FenetreAlbumEvent.ChangeListener;
import graphique.FenetreAlbumEvent.MenuListener;
import modele.*;
import exception.*;
/**
 * Classe d'interface graphique pour un album photo lié à un evenement.
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 */

public class FenetreAlbumEvent extends JFrame implements Observer{
	
	private AlbumPhotoEvent album;
	private AlbumControleurEvent controleur;
	private JLabel labNomPhoto;
	private LabelImage labPhotoIcon;
	private int positionAlbum;
	private JLabel labPositionAlbum;
	private JButton Bprec;
	private JButton Bsuiv;
	
	
	/**
	 * Crée une fenetre pour visionner un album photo d'un evenement.
	 * @param x abscisse du côté gauche supérieur de la fenetre
	 * @param y ordonnée du côté gauche supéieur de la fenetre
	 * @param w la largeur de la fenetre
	 * @param h la hauteur de la fenetre
	 * @param album l'album photo
	 */
	public FenetreAlbumEvent(int x, int y, int w, int h, AlbumPhotoEvent album){
		super(album.getNom());
		this.initialiseMenu();
		this.album = album;
		this.controleur = new AlbumControleurEvent(album);
		album.addObserver(this);
		this.initComposants(album.getPhotoAt(0));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(x,y,w,h);
		this.setVisible(true);
	}
	
	/**
	 * Initialise les différents composants de la barre de menu.
	 */
	private void initialiseMenu(){
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu mdef = new JMenu("Photos");
		JMenu mEvent = new JMenu("Event");
		JMenu mJeu = new JMenu("Jeu");
		/*Menu Photos*/
		menuBar.add(mdef);
		JMenuItem mdefAjouter = new JMenuItem("Ajouter une photo");
		JMenuItem mdefEnlever = new JMenuItem("Enlever une photo");
		JMenuItem mdefTrier = new JMenuItem("Trier l'album par date");
		JMenuItem mdefSauv = new JMenuItem("Sauvegarder l'album");
		mdef.add(mdefAjouter);
		mdef.add(mdefEnlever);
		mdef.add(mdefTrier);
		mdef.add(mdefSauv);
		mdefAjouter.addActionListener(new MenuListener("Ajouter une photo"));
		mdefEnlever.addActionListener(new MenuListener("Enlever une photo"));
		mdefTrier.addActionListener(new MenuListener("Trier l'album par date"));
		mdefSauv.addActionListener(new MenuListener("Sauvegarder l'album"));
		/* Menu Event */
		menuBar.add(mEvent);
		JMenuItem mEventAjouterPers = new JMenuItem("Ajouter une personne");
		JMenuItem mEventEnleverPers = new JMenuItem("Enlever une personne");
		mEvent.add(mEventAjouterPers);
		mEvent.add(mEventEnleverPers);
		mEventAjouterPers.addActionListener(new MenuListener("Ajouter une personne"));
		mEventEnleverPers.addActionListener(new MenuListener("Enlever une personne"));
		/*Menu Jeu*/
		menuBar.add(mJeu);
		JMenuItem mLancerJeu = new JMenuItem("Lancer le jeu");
		mJeu.add(mLancerJeu);
		mLancerJeu.addActionListener(new MenuListener("Lancer le jeu"));
		
	}
	/**
	 * Initialise les différents composants de la fenêtre.
	 * @param p, la Photo qui sera affichée au lancement.
	 */
	public void initComposants(Photo p){
		this.labNomPhoto = new JLabel(p.getNom(), SwingConstants.CENTER);
		this.add(this.labNomPhoto, BorderLayout.NORTH);
		this.labPhotoIcon = new LabelImage(p.getPath());
		this.add(this.labPhotoIcon, BorderLayout.CENTER);
		JPanel boutons = new JPanel();
		this.Bprec = new JButton("<<");
		this.Bsuiv = new JButton(">>");
		this.positionAlbum = 1;
		this.labPositionAlbum = new JLabel(this.positionAlbum+"/"+this.getTaille());
		Bprec.addActionListener(new ChangeListener("<<"));
		Bsuiv.addActionListener(new ChangeListener(">>"));
		this.activeBoutons();
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
		String nomEvent = this.album.getNom();
		JFileChooser fc = new JFileChooser("./images/"+nomEvent);
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
	 * Ouvre une fenêtre qui permet à l'utilisateur de jouer à un jeu.
	 */
	
	public void lancementJeu() {
	try {
		new FenetreJeux();
	}
	catch(PhotoNotFoundException e) {
		System.out.println(e);
	}
	catch(UnhandledFormatException ex) {
		System.out.println(ex);
	}
	catch(WrongFileException exc) {
		System.out.println(exc);
	}
	catch(WrongEventException exce) {
		System.out.println(exce);
	}
	}
	/**
	*Ouvre une fenetre qui demande le nom et l'adresse mail de la personne à ajouter
	*/
	
	public void confirmationAjoutPers() {
		JLabel labelnomPers = new JLabel("Nom");
		JTextField nomPers = new JTextField();

		JLabel labelAdresseMail = new JLabel("Adresse mail");
		JTextField adrMail = new JTextField();

		Object[] infos = { labelnomPers, nomPers, labelAdresseMail, adrMail };

		int res = JOptionPane.showConfirmDialog(null, infos, "Ajouter une personne", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (res == JOptionPane.OK_OPTION) {
	    		this.controleur.notificationAjoutPersonne(nomPers.getText(), adrMail.getText());
		}
	}
	
	/**
	 * Ouvre une fenetre permettant de selectionner la personne à retirer de l'evenement
	 */
	
	public void confirmationDelPers(){
		ArrayList<Personne> lPers = this.album.getEvent().getListe();
		Personne[]liste = new Personne[lPers.size()];
		liste = lPers.toArray(liste);
		Personne p = (Personne)JOptionPane.showInputDialog(null,
				"Selectionner la personne à retirer","",
				JOptionPane.QUESTION_MESSAGE, null, liste,null);
		this.controleur.notificationDelPers(p);
//		Cat selectedCat = (Cat)JOptionPane.showInputDialog(appFrame, "title", JOptionPane.QUESTION_MESSAGE, null, allCatsArray, null);
	}
	/**
	 * Met à jour la fenêtre à chaque changement.
	 */
	public void miseAjour(){
		if(this.positionAlbum==0&&this.getTaille()!=0)this.positionAlbum=1;
		if(this.getTaille()==0){
			this.labNomPhoto.setText("Aucune photo dans l'album.");
			this.labPhotoIcon.setImage("");
			this.labPhotoIcon.repaint();
			this.labPositionAlbum.setText(this.positionAlbum+"/"+this.getTaille());
			this.activeBoutons();
		}
		else{
			Photo ph = this.album.getPhotoAt(this.positionAlbum-1);
	//		this.labPhotoIcon.setIcon(new ImageIcon(ph.getPath()));
			this.labPhotoIcon.setImage(ph.getPath());
			this.labPhotoIcon.repaint();
			this.labNomPhoto.setText(ph.getNom());
			this.labPositionAlbum.setText(this.positionAlbum+"/"+this.getTaille());
			this.activeBoutons();
		}
	}
	
	/**
	 * Active ou désactive les boutons de la fenêtre en fonction de la position de l'utilisateur dans l'album.
	 */
	
	public void activeBoutons(){
		if(this.positionAlbum==0)this.positionAlbum=1;
		
		if(this.positionAlbum==1){
			this.Bprec.setEnabled(false);
		}
		
		if(this.positionAlbum<this.getTaille()){
			this.Bsuiv.setEnabled(true);
		}
		if(this.positionAlbum<this.getTaille()&&this.positionAlbum>1){
			this.Bprec.setEnabled(true);
			this.Bsuiv.setEnabled(true);
		}
		else if(this.positionAlbum==this.getTaille()){
			this.Bsuiv.setEnabled(false);
			if(this.positionAlbum>1){
				this.Bprec.setEnabled(true);
			}
		}
	}
	
	/**
	 * Méthode appelée à chaque fois que le modèle change. Obligatoire car la classe implémente l'interface Observer.
	 *  @param o : l'Observer qui a  notifié un changement
	 *  @param arg : l'objet (pour être le plus général) qui représente le changement de l'Observer
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
				FenetreAlbumEvent.this.positionAlbum++;
				break;
			case "<<":
				FenetreAlbumEvent.this.positionAlbum--;
				break;
			}
			FenetreAlbumEvent.this.miseAjour();
		}
	}
	
	class MenuListener implements ActionListener{
		private String option;
		
		public MenuListener(String mb){
			this.option = mb;
		}
		
		public void actionPerformed(ActionEvent e){
			int pos = FenetreAlbumEvent.this.positionAlbum;
			int taille = FenetreAlbumEvent.this.getTaille();
			Photo ph = null;
			switch(this.option){
			case "Ajouter une photo":
				File[]files = FenetreAlbumEvent.this.chooseFiles();
				try{
					FenetreAlbumEvent.this.controleur.notificationAjoutModele(files);
				}
				catch(PhotoAlreadyHereException excep){
					System.out.println(excep);
				}
				break;
			case "Enlever une photo":
				if(pos<taille&&pos>=1){
					ph = FenetreAlbumEvent.this.album.getPhotoAt(pos-1);
					FenetreAlbumEvent.this.controleur.notificationDelModele(ph);
				}
				else if(pos>=taille-1){
					FenetreAlbumEvent.this.positionAlbum--;
					ph = FenetreAlbumEvent.this.album.getPhotoAt(taille-1);
					FenetreAlbumEvent.this.controleur.notificationDelModele(ph);
				}
				break;
			case "Ajouter une personne":
				FenetreAlbumEvent.this.confirmationAjoutPers();
				break;
			case "Enlever une personne":
				FenetreAlbumEvent.this.confirmationDelPers();
				break;
			case "Lancer le jeu":
				FenetreAlbumEvent.this.lancementJeu();
				break;
			case "Trier l'album par date":
				FenetreAlbumEvent.this.controleur.notificationTriModele();
				break;
			case "Sauvegarder l'album":
				FenetreAlbumEvent.this.confirmationSauv();
				break;
			}
		}
	}

}

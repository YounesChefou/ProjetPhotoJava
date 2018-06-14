package graphique;

import modele.*;
import modele.Event;
import exception.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Classe de gestion d'une fenetre de jeu, permettant de retrouver l'�v�nement correspondant � une photo
 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
 *
 */
public class FenetreJeux extends JFrame{
	private PhotoEvent photoAleatoire;
	private AlbumPhotoEvent albumAleatoire= new AlbumPhotoEvent("");
	private JComboBox reponse;
	private String[] abEvent = {"mariage", "planetes"};
	private static JFrame frame;
	private ArrayList<PhotoEvent> abPhoto = new ArrayList<PhotoEvent>();
	private JLabel image;
	
	/**
	 * Construit une instance de FenetreJeux
	 * @throws PhotoNotFoundException Si la photo n'existe pas
	 * @throws UnhandledFormatException Si la photo �v�nement a le mauvais format
	 * @throws WrongFileException Si la photo ne se trouve pas au bon endroit
	 * @throws WrongEventException Si l'�v�nement ne correspond pas � l'album �v�nement
	 */
	public FenetreJeux() throws PhotoNotFoundException, UnhandledFormatException, WrongFileException, WrongEventException {
		super("Trouver l'évenement");
		photoAleatoire = choixPhotoHasard(choixAlbumHasard());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.initComposants();
		this.setBounds(20,20,500,500);
		this.setVisible(true);
	}
	
	public PhotoEvent getPhotoAleatoire() {
		return this.photoAleatoire;
	}
	
	public AlbumPhotoEvent getAlbumAleatoire() {
		return this.albumAleatoire;
	}
	
	/**
	 * Permet d'initialiser des composants dans l'instance FenetreJeux
	 * @param pe La photo évenement choise pr�alablement au hasard
	 */
	public void initComposants() {
		this.add(new JLabel("Trouver l'évenement correspondant à cette photo :", SwingConstants.CENTER), BorderLayout.NORTH);
		this.image = new JLabel((new ImageIcon(this.photoAleatoire.getPath())));
		this.add(this.image, BorderLayout.CENTER);
		JPanel pSud = this.creePanelSud();
		this.add(pSud, BorderLayout.SOUTH);	
		
	}
	
	/**
	 * Permet de créer un panel qui se positionnera au Sud dans l'instance de FenetreJeux
	 * @return Le panel cr�e
	 */
	public JPanel creePanelSud() {

		JPanel pSud = new JPanel();
		
		JLabel lab1 = new JLabel("Votre réponse : ");
		pSud.add(lab1);
		
		reponse = new JComboBox(abEvent);
		reponse.setPreferredSize(new Dimension(100, 20));
		reponse.setBackground(Color.LIGHT_GRAY);
		reponse.setForeground(Color.RED);
		pSud.add(reponse);

		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(100, 20));
		valider.setBackground(Color.LIGHT_GRAY);
		valider.setForeground(Color.RED);
		pSud.add(valider);
		
		valider.addActionListener(new resultatListener());
		
		return pSud;
	}
	
	/**
	 * Choisit un album évenement aléatoirement dans le tableau static abEvent
	 * @return L'album choisit aléatoirement
	 */
	public String choixAlbumHasard() {
		String choix;
		choix = abEvent[(int)(abEvent.length*Math.random())];
		return choix;
	}
	
	/**
	 * Choisie une photo au hasard parmis les photos de l'album évenement
	 * @param nom 	le nom de l'évenement où la photo sera choisie
	 * @return la photo choisie au hasard
	 * @throws PhotoNotFoundException Si la photo n'existe pas
	 * @throws UnhandledFormatException Si la photo �v�nement a le mauvais format
	 * @throws WrongFileException Si la photo ne se trouve pas au bon endroit
	 * @throws WrongEventException Si l'�v�nement ne correspond pas � l'album �v�nement
	 */
	public PhotoEvent choixPhotoHasard(String nom) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException, WrongEventException {
		Event event = new Event(nom);
		switch (nom){
		case "mariage":
			this.albumAleatoire.setNom(nom);
			this.abPhoto.clear();
			this.abPhoto.add(new PhotoEvent("images/mariage/bagues.jpg", event));
			this.abPhoto.add(new PhotoEvent("images/mariage/robe.jpg", event));
			this.abPhoto.add(new PhotoEvent("images/mariage/gateau.jpg", event));
			this.abPhoto.add(new PhotoEvent("images/mariage/fete.jpg", event));
			this.albumAleatoire.setAlbum(this.abPhoto);
			return (PhotoEvent)this.albumAleatoire.getPhotoAt((int)(this.albumAleatoire.getTaille()*Math.random())); 

			
		case "planetes":
			this.albumAleatoire.setNom(nom);
			this.abPhoto.clear();
			this.abPhoto.add(new PhotoEvent("images/planetes/mercure.gif", event));
			this.abPhoto.add(new PhotoEvent("images/planetes/venus.gif", event));
			this.abPhoto.add(new PhotoEvent("images/planetes/terre.gif", event));
			this.abPhoto.add(new PhotoEvent("images/planetes/mars.gif", event));
			this.abPhoto.add(new PhotoEvent("images/planetes/jupiter.gif", event));
			this.albumAleatoire.setAlbum(this.abPhoto);
			return (PhotoEvent)this.albumAleatoire.getPhotoAt((int)(this.albumAleatoire.getTaille()*Math.random()));

		default : return (new PhotoEvent("images/planetes/terre.gif", event));
		}
	}
	
	/**
	 * V�rifie si la reponse choisie correspond � l'�v�nement de la photo
	 * @return true si les deux correspondent, et false sinon
	 */
	public boolean verificationChoix() {
		if(reponse.getSelectedItem().equals(photoAleatoire.getEvent().getNomEvent()))
			return true;
		else return false;
	}
	
	/**
	 * Cr��e une fen�tre affichant le r�sulat avec possibilit� de r�essayer ou de quitter
	 * @param b true signifie que l'utilisateur a gagn�, et false signifie qu'il a perdu
	 */
	public void fenetreResultat(boolean b) {
		
			this.frame = new JFrame("R�sultat");
			this.frame.setLocationRelativeTo(null);
			this.frame.setSize(200, 200);
			this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.frame.setVisible(true);
			
			JPanel pSud = new JPanel();
			JButton reessayer = new JButton("R�essayer");
			reessayer.setBackground(Color.LIGHT_GRAY);
			reessayer.setForeground(Color.RED);
			
			JButton quitter = new JButton("Quitter");
			quitter.setBackground(Color.LIGHT_GRAY);
			quitter.setForeground(Color.RED);
			
			pSud.add(reessayer);
			pSud.add(quitter);
			
			this.frame.add(pSud, BorderLayout.SOUTH);
			
			reessayer.addActionListener(new TryQuitListener("reessayer"));
			quitter.addActionListener(new TryQuitListener("quitter"));
			
		if (b) {
			this.frame.add(new JLabel("Vous avez Gagn�", SwingConstants.CENTER), BorderLayout.CENTER);
		}
		else {
			this.frame.add(new JLabel("Vous avez Perdu", SwingConstants.CENTER), BorderLayout.CENTER);
		}
	}
	
	/**
	 * Met � jour la fen�tre
	 * @throws PhotoNotFoundException Si la photo n'existe pas
	 * @throws UnhandledFormatException Si la photo �v�nement a le mauvais format
	 * @throws WrongFileException Si la photo ne se trouve pas au bon endroit
	 * @throws WrongEventException Si l'�v�nement ne correspond pas � l'album �v�nement
	 */
	public void miseAJour() throws PhotoNotFoundException, UnhandledFormatException, WrongFileException, WrongEventException {
		photoAleatoire = choixPhotoHasard(choixAlbumHasard());
		ImageIcon newphoto = new ImageIcon(this.photoAleatoire.getPath()); 
		this.image.setIcon(newphoto);
	}

	/**
	 * Inner class de gestion du Listenner resulatListener
	 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
	 *
	 */
	class resultatListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(verificationChoix())
	    		fenetreResultat(true);
			else fenetreResultat(false);
		}
	}
	
	/**
	 * Inner Class de gestion du Listenner TryQuitListener
	 * @author Younes Chefou; Haseeb Javaid; Thomas Blanco; Mathieu Jugi
	 *
	 */
	class TryQuitListener implements ActionListener {
		private String bouton;
		public TryQuitListener(String etat) {
			this.bouton = etat;
		}
		
		public void actionPerformed(ActionEvent e){
			FenetreJeux.frame.dispose();
			
			if (this.bouton.equals("reessayer")) {
				try {
					
					FenetreJeux.this.miseAJour();
					
				} catch (PhotoNotFoundException | UnhandledFormatException | WrongFileException
						| WrongEventException e1) {
					e1.printStackTrace();
				}
			}
			if (this.bouton.equals("quitter")) {
				FenetreJeux.this.dispose();
			}
		}
	}             
	  
	
	
	public static void main(String[] args) throws PhotoNotFoundException, UnhandledFormatException, WrongFileException, WrongEventException {
		new FenetreJeux();
		
	}
}
	

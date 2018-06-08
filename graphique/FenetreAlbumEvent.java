package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphique.FenetreAlbum.ChangeListener;
import graphique.FenetreAlbum.MenuListener;
import modele.AlbumControleur;
import modele.AlbumControleurEvent;
import modele.AlbumPhoto;
import modele.AlbumPhotoEvent;

public class FenetreAlbumEvent extends JFrame implements Observer{
	private static final int SUIV=1,PREC=0,SUPP=2,ADD=3;
	
	private AlbumPhotoEvent al;
	private int positionAlbum;
	private AlbumControleurEvent controleur;
	private JButton suivButton;
	private JButton precButton;
	private LabelImage labImage;
	private JLabel nomPhotoLab;
	private JLabel AlbumIndexLab;
	
	public FenetreAlbumEvent(String titre,int x,int y,int w,int h,AlbumPhotoEvent alb) {
		super(titre);
		this.al=alb;
		this.positionAlbum=0;
		alb.addObserver(this);
		this.controleur = new AlbumControleurEvent(alb);
		System.out.println("J'observe l'album photo : "+ alb.toString());	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(x,y,w,h);
		this.initComposants();
		this.setVisible(true);
	}
	
	private void initComposants() {
		this.initMenu();
		JPanel pSud = this.creePanelSud(1,this.al.getTaille());
		this.add(pSud,BorderLayout.SOUTH);
		String img = this.al.getPhotoAt(0).getNom();
		this.labImage = new LabelImage();
		this.labImage.setImage("images/"+img);
		this.add(this.labImage, BorderLayout.CENTER);
		this.nomPhotoLab=new JLabel(img,SwingConstants.CENTER);
		this.add(this.nomPhotoLab,BorderLayout.NORTH);
		
			
	}
	
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		JMenu set = new JMenu("PhotoSettings");
		jmb.add(set);
		JMenuItem ajouter = new JMenuItem("ajouter une photo");
		JMenuItem supprimer = new JMenuItem("supprimer une photo");
		ajouter.addActionListener(new MenuListener(ADD));
		supprimer.addActionListener(new MenuListener(SUPP));
		set.add(ajouter);
		set.add(supprimer);
	}
	
	private JPanel creePanelSud(int pos,int taille) {
		JPanel pSud = new JPanel();
		this.precButton = new JButton("preccedent");
		this.suivButton = new JButton("suivant");
		this.suivButton.addActionListener(new ChangeListener(SUIV));
		this.precButton.addActionListener(new ChangeListener(PREC));
		this.precButton.setEnabled(false);
		pSud.add(this.precButton);
		this.AlbumIndexLab=new JLabel(pos+"/"+(taille));
		pSud.add(this.AlbumIndexLab);
		pSud.add(this.suivButton);
		return pSud;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public File[] chooseFiles(){
		File[] files = null;
		String nomEvent = this.al.getEvent().getNomEvent();
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
	
	public void miseAjour() {
		if(this.al.getTaille()==0) {
			this.AlbumIndexLab.setText((this.positionAlbum+1)+"/"+(this.al.getTaille()));
			this.labImage.setImage("");
			this.nomPhotoLab.setText("Aucune photo dans l'album photo");
		}
		else {
				this.AlbumIndexLab.setText((this.positionAlbum+1)+"/"+(this.al.getTaille()));
				String img = this.al.getPhotoAt(this.positionAlbum).getNom();
				this.labImage.setImage("images/"+img);
				this.nomPhotoLab.setText(this.al.getPhotoAt(this.positionAlbum).getNom());
		}
	}
	
	public void activeBoutons() {
		if(this.positionAlbum>=this.al.getTaille()-1) {
			this.suivButton.setEnabled(false);
		}
		else {
			this.suivButton.setEnabled(true);
		}
		if(this.positionAlbum<=0) {
			this.precButton.setEnabled(false);
		}
		else {
			this.precButton.setEnabled(true);
		}
	}
	
	class ChangeListener implements ActionListener {
		private int val;
		
		public ChangeListener(int i){
			this.val=i;
		}
		
		public ChangeListener(){
		}
		
		public void actionPerformed(ActionEvent e) {
			switch(val) {
			case SUIV :
				FenetreAlbumEvent.this.positionAlbum++;
				FenetreAlbumEvent.this.activeBoutons();
				FenetreAlbumEvent.this.precButton.setEnabled(true);
				break;
			case PREC :
				FenetreAlbumEvent.this.positionAlbum=FenetreAlbumEvent.this.positionAlbum-1;
				FenetreAlbumEvent.this.activeBoutons();
				FenetreAlbumEvent.this.suivButton.setEnabled(true);
				break;
			}
			FenetreAlbumEvent.this.miseAjour();
			
		}
	}
	
	class MenuListener implements ActionListener {
		private int val;
		
		public MenuListener(int i) {
			this.val=i;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch(val) {
			case ADD :
				try {
					File[]files = FenetreAlbumEvent.this.chooseFiles();
					FenetreAlbumEvent.this.controleur.modifieAlbumAjoute(files);
					FenetreAlbumEvent.this.activeBoutons();
				}
				finally {
					break;
				}
			case SUPP :
				if(FenetreAlbumEvent.this.positionAlbum>=FenetreAlbumEvent.this.al.getTaille()-1) {
					FenetreAlbumEvent.this.positionAlbum=FenetreAlbumEvent.this.positionAlbum-1;
					FenetreAlbumEvent.this.controleur.modifieAlbumDel(FenetreAlbumEvent.this.al.getPhotoAt(FenetreAlbumEvent.this.positionAlbum+1));
				}

				else {
					FenetreAlbumEvent.this.controleur.modifieAlbumDel(FenetreAlbumEvent.this.al.getPhotoAt(FenetreAlbumEvent.this.positionAlbum));
				}

				
				break;	
			}
		}
	}

}

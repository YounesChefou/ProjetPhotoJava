package graphique;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;

import modele.*;

public class TestGraph {
	public static void main(String[] args){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		/* Pour tester avec un AlbumPhoto */
		AlbumPhoto a = new AlbumPhoto("Steroids");
		a.charge("Steroids.txt");
		new FenetreAlbum(0,0,dim.width/2,dim.height,a);
		new FenetreTexteAlbum(dim.width/2,0,dim.width/2,dim.height,a);
//		
		/* Pour tester avec un AlbumPhotoEvent */
//		AlbumPhotoEvent ab = new AlbumPhotoEvent("Spectacle");
//		ab.charge("mariage.txt");
//		new FenetreAlbumEvent(0,0,dim.width/2,dim.height,ab);
//		new FenetreTexteAlbumEvent(dim.width/2,0,dim.width/2,dim.height,ab);
	}
}

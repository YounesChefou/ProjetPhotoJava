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
		Event e = new Event("mariageToto");
//		new FenetreUneImage("Ayy",dim.width,dim.height,"images/Firefox_wallpaper.png");
		AlbumPhoto a = new AlbumPhoto("Youth");
		AlbumPhotoEvent ab = new AlbumPhotoEvent("45");
		a.charge("Steroids.txt");
		ab.charge("86.txt");
		System.out.println(a.getNom());
		System.out.println(ab.getNom());
//		try{
//		ab.ajouterPhoto(new PhotoEvent("images/mariageToto/bande.jpeg",e));
//		}
//		catch(Exception et){
//			System.out.println(et);
//		}
//		ab.charge("spectacle.txt");
//		System.out.println(ab);
		new FenetreAlbumEvent(0,0,dim.width/2,dim.height,ab);
//		new FenetreTexteAlbum(dim.width/2,0,dim.width/2,dim.height,ab);
//		new FenetreListeAlbum((2*dim.width)/3,0,dim.width/3,dim.height,ab);
		
//		File f = new File("images/franku.jpg");
//		System.out.println(f.getPath());
	}
}

package graphique;

import java.awt.Dimension;
import java.awt.Toolkit;

import modele.AlbumPhoto;

public class TestGraph {
	public static void main(String[] args){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
//		new FenetreUneImage("Ayy",dim.width,dim.height,"images/Firefox_wallpaper.png");
		AlbumPhoto ab = new AlbumPhoto("Woop");
		ab.charge("Steroids.txt");
//		System.out.println(ab);
		new FenetreAlbum(0,0,dim.width,dim.height,ab);
//		new FenetreTexteAlbum(dim.width/2,0,dim.width/2,dim.height,ab);
//		new FenetreListeAlbum((2*dim.width)/3,0,dim.width/3,dim.height,ab);
	}
}

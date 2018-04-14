
import java.util.*;
import java.io.*;
import java.util.Date;

import java.text.*;

public class Test {
	public static void main(String[] args){
		Photo p = new Photo("images/Saturne.gif");
		System.out.println(p);
		AlbumPhoto album = new AlbumPhoto("Nation");
		album.ajouterPhoto(p);
		System.out.println(album);
		
		Personne per = new Personne("dubacq","dubacq@orange.fr");
		Event e = new Event("Mariage");
		e.ajouterPersonne(per);
		AlbumPhotoEvent albumE = new AlbumPhotoEvent(e);
		PhotoEvent pE = new PhotoEvent("mariageToto/bouquet.jpeg",e);
		albumE.ajouterPhoto(pE);
		System.out.println(albumE);
	}
}
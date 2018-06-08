package modele;

import java.util.*;
import java.io.*;
import java.text.*;
import graphique.*;
import exception.*;

public class Test{
	public static void main(String[] args)throws 
	 PhotoNotFoundException, UnhandledFormatException, 
	 WrongFileException{
		AlbumPhotoEvent ab = new AlbumPhotoEvent("spectacle");
		ab.charge("spectacle.txt");
		System.out.println(ab);
		Event e = new Event("mariageToto");
		PhotoEvent p = null;
		System.out.println(ab.getEvent());
		try{
			p = new PhotoEvent("images/mariageToto/bande.jpeg",e);
			System.out.println(p);
		}
		catch(WrongFileException ex){
			System.out.println(ex);
		}
		catch(PhotoNotFoundException exce){
			System.out.println(exce);
		}
		catch(UnhandledFormatException exc){
			System.out.println(exc);
		}
		}
}
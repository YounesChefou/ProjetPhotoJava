
import java.util.*;
import java.io.*;
import java.text.*;

public class Test{
	public static void main(String[] args){
		AlbumPhotoEvent ab = new AlbumPhotoEvent("spectacle");
		ab.charge("spectacle.txt");
		System.out.println(ab);
		}
}
package graphique;
import java.awt.*;

import javax.swing.*;


public class LabelImage extends JLabel{
	private Image im;
	private String nomIm;

	public LabelImage(String nomImage)  {
		super("", SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(nomImage);
		this.im = icon.getImage();
		this.nomIm = nomImage;
//		this.setIcon(icon);
	}

	public void setImage(String nouvImage){
		ImageIcon icon = new ImageIcon(nouvImage);
		this.im = icon.getImage();
		this.nomIm = nouvImage;
//		this.setIcon(icon);
	}
	
	/**
	 * Renvoie le quotient de la largeur par la hauteur.
	 * @param largeur : la largeur de l'image
	 * @param hauteur : la hauteur de l'image
	 * @return quotient
	 */
	public double quotientEchelle(double largeur, double hauteur){
		double quotient = (double) largeur/hauteur;
		return quotient;
	}
	
	/**
	 * Renvoie la largeur de l'image affichée dans la fenêtre
	 * @return icon.getIconWidth, largeur
	 */
	public int getWidthImage(){
		ImageIcon icon = new ImageIcon(this.nomIm);
		return icon.getIconWidth();
	}
	
	/**
	 * Renvoie la hauteur de l'image affichée dans la fenêtre
	 * @return icon.getIconHeight, hauteur
	 */
	public int getHeightImage(){
		ImageIcon icon = new ImageIcon(this.nomIm);
		return icon.getIconHeight();
	}
	
	/**
	 * Affiche l'image dans la fenêtre.
	 * @param g
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double hauteurImage = this.getHeightImage();
		double largeurImage = this.getWidthImage();
		double quotient = this.quotientEchelle(largeurImage, hauteurImage);
		int hauteurLabel = this.getHeight();
		int largeurLabel = this.getWidth();
		if(largeurLabel < largeurImage){
			largeurImage = largeurLabel;
			hauteurImage = largeurImage/quotient;
		}
		else if(hauteurLabel < hauteurImage){
			hauteurImage = hauteurLabel;
			largeurImage = hauteurImage*quotient;
		}
		int X = (int) ((int)largeurLabel/2-largeurImage/2);
		int Y = (int)((int)hauteurLabel/2-hauteurImage/2);
		g.drawImage(this.im,X,Y,(int)largeurImage,(int)hauteurImage, this);
		}
}

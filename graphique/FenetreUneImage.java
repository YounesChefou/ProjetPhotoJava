package graphique;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FenetreUneImage extends JFrame{
	
	public FenetreUneImage(String titre, int w, int h, String nomImage){
		super(titre);
		this.add(new JLabel(new ImageIcon(nomImage), SwingConstants.CENTER), BorderLayout.CENTER);
		this.add(new JLabel(nomImage, SwingConstants.CENTER), BorderLayout.NORTH);
		this.setSize(w,h);
		this.setVisible(true);
	}
}

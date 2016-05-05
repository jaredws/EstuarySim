package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 
 * @author Jared Sharpe
 *
 */


public class ResearchView extends ViewTemplate{
	
	private List<Image> images;
	Dimension screenSize;
	
	/**
	 * Buffer the images we will need to move BlueCrabs around the screen.
	 */
	//may consider making this private and using a method to only allow one instnace
	public ResearchView(){
		images = new ArrayList<Image>();
		Image image;
		String[] names = {"CrabResearch", "PhragmitesResearch", "BlueCrabResearch", "TurtleResearch", "CordGrassResearch"};
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		for(String fileName: names){
		try {           
			image = ImageIO.read(new File("./img/"+fileName+".png"));
			images.add(image);
	       } catch (IOException ex) {
	    	   System.out.println("BlueCrab Image read error");
	       }
		}
		
	}
	/**
	 * 
	 * @param i
	 * @return The buffered image of the BlueCrab
	 * 0-Front, 1-back, 2-left, 3-right
	 */
	public Image getImage(int i){
		
			return(images.get(i));
	}
}
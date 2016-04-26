package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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

public class TurtleView extends ViewTemplate{
	
	private List<Image> images;
	
	/**
	 * Buffer the images we will need to move crabs around the screen.
	 */
	//may consider making this private and using a method to only allow one instnace
	public TurtleView(){
		images = new ArrayList<Image>();
		Image image;
		String[] names = {"Turtle"};
		for(String fileName: names){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		try {                
	         image = ImageIO.read(new File("./img/"+fileName+".png"));
	         images.add(image.getScaledInstance((int)screenSize.getWidth()/10, -1,1));
	       } catch (IOException ex) {
	    	   System.out.println("Turtle Image read error");
	       }
		}
		
	}
	/**
	 * 
	 * @param i
	 * @return The buffered image of the crab
	 * 0-Front, 1-back, 2-left, 3-right
	 */
	public Image getImage(int i){
		//Must remove %4 will be changed when calling it from above with motion idicator.
		return(images.get(0));
	}

}
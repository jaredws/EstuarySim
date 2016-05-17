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
 * @author Adam K.
 *
 */


public class PollutionView extends ViewTemplate{
	
	private List<Image> images;
	Dimension screenSize;
	/**
	 * Buffer the images we will need to move pollution around the screen.
	 */
	//may consider making this private and using a method to only allow one instance
	public PollutionView(){
		images = new ArrayList<Image>();
		Image image;
		String[] names = {"pollution1","pollution2","pollution3"};
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		for(String fileName: names){
		try {                
			image = ImageIO.read(new File("./img/"+fileName+".png"));              
			images.add(image.getScaledInstance((int)screenSize.getWidth()/20, -1,Image.SCALE_SMOOTH));
	       } catch (IOException ex) {
	    	   System.out.println("Pollution Image read error");
	       }
		}
	}
	

	
	
	/**
	 * 
	 * @param i - the type of the trash (held in the trash model)
	 * @return The buffered image of the pollution
	 */
	public Image getImage(int i){
		//
			return(images.get(i)); //bag of chips
		}
	}

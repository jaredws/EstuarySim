package model;
/**
 * 
 * @author Steven
 *
 */
public class CordGrass extends Grabbable {
	//With this outline, there's no difference between a crab and anyhting else. 
	//We'll have to change the name of this class to Interactable Object or something
	//Add a name and just name the different items
	
	private int x;
	private int y;
	//The sizes may need to be a ratio to the screen size. -JS
	//temporary public variables
	public int sizeX;
	public int sizeY;
	
	public CordGrass(int x, int y){
		this.x = x;
		this.y = y;
		this.sizeX = 165;
		this.sizeY = 165;
    }
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSizeX() {
		return this.sizeX;
	}
	
	public int getSizeY() {
		return this.sizeY;
	}
	
	public void setSizeY(int y) {
		this.sizeY = y;
	}
	
	public void setSizeX(int x) {
		this.sizeX = x;
	}
}
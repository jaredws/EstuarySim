package model;

/**
 * The Class Background.
 * Stores the size of the background and handles estuary health checks
 * @author Team 0
 */
public class Background {
	
	/** The size x. */
	private int sizeX;
	
	/** The size y. */
	private int sizeY;
	
	/**
	 * Instantiates a new background.
	 *
	 * @param x the width
	 * @param y the height
	 */
	public Background(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;
	}
	
	/**
	 * Check.
	 *
	 * @param health the given health of the estuary
	 * @return the int representing the health rank of the estuary
	 */
	public int check(int health){
		if(health < 15){
			return 2;
		}else if(health < 30){
			return 1;
		}else
			return 0;
		
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return sizeX;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return sizeY;
	}
}

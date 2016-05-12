package model;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * @author Steven
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CordGrassHandler {
	Random rand;
	List<CordGrass> CordGrass;
	Dimension screenSize;
	private boolean Researched;
	int Removed;

	public void addCordGrass(int x, int y){
		CordGrass.add(new CordGrass(x,y,screenSize));
	}
	
	public List<CordGrass> getCordGrass() {
		return this.CordGrass;
	}
	
	public CordGrassHandler(){
		rand  = new Random();
		CordGrass = new ArrayList<CordGrass>();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setResearched(false);
		Removed = 0;
	}
	
	public boolean getResearched() {
		return this.isResearched();
	}
	
	
	
	/*public void deleteCordGrass(ToolControl tc){
		for(int i = 0; i < CordGrass.size(); i++){
			if(CordGrass.get(i).getX() > tc.getTools().get(0).getX() - tc.getTools().get(0).getSizeX()/2 && CordGrass.get(i).getX() < tc.getTools().get(0).getX() + tc.getTools().get(0).getSizeX()/4){
				if((CordGrass.get(i).getY() > tc.getTools().get(0).getY()-tc.getTools().get(0).getSizeY()) && (CordGrass.get(i).getY() < tc.getTools().get(0).getY() + tc.getTools().get(0).getSizeY()/2)){
//					CordGrass.remove(i);
//					i--;
				}
			}
		}
	}*/
	
	public void removeCordGrass(int i){
		CordGrass.remove(i);
		Removed++;
	}
	
	public CordGrass getCordGrass(int i){
		return CordGrass.get(i);
	}

	public void age(){
		for(int i = 0; i < CordGrass.size(); i++){
			CordGrass.get(i).live();
		}
	}
	
	public int getRemoved(){
		return Removed;
	}

	public boolean isResearched() {
		return Researched;
	}

	public void setResearched(boolean researched) {
		Researched = researched;
	}

}
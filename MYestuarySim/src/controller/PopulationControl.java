package controller;

import java.util.Random;

public class PopulationControl {
	
	/*
	 * The total population of plants to be dependent on the number of animals
	 * The total population of animals is limited by the number of plants both upper and lower bounds
	 * TotalPlant = NonInvasive + 0.8*Invasive//Invasive plants take up 'less room' so more can be there
	 * TotalAnimal = (-1/36)TotalPlant^2 + (35/18)*TotalPlant - 325/36 // used mathematica to solve a system so at 35 plants we peak at 25 animals
	 * 		the numbers can and probably will be adjusted so the screen isn't over crowded, remmber this compounds as animals decrease effective 'total plant'
	 * TotalAnimal = NonInvasive + 1.4*Invasive //this is the identity for the division of the Animals
	 * 
	 * I also want it to be more likely to spawn an Invasive species. This can be population dependent if we wish.
	 */
	
	int NIP; //current noninvasive plant
	int IP; //current invasive plant
	int NIA;//current noninvasive animal
	int IA; //current invasive animal
	int TP; //current total plants
	int TA; //current total animals
	
	private int TotalPlant;
	private int TotalAnimal;
	private int NonInvasivePlant;
	private int InvasivePlant;
	private int NonInvasiveAnimal;
	private int InvasiveAnimal;
	private Game game;
	
	public void calculate(){
		NIP = game.getCordGrassControl().getCordGrass().size(); //calculate current noninvasive plant
		IP = game.getPhragmitesControl().getPhragmites().size(); //calculate current invasive plant
		NIA = game.getTurtleControl().getTurtles().size() + game.getBlueCrabControl().getBlueCrabs().size();//calculate current noninvasive animal
		IA = game.getCrabControl().getCrabs().size(); //calculate current invasive animal
		TP = NIP + IP;
		TA = NIA + IA;
		NonInvasivePlant = NIP;
		InvasivePlant = IP;
		NonInvasiveAnimal = NIA;
		TotalAnimal = TA;
		TotalPlant = (int) (2*NonInvasivePlant + 1.5*InvasivePlant - TotalAnimal);
		TotalAnimal = (int) ((-1/36)*TotalPlant*TotalPlant + (35/18)*TotalPlant - 325/36);
		InvasiveAnimal = (int) ((TotalAnimal - NonInvasiveAnimal)/1.4);//some of these lines may not be necessary
		NonInvasiveAnimal = (int) TotalAnimal - InvasiveAnimal;//all are included for my train of thought -JS
		spawn();
		
	}
	
	public void update(Game g){
		game = g;
		calculate();
	}
	
	private void spawn(){//call calculate and spawn at appropriate intervals
		Random rand = new Random();
		if(TotalPlant>TP){
			if(rand.nextInt(8)%3==0){//3/8 probability of spawning non invasive
				game.getCordGrassControl().addCordGrass(rand.nextInt(1350),rand.nextInt(300)+500);
			}
			else{
				game.getPhragmitesControl().addPhragmites(rand.nextInt(1350),rand.nextInt(300)+500);
				}
		}
		if(TotalPlant<TP){
			if(rand.nextInt(2)%2==0){
				game.getCordGrassControl().removeCordGrass(0);
			}
			else{
				game.getPhragmitesControl().removePhragmites(0);
			}
		}
		if(TotalAnimal>TA){
			if(rand.nextInt(8)%3==0){//3/8 probability of spawning non invasive
				if(rand.nextInt(8)%2==0){//3/8ths probability of adding a turtle
					game.getTurtleControl().addTurtle(rand.nextInt(1350),rand.nextInt(300)+500);
				}
				else{//add a non-invasive
					game.getBlueCrabControl().addBlueCrab(rand.nextInt(1350),rand.nextInt(300)+500);
				}
			}
			else{
				game.getCrabControl().addCrab(rand.nextInt(1350),rand.nextInt(300)+500);				}
		}
		if(TotalAnimal<TA){
			if(rand.nextInt(2)%2==0){
				if(rand.nextInt(8)%2==0){//3/8ths probability of removing a turtle
					game.getTurtleControl().removeTurtle(0);
				}
				else{//remove a non-invasive
					game.getBlueCrabControl().removeBlueCrab(0);
				}
			}
			else{//remove an invasive
				game.getCrabControl().removeCrab(0);
			}
		}
	}

}
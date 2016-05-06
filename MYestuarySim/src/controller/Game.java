package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import view.EndingView;
import view.StartingView;
import view.TotalView;

public class Game {
	Random rand = new Random();
	static Random r = new Random();
	static StartingView SV;
	static CrabControl CC;
	static TurtleControl TC;
	static BlueCrabControl BCC;
	static ButtonControl BC;
	static TotalView TV;
	static PopulationControl PopC;
	static CordGrassControl CGC;
	static PhragmitesControl PC;
	static HealthControl HC;
	static PollutionControl PolC;
	static EndingView EV;
	static EndScreenControl ESC;
	static CountdownControl CDC;
	static int countDown = 500;
	static int threeSec = 0;
	static int sec = 0;
	static Timer timer;
	
	public static void main(String[] args){
		Game G = new Game();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		StartScreenControl s = new StartScreenControl();
		SV = new StartingView(s);
		SV.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		while(SV.Showing){
			SV.checkStart();
			SV.repaint();
			try {
    			Thread.sleep(50);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
		ScreenControl S = new ScreenControl();
		TV = new TotalView(S);
		CC = new CrabControl();
		BC = new ButtonControl((int)screenSize.getHeight(),(int)screenSize.getWidth());
		TC = new TurtleControl();
		BCC = new BlueCrabControl();
		PopC = new PopulationControl();
		PC = new PhragmitesControl();
		CGC = new CordGrassControl();
		HC = new HealthControl();
		PolC = new PollutionControl();
		CDC = new CountdownControl();
		TV.update(G);
		TV.setSize((int) screenSize.getWidth(), (int)screenSize.getHeight());
		SV.dispose(); 
		for(int i = 0; i<3; i++){
			PC.addPhragmites(300+100*i,200);
			CGC.addCordGrass(200,300-100*i);
		}
		CC.addCrab(200,200);
		CC.addCrab(200,300);
		BCC.addBlueCrab(200, 100);
		/**
		 * Create a timer for updating the population.
		 * Since the population will naturally correct itself, we want to delay that to allow the player
		 * to mess with the estuary and see the effects.
		 */
		int t = 50;
		ActionListener taskPerformer = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	System.out.println("THREE " + threeSec);
		    	if(sec/t == 20) {
		    		CDC.updateCountdown();
		    		sec = 0;
		    	}
		    	if(threeSec/t == 60 && !S.pause) {
		    		  PopC.update(G);
		    		  threeSec=0;
		    		  
		    	}
		    	if(!S.pause){
					CC.moveCrabs();
					TC.moveTurtles();
					BCC.moveBlueCrabs();
					PC.age();
					CGC.age();
					sec+=t;
				    threeSec+=t;
				}
		    	
		    	
		    	if(countDown <= 0) timer.stop();
		    }
        };
	    timer = new Timer(t, taskPerformer);
	    timer.start();
	    TV.repaint();
		while(true){
			S.checkPos(CC,TC,BCC,CGC,PC,BC);
			TV.update(G);
			TV.repaint();
			if(CDC.getTime() == 0) break;
			if(S.pause) continue;
			CC.deleteCrabs(BC);
			TC.deleteTurtles(BC);
			BCC.deleteBlueCrabs(BC);
			try {
    			Thread.sleep(10);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
		
		EndScreenControl esc = new EndScreenControl();
		EV = new EndingView(esc);
		EV.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		TV.dispose();
		while(EV.Showing){
			EV.repaint();
			EV.add(EV.getScoreLabel());
			try {
    			Thread.sleep(50);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
		
		
		
	}

	public Random getRand() {
		return rand;
	}

	public static CrabControl getCrabControl() {
		return CC;
	}

	public static ButtonControl getButtonControl() {
		return BC;
	}

	public static TotalView getTotalView() {
		return TV;
	}
	
	public static TurtleControl getTurtleControl() {
		return TC;
	}
	
	public static BlueCrabControl getBlueCrabControl() {
		return BCC;
	}
	
	public static PhragmitesControl getPhragmitesControl(){
		return PC;
	}
	
	public static CordGrassControl getCordGrassControl(){
		return CGC;
	}
	
	public static HealthControl getHealthControl(){
		return HC;
	}
	
	public static PollutionControl getPollutionControl(){
		return PolC;
	}
	
	public static CountdownControl getCountdownControl(){
		return CDC;
	}
	
	public int calculateHealth(){
		int c,bc,t,p,cg;
		//Crabs are worth double plants right now
		c = 2*CC.getCrabs().size();
		bc = 2*BCC.getBlueCrabs().size();
		t = TC.getTurtles().size();
		p = PC.getPhragmites().size();
		cg = CGC.getCordGrass().size();
		return (bc+t+cg-p-c);
	}
}


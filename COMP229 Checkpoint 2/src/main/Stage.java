package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import behaviours.Chase;
import behaviours.Passive;
import behaviours.Patrol;
import behaviours.Random;
import onscreen.*;

public class Stage extends    javax.swing.JPanel 
implements MouseListener,
MouseMotionListener {
	private static Stage instance = new Stage();

	public Grid grid;
	public onscreen.Character sheep;
	public onscreen.Character wolf;
	public onscreen.Character shepherd;
	public boolean readyToStep;
	public boolean sheepCaught = false;
	public boolean shepherdPressed= false;
	public int gamesize=20;

	Point lastMouseLoc = new Point(0, 0);
	Cell mouseWasIn = null;

	List<MouseObserver> observers = new ArrayList<MouseObserver>();

	public Stage() {

		readyToStep = false;
		grid = new Grid();

		for(Cell c : grid) registerMouseObserver(c);

		//Debugging lines
		shepherd = new Shepherd(grid.getCell(0, 2), new Passive());
		sheep    = new Sheep(grid.getCell(0, 1), new Random());
		wolf     = new Wolf(grid.getCell(3, 15), new Patrol());

		//Setup game
		//shepherd = new Shepherd(grid.giveMeRandomCell(), new Passive());
		//sheep    = new Sheep(grid.giveMeRandomCell(), new Random());
		//wolf     = new Wolf(grid.giveMeRandomCell(), new Patrol());

		registerMouseObserver(shepherd);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	//Singleton pattern
	public static Stage getInstance() {
		return instance;
	}

	//Mechanics and rules
	public void paint(Graphics g) {
		draw(g);
	}
	public void draw(Graphics g) {
		grid.draw(g);
		sheep.draw(g);
		wolf.draw(g);
		shepherd.draw(g);
		if (result()){
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString("Game Over!", 200,200);
		}
	}
	public void step() {
		sheep.act();
		wolf.act();
		if(shepherd.getLocation()==sheep.getLocation()){
			shepherd = new CarriedCharacter(shepherd);
		}
		readyToStep = false;
	}
	public boolean result(){
		if (shepherd.getLocation().equals(wolf.getLocation())){
			return true;
		}else if (wolf.getLocation().equals(sheep.getLocation())){
			return true;
		} else {
			return false;
		}
	}
	
	//Observer pattern
	public void registerMouseObserver(MouseObserver mo) {
		observers.add(mo);
	}

	//Grid navigation
	public Cell oneCellCloserTo(Cell from, Cell to) {
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
	}
	public Cell returnRandomAdjacent(Cell currentLoc) {
		int randx = ((int) (Math.random()*3))-1;
		int randy = ((int) (Math.random()*3))-1;
		if(((currentLoc.x+randx)>gamesize)||((currentLoc.x+randx)<0)){
			randx=0;
		}
		if(((currentLoc.y+randy)>gamesize)||((currentLoc.y+randy)<0)){
			randy=0;
		}
		return grid.getCell(currentLoc.x+randx, currentLoc.y+randy);
	}
	public Cell returnHorizontalAdjacent(Cell currentLoc, boolean right) {
		if(right){
			return grid.getCell(currentLoc.x+1,currentLoc.y);
		}else{
			return grid.getCell(currentLoc.x-1,currentLoc.y);
		}
	}

	// implementation of MouseListener and MouseMotionListener
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){
		if (shepherd.getCharPressed()){
			shepherdPressed=true;
		}
	}
	public void mouseReleased(MouseEvent e){
		System.out.println("did something"+shepherd.getCharPressed());
		if (shepherd.getCharPressed()||shepherd.getBounds().contains(e.getPoint())){
			shepherd.mouseClicked(e);
		}
	}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){
		for (MouseObserver mo : observers) {
			Rectangle bounds = mo.getBounds();
			if(bounds.contains(e.getPoint())) {
				mo.mouseEntered(e);
			} else if (bounds.contains(lastMouseLoc)) {
				mo.mouseLeft(e);
			}
		}
		lastMouseLoc = e.getPoint();
	}
}
package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import behaviours.Chase;
import behaviours.Passive;
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
	public int gamesize=20;

	Point lastMouseLoc = new Point(0, 0);
	Cell mouseWasIn = null;

	List<MouseObserver> observers = new ArrayList<MouseObserver>();

	public Stage() {
		readyToStep = false;
		grid = new Grid();

		for(Cell c : grid) registerMouseObserver(c);
		

		shepherd = new Shepherd(grid.getCell(0, 2), new Passive());
		sheep    = new Sheep(grid.getCell(0, 1), new Chase(shepherd));
		wolf     = new Wolf(grid.getCell(3, 15), new Chase(sheep));
		//shepherd = new Shepherd(grid.giveMeRandomCell(), new Passive());
		//sheep    = new Sheep(grid.giveMeRandomCell(), new Chase(shepherd));
		//wolf     = new Wolf(grid.giveMeRandomCell(), new Chase(sheep));
		
		registerMouseObserver(shepherd);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public static Stage getInstance() {
		return instance;
	}

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
		readyToStep = false;
		if(shepherd.getLocation()==sheep.getLocation()){
			shepherd = new CarriedCharacter(shepherd);
		}
	}

	public void registerMouseObserver(MouseObserver mo) {
		observers.add(mo);
	}

	public Cell oneCellCloserTo(Cell from, Cell to) {
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
	}
	
	// implementation of MouseListener and MouseMotionListener
	public void mouseClicked(MouseEvent e){
		if (shepherd.getBounds().contains(e.getPoint())){
		  shepherd.mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
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

  public boolean result(){
  	if (shepherd.getLocation().equals(wolf.getLocation())){
  		return true;
  	}else if (wolf.getLocation().equals(sheep.getLocation())){
  		return true;
  	} else {
  		return false;
  	}
  }
}
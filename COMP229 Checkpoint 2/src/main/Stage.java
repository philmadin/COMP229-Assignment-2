package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import onscreen.*;

public class Stage extends    javax.swing.JPanel 
                   implements MouseListener,
                              MouseMotionListener {
	public Grid grid;
	public onscreen.Character sheep;
	public onscreen.Character wolf;
	public onscreen.Character shepherd;
	public boolean readyToStep;
	public int gamesize=20;

	Point lastMouseLoc = new Point(0, 0);
	Cell mouseWasIn = null;

	List<MouseObserver> observers = new ArrayList<MouseObserver>();

	public Stage() {
		readyToStep = false;
		grid = new Grid();
		
		for(int i = 0; i < gamesize; i++)
			for(int j = 0; j < gamesize; j++)
				registerMouseObserver(grid.getCell(i,j));
		

		//shepherd = new Shepherd(this, grid.getCell(0, 2));
		//sheep    = new Sheep(this, grid.getCell(0, 1));
		wolf     = new Wolf(this, grid.getCell(3, 15));
		
		shepherd = new Shepherd(this, grid.giveMeRandomCell());
		sheep    = new Sheep(this, grid.giveMeRandomCell());
		//wolf     = new Wolf(this, grid.giveMeRandomCell());

		registerMouseObserver(shepherd);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		draw(g);
	}

	public void draw(Graphics g) {

		grid.draw(g);
		wolf.draw(g);
		shepherd.draw(g);
		sheep.draw(g);
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
			sheep = new CarriedCharacter(sheep);
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
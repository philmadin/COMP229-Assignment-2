package onscreen;

import behaviours.*;
import main.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Character implements MouseObserver {
	
	protected Cell location;
	protected Color myColour;
	protected Color mySelectedColour;
	protected Color myBorderColour;
	protected Behaviour behaviour;
	private boolean charPressed= false;
	
	public Character(){}
	public Character(Cell location, Color c, Color sc, Behaviour behaviour){
		this.location         = location;
		this.myColour         = c;
		this.mySelectedColour = sc;
		this.myBorderColour   = c;  // by default it is the same, it changes when the character is hightlighted
		this.behaviour        = behaviour;
	}

	public Cell getLocation(){
		return location;
	}
	
	public void setLocation(Cell loc){
		location = loc;
	}

	public void draw(Graphics g) {
		g.setColor(myColour);
		g.fillRect(location.getTopLeft().x,location.getTopLeft().y,35,35);
		g.setColor(myBorderColour);
		g.drawRect(location.getTopLeft().x,location.getTopLeft().y,35,35);
	}
	
	public String getActor(){
		return "Unknown!";
	}
	
	// The argument is the stage on which to act.  Every actor needs a stage
	public void act(){
		location = behaviour.execute(location);
	}

	
	//Mouse behaviour
	public void mouseLeft(MouseEvent e){
    	charPressed=false;
	}
	public void mouseEntered(MouseEvent e){
		myBorderColour = mySelectedColour;
    	charPressed=true;
	}
	
	
	
	public Rectangle getBounds(){
		return location.getBounds();
	}

	//All the getters
	public Color getMyColour(){
		return myColour;
	};
	public Color getMySelectedColour(){
		return mySelectedColour;
	};
	public Color getMyBorderColour(){
		return myBorderColour;
	};
	public Behaviour getBehaviour(){
		return behaviour;
	};
    public boolean getCharPressed(){
    	return charPressed;
    }

}
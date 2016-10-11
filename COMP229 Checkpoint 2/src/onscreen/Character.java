package onscreen;

import behaviours.*;
import main.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Character implements MouseObserver {
	
	protected Cell location;
	protected Stage stage;
	protected Color myColour;
	protected Color mySelectedColour;
	protected Color myBorderColour;
	protected Behaviour behaviour;
	
	public Character(){}
	public Character(Stage stage, Cell location, Color c, Color sc, Behaviour behaviour){
		this.stage            = stage;
		this.location         = location;
		this.myColour         = c;
		this.myBorderColour   = c;  // by default it is the same, it changes when the character is hightlighted
		this.mySelectedColour = sc;
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
		location = behaviour.execute(stage, location);
	}

	public void mouseLeft(MouseEvent e){
		myBorderColour = myColour;
	}

	public void mouseEntered(MouseEvent e){
		myBorderColour = mySelectedColour;
	}

	public void mouseClicked(MouseEvent e){
	}

	public Rectangle getBounds(){
		return location.getBounds();
	}

	public Stage getStage(){
		return stage;
	};
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

}
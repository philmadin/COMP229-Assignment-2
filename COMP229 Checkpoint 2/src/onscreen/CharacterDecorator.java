package onscreen;

import java.awt.*;
import java.awt.event.*;

import behaviours.Behaviour;
import main.*;

public abstract class CharacterDecorator extends Character {
	Character character;
	public CharacterDecorator(Character c){
		this.character = c;
		this.location         = c.getLocation();
		this.myColour         = c.getMyColour();
		this.myBorderColour   = c.getMyBorderColour();  // by default it is the same, it changes when the character is hightlighted
		this.mySelectedColour = c.getMySelectedColour();
		this.behaviour        = c.getBehaviour();
	}

	public void      act(){character.act();}
	public void      mouseLeft(MouseEvent e){character.mouseLeft(e);}
	public void      mouseEntered(MouseEvent e){character.mouseEntered(e);}
	public void      mouseClicked(MouseEvent e){character.mouseClicked(e);}
	public Rectangle getBounds(){return character.getBounds();}
	public Cell      getLocation(){return character.getLocation();}
	public void      setLocation(Cell loc){character.setLocation(loc);}


	public abstract void draw(Graphics g);

}
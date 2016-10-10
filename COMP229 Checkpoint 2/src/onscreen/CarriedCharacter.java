package onscreen;

import java.awt.Graphics;

public class CarriedCharacter extends CharacterDecorator{
	
	public CarriedCharacter(Character c) {
		super(c);
	}

    public void draw(Graphics g){
		g.setColor(new java.awt.Color(255,255,255));
    	g.fillRect(character.location.getTopLeft().x+5, character.location.getTopLeft().y+5, 25, 25);
    }

}

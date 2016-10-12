package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CarriedCharacter extends CharacterDecorator{
    public CarriedCharacter(Character c) {
        super(c);
    }

    @Override
    public void draw(Graphics g) {
        character.draw(g);
        Rectangle r = character.getBounds();
        g.setColor(Color.WHITE);
        g.fillOval(r.x + 5, r.y + 5, 25, 25);
    }

}

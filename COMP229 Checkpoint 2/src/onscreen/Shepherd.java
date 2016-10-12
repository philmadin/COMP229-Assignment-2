package onscreen;

import main.*;

import java.awt.*;
import java.awt.event.*;

import behaviours.Behaviour;

public class Shepherd extends Character {

    public Shepherd(Cell location, Behaviour behaviour){
		super(location, new java.awt.Color(0,255,0), new java.awt.Color(224, 224, 224), behaviour);
    }

    public void mouseClicked(MouseEvent e){
    	Stage.getInstance().shepherd = new HighlightedCharacter(Stage.getInstance().shepherd);
    }
}

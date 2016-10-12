package onscreen;

import java.awt.Graphics;

import behaviours.Behaviour;
import main.*;

public class Sheep extends Character {
	public Sheep(Cell location, Behaviour behaviour){
		super(location, new java.awt.Color(255,255,255), new java.awt.Color(224, 224, 224), behaviour);
	}
	public void act(){
		if(checkInSight()){
			this.behaviour=new behaviours.Chase(Stage.getInstance().shepherd);
		}
		location = behaviour.execute(location);
	}
	public boolean checkInSight(){
		if(Math.abs(Stage.getInstance().shepherd.location.x-this.location.x)<=5&&Math.abs(Stage.getInstance().shepherd.location.y-this.location.y)<=5){
			return true;
		}else{
			return false;
		}

	}

}
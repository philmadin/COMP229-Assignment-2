package onscreen;

import behaviours.Behaviour;
import main.*;

public class Wolf extends Character {
	public Wolf(Cell location, Behaviour behaviour){
		super(location, new java.awt.Color(255,0,0), new java.awt.Color(224, 224, 224), behaviour);
	}
	public void act(){
		if(checkInSight()){
			this.behaviour=new behaviours.Chase(Stage.getInstance().sheep);
		}
		location = behaviour.execute(location);
	}
	public boolean checkInSight(){		
		if(Math.abs(Stage.getInstance().sheep.location.x-this.location.x)<=5&&Math.abs(Stage.getInstance().sheep.location.y-this.location.y)<=5){
			return true;
		}else{
			return false;
		}

	}
}
package onscreen;

import main.*;

public class Wolf extends Character {
	public Wolf(Stage stage, Cell location){
		super(stage, location, new java.awt.Color(255,0,0), new java.awt.Color(153,0,0), new behaviours.Patrol());
	}
	public void act(){
		if(checkInSight()){
			this.behaviour=new behaviours.Chase(stage.sheep);
		}
		location = behaviour.execute(stage, location);
	}
	public boolean checkInSight(){
		if(Math.abs(stage.sheep.location.x-this.location.x)<=5&&Math.abs(stage.sheep.location.y-this.location.y)<=5){
			return true;
		}else{
			return false;
		}

	}
}
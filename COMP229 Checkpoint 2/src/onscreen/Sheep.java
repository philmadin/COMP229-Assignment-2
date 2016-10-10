package onscreen;

import java.awt.Graphics;

import main.*;

public class Sheep extends Character {
	public Sheep(Stage stage, Cell location){
		super(stage, location, new java.awt.Color(255,255,255), new java.awt.Color(224, 224, 224), new behaviours.Random());
	}
	public void act(){
		if(checkInSight()){
			this.behaviour=new behaviours.Chase(stage.shepherd);
		}
		location = behaviour.execute(stage, location);
	}
	public boolean checkInSight(){
		if(Math.abs(stage.shepherd.location.x-this.location.x)<=5&&Math.abs(stage.shepherd.location.y-this.location.y)<=5){
			return true;
		}else{
			return false;
		}

	}

}
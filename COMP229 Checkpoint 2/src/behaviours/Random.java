package behaviours;

import main.Stage;
import onscreen.Cell;

public class Random implements Behaviour{

	@Override
	public Cell execute(Stage stage, Cell location) {
		int randx = ((int) (Math.random()*3))-1;
		int randy = ((int) (Math.random()*3))-1;	
		if(((location.x+randx)>stage.gamesize)||((location.x+randx)<0)){
			randx=0;
		}
		if(((location.y+randy)>stage.gamesize)||((location.y+randy)<0)){
			randy=0;
		}
		return stage.grid.getCell(location.x+randx, location.y+randy);
	}
}

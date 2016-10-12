package behaviours;

import main.Stage;
import onscreen.Cell;

public class Random implements Behaviour{


	@Override
	public Cell execute(Cell location) {
		int randx = ((int) (Math.random()*3))-1;
		int randy = ((int) (Math.random()*3))-1;	
		if(((location.x+randx)>Stage.getInstance().gamesize)||((location.x+randx)<0)){
			randx=0;
		}
		if(((location.y+randy)>Stage.getInstance().gamesize)||((location.y+randy)<0)){
			randy=0;
		}
		return Stage.getInstance().grid.getCell(location.x+randx, location.y+randy);
	}
}

package behaviours;

import main.Stage;
import onscreen.Cell;

public class Patrol implements Behaviour{
	public Patrol(){};
	boolean right=false;
	@Override
	public Cell execute(Cell location) {
		if(location.x==Stage.getInstance().gamesize-1){
			right=false;
		}else if(location.x==0){
			right=true;			
		}
		return Stage.getInstance().returnHorizontalAdjacent(location, right);
	}
}

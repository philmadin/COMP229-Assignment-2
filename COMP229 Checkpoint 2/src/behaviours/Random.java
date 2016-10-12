package behaviours;

import main.Stage;
import onscreen.Cell;

public class Random implements Behaviour{
	public Random(){};

	@Override
	public Cell execute(Cell location) {		
		return Stage.getInstance().returnRandomAdjacent(location);
	}
}

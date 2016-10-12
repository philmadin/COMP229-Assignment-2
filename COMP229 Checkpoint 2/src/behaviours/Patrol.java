package behaviours;

import main.Stage;
import onscreen.Cell;

public class Patrol implements Behaviour{

	boolean right=false;
	@Override
	public Cell execute(Cell location) {
		onscreen.Cell returnable = null;
		while(returnable == null){
			try{
				if(right==true){
					returnable=Stage.getInstance().grid.getCell(location.x+1, location.y);
				}else{
					returnable=Stage.getInstance().grid.getCell(location.x-1, location.y);			
				}
				
			}catch(ArrayIndexOutOfBoundsException e){
				right=!right;
			}
		}
		return returnable;
	}

}

package behaviours;

import main.Stage;
import onscreen.Cell;

public class Patrol implements Behaviour{

	boolean right=false;
	@Override
	public Cell execute(Stage stage, Cell location) {
		onscreen.Cell returnable = null;
		while(returnable == null){
			try{
				if(right==true){
					returnable=stage.grid.getCell(location.x+1, location.y);
				}else{
					returnable=stage.grid.getCell(location.x-1, location.y);			
				}
				
			}catch(ArrayIndexOutOfBoundsException e){
				right=!right;
			}
		}
		return returnable;
	}

}

package behaviours;
import main.*;
import onscreen.*;

public class Chase implements Behaviour {
  onscreen.Character target;
  
  public Chase(onscreen.Character target){this.target = target;}

  public Cell execute(Stage stage, Cell location){
    return stage.oneCellCloserTo(location, target.getLocation());
  }

}
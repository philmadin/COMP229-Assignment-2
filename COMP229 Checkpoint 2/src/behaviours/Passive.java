package behaviours;
import main.*;
import onscreen.*;

public class Passive implements Behaviour {
	public Cell execute(Stage stage, Cell location){
		return location;
	}
}
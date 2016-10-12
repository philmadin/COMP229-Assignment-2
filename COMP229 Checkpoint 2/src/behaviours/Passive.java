package behaviours;
import main.*;
import onscreen.*;

public class Passive implements Behaviour {


	public Passive(){}
	@Override
	public Cell execute(Cell location) {
		return location;
	}
}
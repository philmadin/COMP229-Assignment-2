package behaviours;
import main.*;
import onscreen.*;

public class Passive implements Behaviour {

	@Override
	public Cell execute(Cell location) {
		return location;
	}
}
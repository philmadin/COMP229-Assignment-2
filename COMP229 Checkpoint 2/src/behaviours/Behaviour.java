package behaviours;
import main.*;
import onscreen.*;

public interface Behaviour {
  public Cell execute(Stage stage, Cell location);
}
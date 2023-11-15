/**
 * We will use this class/ interface for instance variables, a boolean preheat.
 * We will also use methods that include get or set methods for the preheat.
 * @author Ryan McKelphin
 * @version 1.0
 *
 */
public class UsesOven {

public interface UsesOven {
    boolean preheat = false;
    boolean getPreheat();
    void setPreheat(boolean preheat);
}
  
}

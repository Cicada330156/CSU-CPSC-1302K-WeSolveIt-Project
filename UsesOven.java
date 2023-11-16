/**
 * We will use this class/ interface for instance variables, a boolean preheat.
 * We will also use methods that include get or set methods for the preheat.
 * @author Ryan McKelphin
 * @version 1.0
 *
 */
public class UsesOven {

/**
* We are using an interface to UsesOven for finding out the preheat with a boolean.
*
*/
public interface UsesOven {
    boolean preheat = false;
    boolean getPreheat();
   void setPreheat(boolean preheat);
}
  
}
